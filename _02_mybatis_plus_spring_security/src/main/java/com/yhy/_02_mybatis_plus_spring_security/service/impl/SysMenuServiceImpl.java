package com.yhy._02_mybatis_plus_spring_security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhy._02_mybatis_plus_spring_security.entity.SysMenu;
import com.yhy._02_mybatis_plus_spring_security.service.SysMenuService;
import com.yhy._02_mybatis_plus_spring_security.dao.SysMenuMapper;
import org.springframework.stereotype.Service;

/**
* @author yhy
* @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
* @createDate 2024-12-19 22:53:53
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService{

}




