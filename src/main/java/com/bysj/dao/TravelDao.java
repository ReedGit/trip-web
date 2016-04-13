package com.bysj.dao;

import com.bysj.model.PageBean;
import com.bysj.model.Travel;

public interface TravelDao extends BaseDao<Travel>{

    public PageBean<Travel> findAllByPage(int page,int size);
    
    public PageBean<Travel> findByArea(int page,int size,String... params);
    
    public PageBean<Travel> findByTitle(int page,int size,String... params);
    
    public Travel findByTitle(String title);
    
    public boolean deleteTravel(long id);
    
}
