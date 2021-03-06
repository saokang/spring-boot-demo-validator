package com.example.validator.config;

import com.example.validator.annotation.NotResponseBody;
import com.example.validator.enums.ResponseStatus;
import com.example.validator.exception.InterfaceException;
import com.example.validator.utils.ResponseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 增强响应信息：重写的这两个方法是用来在controller将数据进行返回前进行增强操作，
 * supports方法要返回为true才会执行beforeBodyWrite方法，
 * 所以如果有些情况不需要进行增强操作可以在supports方法里进行判断。
 * 对返回数据进行真正的操作还是在beforeBodyWrite方法中，
 * 我们可以直接在该方法里包装数据，这样就不需要每个接口都进行数据包装了，省去了很多麻烦。
 */
@RestControllerAdvice(basePackages = "com.example.validator.controller")
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 如果接口返回的类型本身就是 ResponseUtil 类型，那就没有必要进行额外操作，返回 false 不执行其他操作。
     * 如果接口添加了自定义注解 @NotResponseBody，也无需额外操作！
     */
    @Override
    public boolean supports(MethodParameter returnType, Class aClass) {
        return !(returnType.getParameterType().equals(ResponseUtil.class) || returnType.hasMethodAnnotation(NotResponseBody.class));
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 若返回类型是 string，需要将数据包装转成JSON格式返回前端
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(ResponseUtil.success(data));
            } catch (JsonProcessingException e) {
                throw new InterfaceException(ResponseStatus.JSON_ERROR.getCode(), ResponseStatus.JSON_ERROR.getMsg());
            }
        }
        // 将原本数据包装并返回
        return ResponseUtil.success(data);
    }
}
