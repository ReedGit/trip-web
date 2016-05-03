package com.bysj.service;

import java.util.List;

import com.bysj.dto.ContentDto;
import com.bysj.dto.TravelDto;
import com.bysj.model.PageBean;
import com.bysj.model.Travel;

public interface TravelService {

    public PageBean<TravelDto> findAllTravel(int page,int size);
    
    public PageBean<TravelDto> findByArea(int page,int size,String...objects);
    
    public PageBean<TravelDto> findByTitle(int page,int size,String...objects);
    
    public Travel saveTravel(Travel travel);
    
    public Travel findByTitle(String title);
    
    public boolean deleteTravel(long id);
    
    public List<ContentDto> detail(long travelId);
    
    public PageBean<TravelDto> findByUserId(long userId,int page,int size);
    
}
