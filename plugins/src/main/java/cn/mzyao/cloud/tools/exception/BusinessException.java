package cn.mzyao.cloud.tools.exception;

import cn.mzyao.cloud.tools.enums.ResponseCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ResponseCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ResponseCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

}
