package com.feiyi.dao;

import com.feiyi.domain.CountUserByYear;
import com.feiyi.domain.Role;
import com.feiyi.domain.User;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    //注册
    void addUser(User user);

    //登录
    User findUser(User user);

    //搜索
    public List<User> searchUser(String keyword);

    //修改密码
    void updatePassword(User user);

    //注册根据名字查询
    User findUserByName(String name);
    //token根据id查询
    User findById(int id);

    //修改用户信息
    public void updateUserById(User user);

//    删除用户
    public void deleteById(Integer id);
    //查询所有
    List<User> findAllUser();
    //统计用户数量
    Integer countUser();

    //统计每年用户数量
    List<CountUserByYear> countByYear();

    //获取所有的用户角色
    List<Role> findAllRole();

    //前台：修改用户信息
    public void updateFontUserById(User user);
}
