package com.yhy._02_mybatis_plus_spring_security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhy._02_mybatis_plus_spring_security.entity.SysUser;
import com.yhy._02_mybatis_plus_spring_security.service.SysUserService;
import com.yhy._02_mybatis_plus_spring_security.dao.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author yhy
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2024-12-09 22:59:07
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}




