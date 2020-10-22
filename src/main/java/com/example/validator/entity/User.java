package com.example.validator.entity;

import com.example.validator.annotation.ExceptionInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 在属性上配置提示信息和校验规则，接下来只用在接口的参数上添加 @valid 注解
 */
@ApiModel("用户")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @ApiModelProperty("用户Id")
    @NotNull(message = "用户Id不能为空")
    @ExceptionInfo(value = 4101, msg = "Id参数错误")
    private Long id;

    @ApiModelProperty("用户昵称")
    @NotNull(message = "用户昵称不能为空")
    @Size(min = 6,max = 10,message = "用户昵称必须为6-10位")
    @ExceptionInfo(value = 4102, msg = "昵称参数错误")
    private String username;

    @ApiModelProperty("用户密码")
    @NotNull(message = "用户密码不能为空")
    @Size(min = 6,max = 20,message = "用户密码必须为6-20位")
    @ExceptionInfo(value = 4103, msg = "密码参数错误")
    private String password;

    @ApiModelProperty("用户邮箱")
    @NotNull(message = "用户邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @ExceptionInfo(value = 4104, msg = "邮箱参数错误")
    private String email;
}
