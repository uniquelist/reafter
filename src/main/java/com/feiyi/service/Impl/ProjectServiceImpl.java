package com.feiyi.service.Impl;

import com.feiyi.dao.DeclarerDao;
import com.feiyi.dao.ProjectDao;
import com.feiyi.domain.Category;
import com.feiyi.domain.CountByCategory;
import com.feiyi.domain.Projects;
import com.feiyi.service.ProjectService;
import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private DeclarerDao declarerDao;

    @Override
    public PageInfo<Projects> findAll(int pageCode, int pageSize) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<Projects> classInfos = projectDao.findAll();
        return new PageInfo<>(classInfos);
    }

    @Override
    public void addProject(Projects projects) {
        projectDao.addProject(projects);
    }

    @Override
    public void updateProject(Projects projects) {
        projectDao .updateProject(projects);
    }

    @Override
    public void deleteById(int projectId) {
        declarerDao.deleteByCode(projectId);
        projectDao.deleteById(projectId);

    }

    @Override
    public List<Category> findAllCate() {
        return projectDao.findAllCate();
    }

    @Override
    public Projects findById(int projectId) {
        return projectDao.findById(projectId);
    }

    @Override
    public Integer countProject() {
        return projectDao.countProject();
    }

    @Override
    public List<CountByCategory> countCateBai() {
        return projectDao.countCateBai();
    }

    @Override
    public Integer countYear() {
        return projectDao.countYear();
    }

    @Override
    public PageInfo<Projects> searchProject(int pageCode, int pageSize, String keyword) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<Projects> classInfos = projectDao.searchProject(keyword);
        return new PageInfo<>(classInfos);
    }
}

