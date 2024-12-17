package com.yhy._02_mybatis_plus_spring_security;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/*
 *   @author:Nico
 *   @create date:2022/8/12 0012 16:19
 *   @mail:hjx674894982@gmail.com
 *
 *
 *
 *
 */
/**
 * @Author 三更  B站： https://space.bilibili.com/663528522
 */
@SpringBootTest
public class ApplicationTests {

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void TestBCryptPasswordEncoder()
    {
        String encode = passwordEncoder.encode("asdf1234");
        System.out.println(encode);
    }
}