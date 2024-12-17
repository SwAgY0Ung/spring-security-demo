package com.yhy._02_mybatis_plus_spring_security.service;

import com.yhy._02_mybatis_plus_spring_security.dto.UserDTO;
import com.yhy._02_mybatis_plus_spring_security.entity.ResponseResult;

public interface LoginService {
    ResponseResult<String> login(UserDTO userDTO);

    ResponseResult<String> logout();
}
