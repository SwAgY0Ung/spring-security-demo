package com.yhy._02_mybatis_plus_spring_security.security;

import com.yhy._02_mybatis_plus_spring_security.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

/**
 * 自定义权限校验方法
 * @Auther yhy
 * @Date 2024/12/24 21:56
 */
@Component("customMethod")
public class CustomExpressionRoot {

    public boolean hasAuthority(String authority){
        // 获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        // 获取当前用户所有用的权限
        List<String> permissions = loginUser.getPermissions();
        HashSet<String> permissionSet = new HashSet<>(permissions); //转换成set效率更高

        // 直接和我们自定义的权限集合：Permissions进行比较，如果用户有该权限则返回true
        return permissionSet.contains(authority);
    }

}
