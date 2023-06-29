package com.feiyi.service.Impl;

import com.feiyi.dao.DeclarerDao;
import com.feiyi.domain.CountByCategory;
import com.feiyi.domain.Declarer;
import com.feiyi.domain.Nation;
import com.feiyi.service.DeclarerService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeclarerServiceImpl implements DeclarerService {
    @Autowired
    private DeclarerDao declarerDao;

    /*@Override
    public List<Declarer> findAll(int currentPage,int pageSize) {
        Map<String,Object> map=new HashMap<>();
        int startCurrentPage=(currentPage-1)*pageSize;        //从第几个数据开始
        int count=countPeople();
        int totalPage=count/pageSize;                   //总页数
        if (currentPage>totalPage || currentPage<=0){
            return null;
        }else{
            map.put("currentPage",startCurrentPage);
            map.put("size",pageSize);
            List<Declarer> list = declarerDao.findAll(map);
            return list;
        }
    }*/

    @Override
    public PageInfo<Declarer> findAll(int pageCode, int pageSize) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<Declarer> classInfos = declarerDao.findAll();
        for (Declarer declarer:classInfos)
        {
            System.out.println(declarer);
        }
        return new PageInfo<>(classInfos);
    }

    @Override
    public void addDeclarer(Declarer declarer) {
        declarerDao.addDeclarer(declarer);
    }

    @Override
    public void updateDeclarer(Declarer declarer) {
        declarerDao.updateDeclarer(declarer);
    }

    @Override
    public void deleteById(int declarerId) {
        declarerDao.deleteById(declarerId);
    }

    @Override
    public List<Nation> findAllNation() {
        return declarerDao.findAllNation();
    }

    @Override
    public PageInfo<Declarer> searchDeclarer(int pageCode, int pageSize, String keyword) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<Declarer> classInfos = declarerDao.searchDeclarer(keyword);
        return new PageInfo<>(classInfos);
    }

    @Override
    public Declarer findById(int declarerId) {
        return declarerDao.findById(declarerId);
    }

    @Override
    public Integer countPeople() {
        return declarerDao.countPeople();
    }

    @Override
    public List<CountByCategory> countByNation() {
        return declarerDao.countByNation();
    }

    @Override
    public void deleteByCode(Integer id) {
        declarerDao.deleteByCode(id);
    }
}

