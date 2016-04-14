package com.bysj.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bysj.dao.LikeDao;
import com.bysj.dao.TravelDao;
import com.bysj.model.Like;
import com.bysj.model.PageBean;
import com.bysj.model.Travel;
import com.bysj.service.LikeService;

@Service(value="likeService")
@Transactional
public class LikeServiceImpl implements LikeService{
    
    @Resource(name = "likeDao")
    private LikeDao likeDao;
    
    @Resource(name="travelDao")
    private TravelDao travelDao;
    
    @Override
    public boolean isLikedByUser(long userId, long travelId) {
        return likeDao.isLikedByUser(userId, travelId);
    }

    @Override
    public PageBean<Travel> findLikeByUser(long userId, int page, int size) {
        List<Like> likes = likeDao.findTravelByUser(userId);
        List<Long> ids = new ArrayList<>();
        if(likes != null){
            for(Like like:likes){
                ids.add(like.getTravelId());
            }
        }
        PageBean<Travel> travels = travelDao.findByPage(page, size, ids);
        return travels;
    }

    @Override
    public int getTotalByTravel(long trvaelId) {
        return likeDao.getTotalByTravel(trvaelId);
    }

    @Override
    public void saveLike(Like like) {
        likeDao.save(like);
    }

    @Override
    public boolean deleteLike(Like like) {
        return likeDao.deleteLike(like.getTravelId(), like.getUserId());
    }
    
}
