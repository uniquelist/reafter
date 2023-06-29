package com.feiyi.service.Impl;

import com.feiyi.dao.ExhibitionDao;
import com.feiyi.domain.Exhibition;
import com.feiyi.service.ExhibitionService;
import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ExhibitionServiceImpl implements ExhibitionService {
    @Autowired
    private ExhibitionDao exhibitionDao;
    @Override
    public PageInfo<Exhibition> findAll(int pageCode, int pageSize) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<Exhibition> classInfos =  exhibitionDao.findAll();
        return new PageInfo<>(classInfos);
    }

    @Override
    public PageInfo<Exhibition> findAllByStatus(int pageCode, int pageSize, int status) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<Exhibition> classInfos =  exhibitionDao.findAllByStatus(status);
        return new PageInfo<>(classInfos);
    }

    @Override
    public List<Exhibition> findAllByStatus(int status) {
        return exhibitionDao.findAllByStatus(status);
    }

    @Override
    public void addExhibition(Exhibition exhibition) {
        exhibitionDao.addExhibition(exhibition);
    }

    @Override
    public void deleteExhibition(int id) {
        exhibitionDao.deleteExhibition(id);
    }

    @Override
    public void updateExhibition(Exhibition exhibition) {
        exhibitionDao.updateExhibition(exhibition);
    }

    @Override
    public Exhibition findById(int id) {
        return exhibitionDao.findById(id);
    }

    @Override
    public List<Exhibition> findByTitle(String title) {
        return exhibitionDao.findByTitle(title);
    }

    @Override
    public void updateExhibitionStatus(Exhibition exhibition) {
        exhibitionDao.updateExhibitionStatus(exhibition);
    }

    @Override
    public Integer countExhibitionByStatus() {
        return exhibitionDao.countExhibitionByStatus();
    }

    @Override
    public PageInfo<Exhibition> findExhibition(int pageCode, int pageSize, String keyword) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<Exhibition> classInfos = exhibitionDao.findExhibition(keyword);
        return new PageInfo<>(classInfos);
    }
    @Override
    public PageInfo<Exhibition> findExhibition0(int pageCode, int pageSize, String keyword) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<Exhibition> classInfos = exhibitionDao.findExhibition0(keyword);
        return new PageInfo<>(classInfos);
    }

    @Override
    public List<Exhibition> findByAll(String keyword) {
        return exhibitionDao.findByAll(keyword);
    }


}
