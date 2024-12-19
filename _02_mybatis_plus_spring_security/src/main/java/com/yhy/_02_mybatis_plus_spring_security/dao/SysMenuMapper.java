package com.yhy._02_mybatis_plus_spring_security.dao;

import com.yhy._02_mybatis_plus_spring_security.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author yhy
* @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
* @createDate 2024-12-19 22:53:53
* @Entity com.yhy._02_mybatis_plus_spring_security.entity.SysMenu
*/
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 通过用户id查询权限集合
     * @param userId 用户id
     * @return 权限结合
     */
    List<String> getMenusByUserId(@Param("userId") Long userId);
}




