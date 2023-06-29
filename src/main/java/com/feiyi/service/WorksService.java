package com.feiyi.service;

import com.feiyi.domain.CountWorksByYear;
import com.feiyi.domain.Works;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorksService {
    //根据作品类别id，查询作品列表
    //分页
    public PageInfo<Works> findByPageService(int pageCode, int pageSize, int categoryId);
    public List<Works> findAll(Integer categoryId);

    //根据作品类别id，查询作品列表
    public PageInfo<Works> findAll0(int pageCode, int pageSize, int categoryId);
    //public List<Works> findAll0(Integer categoryId);

    //根据作品类别id，查询没有被拉黑的
    public List<Works> findAllByStatus(Integer categoryId);

    //根据ID查找
    public Works findAllById(Integer wid);

    //添加作品
    public void addWorks(Works works);

    //修改作品状态
    public void updateWorks(Works works);

    //删除作品
    public void deleteById(int WorksId);

    //根据title查询作品
    public List<Works> findByTitle(String title);

    //根据作品类别id，统计作品数量
    public Integer countWorks();

    //根据作品类别id，统计每年近六年作品上传数量
    public List<CountWorksByYear> countWorksByYear(Integer categoryId);

    //根据用户ID查找作品
    public List<Works> findAllByUserId(int userId,int categoryId);

    //根据id修改作品
    public void updateWorksById(Works works);

    public PageInfo<Works> findByTitle(int pageCode, int pageSize,int categoryId,String keyword);
    public PageInfo<Works> findByTitle0(int pageCode, int pageSize,int categoryId,String keyword);

    //前端查找
    public List<Works> findByAll(String keyword);
}
