package com.feiyi.service.Impl;

import com.feiyi.dao.OwnerDao;
import com.feiyi.domain.Pcollect;
import com.feiyi.domain.Pexhibition;
import com.feiyi.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerDao ownerDao;


    @Override
    public void addBook(Pexhibition pexhibition) {
        ownerDao.addBook(pexhibition);
    }

    @Override
    public void deleteExhibitionById(Pexhibition pexhibition) {
        ownerDao.deleteExhibitionById(pexhibition);
    }

    @Override
    public void addCollect(Pcollect pcollect) {
        ownerDao.addCollect(pcollect);
    }

    @Override
    public void deleteCollectById(Pcollect pcollect) {
        ownerDao.deleteCollectById(pcollect);
    }

    @Override
    public Integer countByEid(Integer exhibitionId) {
        return ownerDao.countByEid(exhibitionId);
    }

    @Override
    public List<Pcollect> findCollectById(Integer userId, Integer categoryId) {
        return ownerDao.findCollectById(userId,categoryId);
    }

    @Override
    public List<Pexhibition> findExhibitionById(Integer userId) {
        return ownerDao.findExhibitionById(userId);
    }
}
