package com.yhy._02_mybatis_plus_spring_security.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {
    /**
     * 用户名
     */
    @NotEmpty
    private String userName;
    /**
     * 密码
     */
    @NotEmpty
    private String password;
}
