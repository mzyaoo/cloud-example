package cn.mzyao.cloud.tools.exception;

import cn.mzyao.cloud.tools.enums.ResponseCode;
import lombok.Getter;

@Getter
public class GatewayException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public GatewayException(int code, String message) {
        super(message);
        this.code = code;
    }

    public GatewayException(ResponseCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public GatewayException(ResponseCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }


}
