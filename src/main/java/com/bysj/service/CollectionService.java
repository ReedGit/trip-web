package com.bysj.service;

import com.bysj.model.Collection;
import com.bysj.model.PageBean;
import com.bysj.model.Travel;

public interface CollectionService {

    public boolean isCollectedByUser(long travelId,long userId);
    
    public PageBean<Travel> findCollectionByUser(long userId,int page,int size);
    
    public int getTotalByTravel(long trvaelId);
    
    public void saveCollection(Collection collection);
    
    public boolean deleteCollection(Collection collection);
}
