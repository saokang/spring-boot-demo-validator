package com.example.validator.service;

import com.example.validator.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String addUser(User user) {
        System.out.println("昵称为: " + user.getUsername() + " 的用户添加成功！");
        return "SUCCESS";
    }
}
