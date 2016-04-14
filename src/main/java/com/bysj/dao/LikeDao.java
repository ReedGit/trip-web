package com.bysj.dao;

import java.util.List;

import com.bysj.model.Like;

public interface LikeDao extends BaseDao<Like>{

    public boolean isLikedByUser(long userId,long travelId);
    
    public List<Like> findTravelByUser(long userId);
    
    public int getTotalByTravel(long travelId);
    
    public boolean deleteLike(long travelId,long userId);
}
