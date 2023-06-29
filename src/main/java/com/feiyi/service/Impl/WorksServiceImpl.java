package com.feiyi.service.Impl;

import com.feiyi.dao.WorksDao;
import com.feiyi.domain.CountWorksByYear;
import com.feiyi.domain.Works;
import com.feiyi.service.WorksService;
import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorksServiceImpl implements WorksService {
    @Autowired
    private WorksDao worksDao;
    @Override
    public PageInfo<Works> findByPageService(int pageCode, int pageSize, int categoryId) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<Works> classInfos = worksDao.findAll(categoryId);
        return new PageInfo<>(classInfos);
    }

    @Override
    public List<Works> findAll(Integer categoryId) {
        return worksDao.findAllByStatus(categoryId);
    }

    @Override
    public PageInfo<Works> findAll0(int pageCode, int pageSize, int categoryId) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<Works> classInfos = worksDao.findAll0(categoryId);
        return new PageInfo<>(classInfos);
    }

    @Override
    public List<Works> findAllByStatus(Integer categoryId) {
        return worksDao.findAllByStatus(categoryId);
    }

    @Override
    public Works findAllById(Integer wid) {
        return worksDao.findAllById(wid);
    }

    @Override
    public void addWorks(Works works) {
        worksDao.addWorks(works);
    }

    @Override
    public void updateWorks(Works works) {
        worksDao.updateWorks(works);
    }

    @Override
    public void deleteById(int WorksId) {
        worksDao.deleteById(WorksId);
    }

    @Override
    public List<Works> findByTitle(String title) {
        return worksDao.findByTitle(title);
    }

    @Override
    public Integer countWorks() {
        return worksDao.countWorks();
    }

    @Override
    public List<CountWorksByYear> countWorksByYear(Integer categoryId) {
        return worksDao.countWorksByYear(categoryId);
    }

    @Override
    public List<Works> findAllByUserId(int userId, int categoryId) {
        return worksDao.findAllByUserId(userId,categoryId);
    }

    @Override
    public void updateWorksById(Works works) {
        worksDao.updateWorksById(works);
    }

    @Override
    public PageInfo<Works> findByTitle(int pageCode, int pageSize, int categoryId,String keyword) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("keyword", keyword);
        paramMap.put("categoryId", categoryId);
        List<Works> classInfos = worksDao.findByTitle(paramMap);
        return new PageInfo<>(classInfos);
    }
    @Override
    public PageInfo<Works> findByTitle0(int pageCode, int pageSize, int categoryId,String keyword) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("keyword", keyword);
        paramMap.put("categoryId", categoryId);
        List<Works> classInfos = worksDao.findByTitle0(paramMap);
        return new PageInfo<>(classInfos);
    }

    @Override
    public List<Works> findByAll(String keyword) {
        return worksDao.findByAll(keyword);
    }
}
