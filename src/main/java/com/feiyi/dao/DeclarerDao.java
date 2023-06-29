package com.feiyi.dao;

import com.feiyi.domain.CountByCategory;
import com.feiyi.domain.Declarer;
import com.feiyi.domain.Nation;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeclarerDao {
    //查询国家非遗传承人列表
    //public List<Declarer> findAll();
    public List<Declarer> findAll();

    public List<Declarer> searchDeclarer(String keyword);

    //添加国家级非遗传承人列表
    public void addDeclarer(Declarer declarer);

    //修改国家级非遗传承人列表
    public void updateDeclarer(Declarer declarer);

    //删除国家级非遗传承人列表
    public void deleteById(int declarerId);

    //查询民族
    public  List<Nation> findAllNation();

    //根据id查询
    public Declarer findById(int declarerId);

    //    统计负责人总数
    public Integer countPeople();

    //    统计民族
    public List<CountByCategory> countByNation();

    //根据编号删除
    public void deleteByCode(Integer id);

}
