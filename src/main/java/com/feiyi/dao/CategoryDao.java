package com.feiyi.dao;

import com.feiyi.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryDao {
    public Integer countCategory();

    public List<Category> findAllCategory();
}
