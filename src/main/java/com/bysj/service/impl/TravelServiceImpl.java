package com.bysj.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bysj.dao.TravelDao;
import com.bysj.model.PageBean;
import com.bysj.model.Travel;
import com.bysj.service.TravelService;

@Service(value = "travelService")
@Transactional
public class TravelServiceImpl implements TravelService{
    
    @Resource(name = "travelDao")
    TravelDao travelDao;

    @Override
    public PageBean<Travel> findAllTravel(int page, int size) {
        return travelDao.findAllByPage(page, size);
    }

    @Override
    public PageBean<Travel> findByArea(int page, int size, String... objects) {
        return travelDao.findByArea(page, size, objects);
    }

    @Override
    public PageBean<Travel> findByTitle(int page, int size, String... objects) {
        return travelDao.findByTitle(page, size, objects);
    }

    @Override
    public Travel saveTravel(Travel travel) {
        return travelDao.save(travel);
    }

    @Override
    public Travel findByTitle(String title) {
        return travelDao.findByTitle(title);
    }
    
    public boolean deleteTravel(long id){
        return travelDao.deleteTravel(id);
    }
}
