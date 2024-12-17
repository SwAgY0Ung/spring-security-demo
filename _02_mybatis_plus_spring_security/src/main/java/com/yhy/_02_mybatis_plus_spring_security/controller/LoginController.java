package com.yhy._02_mybatis_plus_spring_security.controller;

import com.yhy._02_mybatis_plus_spring_security.dto.UserDTO;
import com.yhy._02_mybatis_plus_spring_security.entity.ResponseResult;
import com.yhy._02_mybatis_plus_spring_security.service.LoginService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/index")
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登陆
     * @param userDTO user传输对象，包含用户名和密码
     * @return token（jwt）
     */
    @GetMapping("/login")
    public ResponseResult<String> login(@Validated UserDTO userDTO) {
        return loginService.login(userDTO);
    }

    /**
     * 登出
     * @return
     */
    @GetMapping("/logout")
    public ResponseResult<String> logout() {
        return loginService.logout();
    }
}
