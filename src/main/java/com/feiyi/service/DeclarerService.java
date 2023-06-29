package com.feiyi.service;

import com.feiyi.domain.CountByCategory;
import com.feiyi.domain.Declarer;
import com.feiyi.domain.Nation;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeclarerService {
    // 查询国家级非遗传承人列表
    //public List<Declarer> findAll();
    public PageInfo<Declarer> findAll(int pageCode, int pageSize);

    //添加国家级非遗传承人列表
    public void addDeclarer(Declarer declarer);

    //修改国家级非遗传承人列表
    public void updateDeclarer(Declarer declarer);

    //删除国家级非遗传承人列表
    public void deleteById(int declarerId);

    //查询民族
    public  List<Nation> findAllNation();

    public PageInfo<Declarer> searchDeclarer(int pageCode, int pageSize,String keyword);

    //根据id查询
    public Declarer findById(int declarerId);

    //    统计负责人总数
    public Integer countPeople();

    //    统计民族
    public List<CountByCategory> countByNation();

    //根据编号删除
    public void deleteByCode(Integer id);
}
