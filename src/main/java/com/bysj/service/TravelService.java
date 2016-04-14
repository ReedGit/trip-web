package com.bysj.service;

import java.util.List;

import com.bysj.dto.ContentDto;
import com.bysj.model.PageBean;
import com.bysj.model.Travel;

public interface TravelService {

    public PageBean<Travel> findAllTravel(int page,int size);
    
    public PageBean<Travel> findByArea(int page,int size,String...objects);
    
    public PageBean<Travel> findByTitle(int page,int size,String...objects);
    
    public Travel saveTravel(Travel travel);
    
    public Travel findByTitle(String title);
    
    public boolean deleteTravel(long id);
    
    public List<ContentDto> detail(long travelId);
    
}
