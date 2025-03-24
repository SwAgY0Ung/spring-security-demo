package com.yhy._02_mybatis_plus_spring_security.controller;

import com.yhy._02_mybatis_plus_spring_security.entity.ResponseResult;
import com.yhy._02_mybatis_plus_spring_security.entity.SysUser;
import com.yhy._02_mybatis_plus_spring_security.service.SysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private SysUserService userService;

    /**
     * 查询
     * 需要有user:select权限
     * @return 用户列表
     */
    @PreAuthorize("hasAuthority('user:select')")
    @GetMapping("/list")
    public ResponseResult<List<SysUser>> list() {
        return new ResponseResult<>(200, "查询成功", (userService.list()));
    }

    /**
     * 新增
     * 需要有admin角色
     * @param user user实体类
     * @return 是否添加成功
     */
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/add")
    public ResponseResult<Boolean> add(SysUser user) {
        return new ResponseResult<>(200, "添加成功", userService.save(user));
    }

    /**
     * 删除
     * 需要有user:delete权限
     * 用自定义的方法进行权限校验
     * @param id 删除用户的主键
     * @return 是否删除成功
     */
    @PreAuthorize("@customMethod.hasAuthority('user:delete')")
    public ResponseResult<Boolean> delete(Integer id) {
        return new ResponseResult<>(200, "删除成功", userService.removeById(id));
    }
}
