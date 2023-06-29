package com.feiyi.dao;

import com.feiyi.domain.Category;
import com.feiyi.domain.CountByCategory;
import com.feiyi.domain.Projects;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectDao {
    //查询国家非遗项目列表
    public List<Projects> findAll();

    //添加国家级项目
    public void addProject(Projects projects);

    //搜素
    public List<Projects> searchProject(String keyword);

    //修改国家级项目
    public void updateProject(Projects projects);

    //删除国家级项目
    public void deleteById(int projectId);

    //查询类别
    public List<Category> findAllCate();

    //根据id查询
    public Projects findById(int projectId);

    //    统计项目总数
    public Integer countProject();

    //    统计各类型占比
    public List<CountByCategory> countCateBai();

    //    统计年份个数
    public Integer countYear();
}
