package com.feiyi.service.Impl;

import com.feiyi.dao.UserDao;
import com.feiyi.domain.CountUserByYear;
import com.feiyi.domain.Role;
import com.feiyi.domain.User;
import com.feiyi.service.UserService;
import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    //    注册，添加用户
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
        System.out.println("userid == " + user.getId());
    }

    //    登录，查找用户
    @Override
    public User findUser(User user) {
        return userDao.findUser(user);
    }

    @Override
    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public void updateUserById(User user) {
        userDao.updateUserById(user);
    }

    @Override
    public void updateFontUserById(User user) {
        userDao.updateFontUserById(user);
    }

    @Override
    public void deleteById(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public PageInfo<User> findAllUser(int pageCode, int pageSize) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<User> classInfos = userDao.findAllUser();
        return new PageInfo<>(classInfos);
    }

    @Override
    public Integer countUser() {
        return userDao.countUser();
    }

    @Override
    public List<CountUserByYear> countByYear() {
        return userDao.countByYear();
    }

    @Override
    public List<Role> findAllRole() {
        return userDao.findAllRole();
    }

    @Override
    public void updatePassword(User user) {
        userDao.updatePassword(user);
    }

    @Override
    public PageInfo<User> searchUser(int pageCode, int pageSize, String keyword) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<User> classInfos = userDao.searchUser(keyword);
        return new PageInfo<>(classInfos);
    }
}

