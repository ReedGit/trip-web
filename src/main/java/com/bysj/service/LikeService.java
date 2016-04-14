package com.bysj.service;

import com.bysj.model.Like;
import com.bysj.model.PageBean;
import com.bysj.model.Travel;

public interface LikeService {
    public boolean isLikedByUser(long userId,long travelId);
    
    public PageBean<Travel> findLikeByUser(long userId,int page,int size);
    
    public int getTotalByTravel(long trvaelId);
    
    public void saveLike(Like like);
    
    public boolean deleteLike(Like like);
}
