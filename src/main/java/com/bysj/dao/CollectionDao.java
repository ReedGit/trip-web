package com.bysj.dao;

import java.util.List;

import com.bysj.model.Collection;

public interface CollectionDao extends BaseDao<Collection>{

    public boolean isCollectedByUser(long userId,long travelId);
    
    public List<Collection> findTravelByUser(long userId);
    
    public int getTotalByTravel(long travelId);
    
    public boolean deleteCollection(long travelId,long userId);
    
    public int getTotalByUser(long userId);
}
