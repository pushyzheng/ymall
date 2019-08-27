package site.pushy.ymall;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import site.pushy.ymall.common.exception.HttpErrorException;
import site.pushy.ymall.common.exception.ServerUnavailableErrorException;
import site.pushy.ymall.common.jedis.JedisClient;
import site.pushy.ymall.common.limit.RateLimit;

import javax.servlet.http.HttpServletRequest;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpRetryException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Pushy
 * @since 2019-7-24 12:43:09
 */
@Aspect
@Configuration
public class RateLimitAspect {

    private static final Logger logger = LoggerFactory.getLogger(RateLimitAspect.class);

    @Autowired
    private JedisClient jedisClient;

    private String rateLimitScript;

    @Around("execution(* site.pushy.ymall.web ..*(..) )")
    public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取到注解到方法上的注解类 RateLimit
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RateLimit rateLimit = method.getAnnotation(RateLimit.class);
        if (rateLimit == null) {
            return joinPoint.proceed();
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String key = getLimitKey(request);
        List<String> keys = Collections.singletonList(key);

        // 执行Lua脚本
        if (rateLimitScript == null) {
            try {
                readRateScript();
            } catch (IOException e) { // 如果读取失败的话，在直接返回，取消限流
                e.printStackTrace();
                return joinPoint.proceed();
            }
        }
        List<String> args = new ArrayList<>();
        args.add(String.valueOf(rateLimit.count()));
        args.add(String.valueOf(rateLimit.time()));

        long result = (long) jedisClient.eval(rateLimitScript, keys, args);
        if (result != 0L && result <= rateLimit.count()) {
            return joinPoint.proceed();
        }
        else {
            throw new ServerUnavailableErrorException("请求过于频繁，请稍后访问！");
        }
    }

    /**
     * 根据业务逻辑创建限流的KEY，如果要把用户对所有接口限流，则KEY设置为userId
     */
    private String getLimitKey(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        if (userId == null) {
            throw new HttpErrorException("认证失败");
        }
        return userId;
    }

    private void readRateScript() throws IOException {
        Resource resource = new ClassPathResource("rateLimit.lua");
        if (!resource.exists()) {
            throw new NoSuchFileException("Cannot be find the rateLimit.lua file");
        }
        FileReader reader = new FileReader(resource.getFile());
        StringBuilder result = new StringBuilder();
        while (reader.ready()) {
            result.append((char) reader.read());
        }
        rateLimitScript = result.toString();
    }

}
