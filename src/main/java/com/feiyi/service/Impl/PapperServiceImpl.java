package com.feiyi.service.Impl;

import com.feiyi.dao.PapperDao;
import com.feiyi.domain.Papper;
import com.feiyi.service.PapperService;
import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PapperServiceImpl implements PapperService {
    @Autowired
    private PapperDao papperDao;

    @Override
    public List<Papper> findAll() {
        return papperDao.findAll();
    }

    @Override
    public PageInfo<Papper> findAll(int pageCode, int pageSize) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<Papper> classInfos = papperDao.findAll();
        return new PageInfo<>(classInfos);
    }

    @Override
    public void addPapper(Papper papper) {
        papperDao.addPapper(papper);
    }

    @Override
    public void deleteById(int papperId) {
        papperDao.deleteById(papperId);
    }

    @Override
    public PageInfo<Papper> searchPapper(int pageCode, int pageSize, String keyword) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<Papper> classInfos = papperDao.searchPapper(keyword);
        return new PageInfo<>(classInfos);
    }
}

