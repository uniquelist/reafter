package com.feiyi.dao;

import com.feiyi.domain.Pcollect;
import com.feiyi.domain.Pexhibition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OwnerDao {
    //预约展馆
    void addBook(Pexhibition pexhibition);
    //取消预约
    void deleteExhibitionById(Pexhibition pexhibition);

    //收藏作品
    void addCollect( Pcollect pcollect);
    //取消收藏
    void deleteCollectById(Pcollect pcollect);

    //根据id统计该展馆已经有多少人预约了
    Integer countByEid(Integer exhibitionId);

    //根据用户id以及作品类型id查看该用户收藏的作品
    List<Pcollect> findCollectById(Integer userId, Integer categoryId);

    //根据用户id查看用户预定的展馆
    List<Pexhibition> findExhibitionById(Integer userId);
}
