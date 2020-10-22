package com.example.validator.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ResponseStatus {

    SUCCESS(2000, "操作成功"),
    BAD_REQUEST(4000, "错误请求"),
    ARGUMENT_ERROR(4001, "参数异常"),
    INTERFACE_ERROR(4002, "接口异常"),
    JSON_ERROR(4003, "JSON转换异常"),
    UNKNOWN_ERROR(5000, "未知错误");

    /**
     * 业务异常码
     */
    private Integer code;

    /**
     * 业务异常信息描述
     */
    private String msg;

    ResponseStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}