package com.yhy._02_mybatis_plus_spring_security.exception;/**
 * @Auther yhy
 * @Date 2024/12/24 21:44
 */

import com.alibaba.fastjson.JSON;
import com.yhy._02_mybatis_plus_spring_security.entity.ResponseResult;
import com.yhy._02_mybatis_plus_spring_security.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SpringSecurity的认证失败的处理器
 *
 * @Auther yhy
 * @Date 2024/12/24 21:44
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "认证失败请重新登录");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);
    }
}
