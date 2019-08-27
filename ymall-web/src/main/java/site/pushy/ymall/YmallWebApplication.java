package site.pushy.ymall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pushy zheng
 * @since 2019/8/7
 */
@SpringBootApplication(scanBasePackages = "site.pushy.ymall")
@MapperScan("site.pushy.ymall.dao")
public class YmallWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(YmallWebApplication.class, args);
    }
}