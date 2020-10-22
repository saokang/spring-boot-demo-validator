package com.example.validator.config;

import com.example.validator.annotation.ExceptionInfo;
import com.example.validator.enums.ResponseStatus;
import com.example.validator.exception.InterfaceException;
import com.example.validator.utils.ResponseUtil;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;

/**
 * 全局异常统一处理
 */
@RestControllerAdvice
public class GlobalExceptionHandle {

    /**
     * 捕获 MethodArgumentNotValidException 类型异常并处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseUtil<String> MethodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) throws NoSuchFieldException {
        /** v2.0 返回值是 String 类型
         * // 从异常对象中获取 ObjectError 对象
         * ObjectError error = e.getBindingResult().getAllErrors().get(0);
         * // 然后提取错误信息进行返回
         * return error.getDefaultMessage();
         */

        /** v4.0 返回值是 ResponseUtil<String> 类型
         * // 从异常对象中获取 ObjectError 对象
         * ObjectError error = e.getBindingResult().getAllErrors().get(0);
         * // 然后提取错误信息进行返回
         * return ResponseUtil.failure(ResultStatus.ARGUMENT_ERROR, error.getDefaultMessage());
         */

        /** v5.0 返回值是 ResponseUtil<String> 类型
         *
         */
        // 从异常对象中获得异常信息
        String errorMsg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        // 参数的 class 对象，方便等下通过字段名拿到 Field 对象
        Class<?> parameterType = e.getParameter().getParameterType();
        // 拿到错误字段名称
        String fieldName = e.getBindingResult().getFieldError().getField();
        Field errorField = parameterType.getDeclaredField(fieldName);
        // 获取字段上的自定义注解
        ExceptionInfo annotation = errorField.getAnnotation(ExceptionInfo.class);

        // 若存在自定义注解，返回自定义注解信息
        if (annotation != null) {
            return ResponseUtil.failure(annotation.value(), annotation.msg(), errorMsg);
        }

        return ResponseUtil.failure(ResponseStatus.ARGUMENT_ERROR, errorMsg);
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(InterfaceException.class)
    public ResponseUtil<String> InterfaceExceptionHandle(InterfaceException e) {
        return ResponseUtil.failure(ResponseStatus.INTERFACE_ERROR, e.getMsg());
    }
}
