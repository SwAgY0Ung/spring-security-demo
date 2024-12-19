package com.yhy._02_mybatis_plus_spring_security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan(basePackages={"com.yhy._02_mybatis_plus_spring_security.dao"})
@SpringBootApplication
public class MybatisPlusSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusSpringSecurityApplication.class, args);
    }

}
