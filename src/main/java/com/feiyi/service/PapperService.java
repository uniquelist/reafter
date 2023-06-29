package com.feiyi.service;

import com.feiyi.domain.Papper;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PapperService {
    //查询文章所有列表
    public List<Papper> findAll();
    public PageInfo<Papper> findAll(int currentPage, int size);
    //添加文章
    public void addPapper(Papper papper);
    //删除文章
    public void deleteById(int papperId);
    //根据输入内容查询
    public PageInfo<Papper> searchPapper(int pageCode, int pageSize,String keyword);
}
