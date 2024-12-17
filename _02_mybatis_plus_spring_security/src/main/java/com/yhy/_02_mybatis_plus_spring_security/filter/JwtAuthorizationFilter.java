package com.yhy._02_mybatis_plus_spring_security.filter;

import com.yhy._02_mybatis_plus_spring_security.entity.LoginUser;
import com.yhy._02_mybatis_plus_spring_security.utils.JwtUtil;
import com.yhy._02_mybatis_plus_spring_security.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * jwt过滤器
 * 检查请求头中是否携带token，如果携带token就将用户信息存入securityContextHolder
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Resource
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1.获取请求头中的token（一般是由authorization携带的jwt）
        String jwt = request.getHeader("Authorization");
        // 1.1.如果请求头中没有jwt，该过滤器直接放行就可以，交给后面的过滤器去处理
        if (!StringUtils.hasText(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2.解析jwt中的payload(subject)，该项目中存放的是userId
        Claims claims;
        try {
            claims = JwtUtil.parseJWT(jwt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String userId = claims.getSubject();

        // 3.取出redis中保存的用户信息
        LoginUser loginUser = redisUtils.getCacheObject("login:" + userId);

        if(Objects.isNull(loginUser)){
            // 不保存，直接放行（之后写一个全局异常拦截器，拦截403。报未登录异常就可以了）
            filterChain.doFilter(request, response);
        }

        // 4.将用户信息放入到SecurityContextHolder中，方便后续获取
        // 这里创建一个UsernamePasswordAuthenticationToken，使用三个参数的构造器，会将Authenticated设置为true，表示已经认证过
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
