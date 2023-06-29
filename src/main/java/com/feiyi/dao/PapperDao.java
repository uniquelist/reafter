package com.feiyi.dao;

import com.feiyi.domain.Papper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PapperDao {
    //查询文章列表
    public List<Papper> findAll();
    //添加文章
    public void addPapper(Papper papper);
    //删除文章
    public void deleteById(int papperId);

    //根据输入内容查询
    public List<Papper> searchPapper(String keyword);
}
