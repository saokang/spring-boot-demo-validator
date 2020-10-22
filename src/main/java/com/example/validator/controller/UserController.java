package com.example.validator.controller;

import com.example.validator.annotation.NotResponseBody;
import com.example.validator.entity.User;
import com.example.validator.service.UserService;
import com.example.validator.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api("UserController")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("v1.0 通过BandingResult返回错误信息的测试接口【已废除】")
    @RequestMapping(value = "/addUserWithBandingResult", method = RequestMethod.POST)
    public String addUserWithBandingResult(@RequestBody @Valid User user, BindingResult bindingResult) {
        // 若参数校验失败，错误信息会封装在 bindingResult 对象里
        for (ObjectError error : bindingResult.getAllErrors()) {
            return error.getDefaultMessage();
        }
        return userService.addUser(user);
    }

    @ApiOperation("v2.0 通过捕获抛出的异常返回错误信息【已废除】")
    @RequestMapping(value = "/addUserWithThrowException", method = RequestMethod.POST)
    public String addUserWithThrowException(@RequestBody @Valid User user) {
        // 若参数校验失败，会抛出异常，由全局异常统一处理
        return userService.addUser(user);
    }

    @ApiOperation("v3.0 测试统一JSON返回格式")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseUtil<User> get() {
        User user = new User(100L, "黄志康", "123456", "1527136955@qq.com");
        return ResponseUtil.success(user);
    }

    @ApiOperation("v4.0 通过捕获抛出的异常统一JSON返回错误信息")
    @RequestMapping(value = "/addUserWithThrowExceptionAndJSON", method = RequestMethod.POST)
    public ResponseUtil<String> addUserWithThrowExceptionAndJSON(@RequestBody @Valid User user) {
        // 若参数校验失败，会抛出异常，由全局异常统一处理
        return ResponseUtil.success(userService.addUser(user));
    }

    @ApiOperation("v5.0 通过自定义注解并捕获抛出的异常统一JSON返回错误信息")
    @RequestMapping(value = "/addUserWithThrowExceptionDiyAnnotationAndJSON", method = RequestMethod.POST)
    public ResponseUtil<String> addUserWithThrowExceptionDiyAnnotationAndJSON(@RequestBody @Valid User user) {
        // 若参数校验失败，会抛出异常，由全局异常统一处理
        return ResponseUtil.success(userService.addUser(user));
    }

    @ApiOperation("v6.0 测试增强返回格式")
    @RequestMapping(value = "/getAdviceUser", method = RequestMethod.GET)
    @NotResponseBody
    public User getAdviceUser() {
        User user = new User(100L, "黄志康", "654321", "1527136955@qq.com");
        return user;
    }
}
