package com.example.validator.exception;

import com.example.validator.enums.ResponseStatus;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class InterfaceException extends RuntimeException {

    private Integer code;
    private String msg;

    public InterfaceException() {
        this(ResponseStatus.INTERFACE_ERROR.getCode(), ResponseStatus.INTERFACE_ERROR.getMsg());
    }

    public InterfaceException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
