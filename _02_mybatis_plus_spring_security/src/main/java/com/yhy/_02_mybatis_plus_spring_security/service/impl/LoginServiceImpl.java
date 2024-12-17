package com.yhy._02_mybatis_plus_spring_security.service.impl;

import com.yhy._02_mybatis_plus_spring_security.dto.UserDTO;
import com.yhy._02_mybatis_plus_spring_security.entity.LoginUser;
import com.yhy._02_mybatis_plus_spring_security.entity.ResponseResult;
import com.yhy._02_mybatis_plus_spring_security.service.LoginService;
import com.yhy._02_mybatis_plus_spring_security.utils.JwtUtil;
import com.yhy._02_mybatis_plus_spring_security.utils.RedisUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisUtils redisUtils;

    @Override
    public ResponseResult<String> login(UserDTO userDTO) {
        // 1.获取authenticationManager对象（就像shiro中获取SecurityManager一样）
        // ，调用authenticationManager.authenticate方法（就像shiro中调用securityManager.login方法一样）
        // 1.1创建UsernamePasswordAuthenticationToken（就像shiro中创建UsernamePasswordToken一样）
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        // 1.2.getPrincipal就是获取封装好的userDetails对象，这里由于我们重写了，所以返回的是LoginUser，直接强转就可以
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        // 1.2.获取LoginUser中的User属性，再获取User中的userId
        String userId = loginUser.getUser().getId().toString();

        // 2.将userId作为payload封装到jwt中
        String jwt = JwtUtil.createJWT(userId);

        // 3.将用户数据缓存到redis中，后续用jwt验证时，从redis中获取用户信息
        redisUtils.setCacheObject("login:" + userId, loginUser);

        return new ResponseResult<>(200, "登录成功", jwt);
    }

    /**
     * 登出
     * @return
     */
    @Override
    public ResponseResult<String> logout() {
        // 1.从securityContextHolder中获取用户数据
        //  我们实现UserDetailsService接口时，向contextHolder中保存的是UsernamePasswordAuthenticationToken，这里直接强制转换即可
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        // 2.从用户数据中获取userId
        Long userId = loginUser.getUser().getId();

        // 3.根据userId删除redis中的用户数据
        boolean result = redisUtils.deleteObject("login:" + userId);
        return new ResponseResult<>(200, result ? "退出成功" : "退出失败");
    }
}
