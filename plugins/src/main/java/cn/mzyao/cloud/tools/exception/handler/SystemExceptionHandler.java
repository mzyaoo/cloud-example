package cn.mzyao.cloud.tools.exception.handler;


import cn.mzyao.cloud.tools.exception.GatewayException;
import cn.mzyao.cloud.tools.response.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SystemExceptionHandler {

    @ExceptionHandler(GatewayException.class)
    public Result<?> businessExceptionHandler(GatewayException e){
        return Result.fail(e.getCode(),e.getMessage());
    }

}
