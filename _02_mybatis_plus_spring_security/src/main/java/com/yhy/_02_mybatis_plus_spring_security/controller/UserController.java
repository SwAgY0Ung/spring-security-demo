package com.yhy._02_mybatis_plus_spring_security.controller;

import com.yhy._02_mybatis_plus_spring_security.entity.SysUser;
import com.yhy._02_mybatis_plus_spring_security.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private SysUserService userService;

    @GetMapping("/list")
    public List<SysUser> list() {
        return userService.list();
    }

}
