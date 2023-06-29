package com.feiyi.service.Impl;

import com.feiyi.dao.CategoryDao;
import com.feiyi.domain.Category;
import com.feiyi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Integer countCategory() {
        return categoryDao.countCategory();
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryDao.findAllCategory();
    }
}
