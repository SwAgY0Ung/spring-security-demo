package com.yhy._02_mybatis_plus_spring_security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yhy._02_mybatis_plus_spring_security.dao.SysMenuMapper;
import com.yhy._02_mybatis_plus_spring_security.dao.SysUserMapper;
import com.yhy._02_mybatis_plus_spring_security.entity.LoginUser;
import com.yhy._02_mybatis_plus_spring_security.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * userDetails实现类
 * 由于SpringSecurity中默认的UserDetailsService默认是从内存中查询的，
 * 所以这里直接实现UserDetailsService接口，重写里面的loadUserByUsername方法，改成从数据库查询
 *
 * @author Echo
 * @date
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 1.从数据库中查询用户
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName, s);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);

        // 1.1校验用户如果不存在，则返回异常
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 2.从数据库中查询权限信息
        List<String> menus = sysMenuMapper.getMenusByUserId(sysUser.getId());

        /*
         3.封装成UserDetails对象返回
         由于UserDetails是接口，所以我们可以新写一个实现类，实现UserDetails接口，然后返回。
         所以这里直接将loginUser实现UserDetails对象返回即可
         将用户信息和权限信息一并保存到封装好的LoginUser当中即可
         */
        return new LoginUser(sysUser, menus);
    }
}
