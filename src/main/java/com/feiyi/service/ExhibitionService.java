package com.feiyi.service;

import com.feiyi.domain.Exhibition;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExhibitionService {
    //显示所有展览
    public PageInfo<Exhibition> findAll(int pageCode, int pageSize);
    //查询展览馆（未审核-1，线上0，线下1）
    public PageInfo<Exhibition> findAllByStatus(int pageCode, int pageSize, int status);

    //查询所有正在上线的展馆
    public List<Exhibition> findAllByStatus(int status);

    //添加展览馆
    public void addExhibition(Exhibition exhibition);
    //删除展览馆
    public void deleteExhibition(int id);
    //修改展览馆
    public void updateExhibition(Exhibition exhibition);
    //根据id查询
    public Exhibition findById(int id);
    //根据标题查询
    public List<Exhibition> findByTitle(String title);
    //修改展览馆状态（线上，线下）
    public void updateExhibitionStatus(Exhibition exhibition);

    //统计未审核的展览馆数量
    public Integer countExhibitionByStatus();

    //查询
    public PageInfo<Exhibition> findExhibition(int pageCode, int pageSize , String keyword);
    public PageInfo<Exhibition> findExhibition0(int pageCode, int pageSize , String keyword);

    public List<Exhibition> findByAll(String keyword);
}

