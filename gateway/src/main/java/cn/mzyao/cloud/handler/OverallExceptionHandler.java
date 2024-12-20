package cn.mzyao.cloud.handler;

import cn.hutool.json.JSONUtil;
import cn.mzyao.cloud.tools.exception.GatewayException;
import cn.mzyao.cloud.tools.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;


@Slf4j
@Order(-1)
@Configuration
public class OverallExceptionHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();

        response.getHeaders().add("Content-Type","application/json;charset=UTF-8");

        if (response.isCommitted()) {
            // 对于已经committed(提交)的response，就不能再使用这个response向缓冲区写任何东西
            return Mono.error(ex);
        }

        // 按照异常类型进行翻译处理，翻译的结果易于前端理解
        String message;
        if (ex instanceof NotFoundException) {
            response.setStatusCode(HttpStatus.NOT_FOUND);
            message = "您请求的服务不存在";
        } else if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            response.setStatusCode(responseStatusException.getStatus());
            message = ex.getMessage();
        } else if (ex instanceof GatewayException) {
            response.setStatusCode(HttpStatus.FORBIDDEN);
            message = ex.getMessage();
        } else {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            message = "服务器内部错误";
        }

        writeLog(exchange, ex);

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(JSONUtil.toJsonStr(Result.fail(message)).getBytes());
        }));

    }

    //将错误信息以日志的形式记录下来
    private void writeLog(ServerWebExchange exchange, Throwable ex) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        String host = uri.getHost();
        int port = uri.getPort();
        log.error("[gateway]-host:{} ,port:{}，url:{},  errormessage:",
                host,
                port,
                request.getPath(),
                ex);
    }

}
