package com.feiyi.dao;

import com.feiyi.domain.Exhibition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExhibitionDao {
    //显示所有展览
    public List<Exhibition> findAll();
    //查询展览馆（未审核-1，线上0，线下1）
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
    public List<Exhibition> findExhibition(String keyword);
    public List<Exhibition> findExhibition0(String keyword);
    public List<Exhibition> findByAll(String keyword);


}
