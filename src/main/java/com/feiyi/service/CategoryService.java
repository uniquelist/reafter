package com.feiyi.service;

import com.feiyi.domain.Category;

import java.util.List;

public interface CategoryService {

    public Integer countCategory();

    public List<Category> findAllCategory();
}
