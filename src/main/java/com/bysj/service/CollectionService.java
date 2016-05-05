package com.bysj.service;

import com.bysj.dto.TravelDto;
import com.bysj.model.Collection;
import com.bysj.model.PageBean;

public interface CollectionService {

    public boolean isCollectedByUser(long travelId,long userId);
    
    public PageBean<TravelDto> findCollectionByUser(long userId,int page,int size);
    
    public int getTotalByTravel(long trvaelId);
    
    public void saveCollection(Collection collection);
    
    public boolean deleteCollection(Collection collection);
}
