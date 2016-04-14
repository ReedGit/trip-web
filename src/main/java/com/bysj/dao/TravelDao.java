package com.bysj.dao;

import java.util.List;

import com.bysj.model.PageBean;
import com.bysj.model.Travel;

public interface TravelDao extends BaseDao<Travel>{

    public PageBean<Travel> findAllByPage(int page,int size);
    
    public PageBean<Travel> findByArea(int page,int size,String... params);
    
    public PageBean<Travel> findByTitle(int page,int size,String... params);
    
    public Travel findByTitle(String title);
    
    public boolean deleteTravel(long id);
    
    public PageBean<Travel> findByPage(int page,int size,List<Long> ids);
    
    public int getTotalByUser(long userId);
    
}
