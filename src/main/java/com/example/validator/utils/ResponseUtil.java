package com.example.validator.utils;

import com.example.validator.enums.ResponseStatus;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseUtil<T> {

    /**
     * 状态码：比如 1000 代表操作成功
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    public ResponseUtil(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseUtil(ResponseStatus resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.msg = resultStatus.getMsg();
        this.data = data;
    }

    public static <T> ResponseUtil<T> success() {
        return new ResponseUtil<>(ResponseStatus.SUCCESS, null);
    }

    public static <T> ResponseUtil<T> success(T data) {
        return new ResponseUtil<>(ResponseStatus.SUCCESS, data);
    }

    public static <T> ResponseUtil<T> success(ResponseStatus resultStatus, T data) {
        return new ResponseUtil<>(resultStatus, data);
    }

    public static <T> ResponseUtil<T> success(Integer code, String msg, T data) {
        return new ResponseUtil<>(code, msg, data);
    }

    public static <T> ResponseUtil<T> failure() {
        return new ResponseUtil<>(ResponseStatus.UNKNOWN_ERROR, null);
    }

    public static <T> ResponseUtil<T> failure(T data) {
        return new ResponseUtil<>(ResponseStatus.UNKNOWN_ERROR, data);
    }

    public static <T> ResponseUtil<T> failure(ResponseStatus resultStatus, T data) {
        return new ResponseUtil<>(resultStatus, data);
    }

    public static <T> ResponseUtil<T> failure(Integer code, String msg, T data) {
        return new ResponseUtil<>(code, msg, data);
    }
}
