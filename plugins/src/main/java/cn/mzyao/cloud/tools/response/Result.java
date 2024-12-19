package cn.mzyao.cloud.tools.response;

import cn.mzyao.cloud.tools.enums.ResponseCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result <T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T data;

    public static <T> Result<T> success() {
        return restResult(null, ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    public static <T> Result<T> success(T data) {
        return restResult(data, ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    public static <T> Result<T> success(T data, String msg) {
        return restResult(data, ResponseCode.SUCCESS.getCode(), msg);
    }


    public static <T> Result<T> fail() {
        return restResult(null, ResponseCode.SYSTEM_ERROR.getCode(), ResponseCode.SYSTEM_ERROR.getMessage());
    }

    public static <T> Result<T> fail(String msg) {
        return restResult(null, ResponseCode.SYSTEM_ERROR.getCode(), msg);
    }

    public static <T> Result<T> fail(ResponseCode errorCode) {
        return restResult(null, errorCode.getCode(), errorCode.getMessage());
    }

    public static <T> Result<T> fail(ResponseCode errorCode, T data) {
        return restResult(data, errorCode.getCode(), errorCode.getMessage());
    }


    public static <T> Result<T> fail(T data) {
        return restResult(data, ResponseCode.SYSTEM_ERROR.getCode(), "操作失败");
    }

    public static <T> Result<T> fail(T data, String msg) {
        return restResult(data, ResponseCode.SYSTEM_ERROR.getCode(), msg);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    private static <T> Result<T> restResult(T data, int code, String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public static <T> Boolean isError(Result<T> ret) {
        return !isSuccess(ret);
    }

    public static <T> Boolean isSuccess(Result<T> ret) {
        return ResponseCode.SUCCESS.getCode() == ret.getCode();
    }

}
