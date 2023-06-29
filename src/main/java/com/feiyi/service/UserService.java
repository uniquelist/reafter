package com.feiyi.service;

import com.feiyi.domain.CountUserByYear;
import com.feiyi.domain.Role;
import com.feiyi.domain.User;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    // 添加用户，注册
    public void addUser(User user);
    // 查询用户，登录
    public User findUser(User user);
    //注册根据名字查询
    User findUserByName(String username);

    //token根据id查询
   User findById(int id);

    //修改用户信息
    public void updateUserById(User user);

    //前台：修改用户信息
    public void updateFontUserById(User user);

    //    删除用户
    public void deleteById(Integer id);

    //查询所有
    public PageInfo<User> findAllUser(int pageCode, int pageSize);
    //List<User> findAllUser();

    //统计用户数量
    Integer countUser();

    //统计每年用户数量
    List<CountUserByYear> countByYear();

    //获取所有的用户角色
    List<Role> findAllRole();

    //修改密码
    void updatePassword(User user);

    //搜索
    public PageInfo<User> searchUser(int pageCode, int pageSize,String keyword);
}
