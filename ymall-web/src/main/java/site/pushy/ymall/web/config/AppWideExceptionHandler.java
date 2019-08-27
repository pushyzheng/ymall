package site.pushy.ymall.web.config;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import site.pushy.ymall.common.exception.HttpErrorException;
import site.pushy.ymall.common.exception.ServerUnavailableErrorException;
import site.pushy.ymall.common.util.RespUtil;

@ControllerAdvice
public class AppWideExceptionHandler {

    /**
     * 处理GET请求必要查询字符串缺少的错误
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String missingParameterException(MissingServletRequestParameterException e) {
        String errorMsg;
        if (e != null) {
            String parameterName = e.getParameterName();
            errorMsg = String.format("Required parameter '%s' is not present", parameterName);
        } else {
            errorMsg = "请求数据校验不合法";
        }
        return RespUtil.error(errorMsg);
    }

    /**
     * 处理POST表单校验不通过的错误
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String argumentNotValidException(MethodArgumentNotValidException e) {
        String errorMsg;
        if (e != null) {
            BindingResult result = e.getBindingResult();
            FieldError error = result.getFieldError();
            String field = error.getField();
            errorMsg = String.format("Form parameter '%s' is not present", field);
        } else {
            errorMsg = "请求表单参数不合法";
        }
        return RespUtil.error(errorMsg);
    }

    @ExceptionHandler(HttpErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String errorException(HttpErrorException e) {
        return RespUtil.error(e.getMessage());
    }

    @ExceptionHandler(ServerUnavailableErrorException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public String errorException(ServerUnavailableErrorException e) {
        return RespUtil.error(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String errorException(RuntimeException e) {
        e.printStackTrace();
        return RespUtil.error(e.getMessage());
    }


}
