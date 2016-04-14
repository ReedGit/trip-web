package com.bysj.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bysj.model.Like;
import com.bysj.model.PageBean;
import com.bysj.model.Travel;
import com.bysj.service.LikeService;
import com.bysj.utils.Constants;

/**
 * 
* <p>Title: LikeController</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2016</p>
* @author zerolu
* @date 2016年4月14日
* @version 1.0
 */
@Controller
@RequestMapping("/like")
public class LikeController {

    @Resource(name="likeService")
    private LikeService likeService;
    
    @RequestMapping(value="/isLike",method=RequestMethod.GET)
    @ResponseBody
    public JSONObject isLiked(@RequestParam Map<String, Object> map){
        JSONObject result = new JSONObject();
        long travelId = 0;
        if(map.containsKey("travelId"))
           travelId  = Long.parseLong(map.get("travelId").toString());
        long userId = 0;
        if(map.containsKey("userId"))
            userId  = Long.parseLong(map.get("userId").toString());
        boolean isCollect = likeService.isLikedByUser(travelId, userId);
        if(isCollect){
            result.put("status", "0");
            result.put("msg", "");
            result.put("data", Constants.IS_LIKED);
        }else{
            result.put("status", "0");
            result.put("msg", "");
            result.put("data", Constants.NOT_LIKED);
        }
        return result;
    }
    
    @RequestMapping(value="/travels",method=RequestMethod.GET)
    @ResponseBody
    public JSONObject getTravels(@RequestParam Map<String, Object> map){
        JSONObject result = new JSONObject();
        long userId = 0;
        if(map.containsKey("userId"))
            userId  = Long.parseLong(map.get("userId").toString());
        int page = 1;
        if(map.containsKey("page"))
            page  = Integer.parseInt(map.get("page").toString());
        PageBean<Travel> travels = likeService.findLikeByUser(userId, page, Constants.PAGE_SIZE);
        result.put("status", "0");
        result.put("size", travels.getTotal());
        result.put("msg", "");
        result.put("data", travels.getList());
        return result;
    }
    
    @RequestMapping(value="/users",method=RequestMethod.GET)
    @ResponseBody
    public JSONObject getUsers(@RequestParam Map<String, Object> map){
        JSONObject result = new JSONObject();
        long travelId = 0;
        if(map.containsKey("travelId"))
           travelId  = Long.parseLong(map.get("travelId").toString());
        int total = likeService.getTotalByTravel(travelId);
        result.put("status", "0");
        result.put("size", total);
        result.put("msg", "");
        return result;
    }
    
    @RequestMapping(value="/like",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject collect(@RequestParam Map<String, Object> map){
        JSONObject result = new JSONObject();
        long travelId = 0;
        if(map.containsKey("travelId"))
           travelId  = Long.parseLong(map.get("travelId").toString());
        long userId = 0;
        if(map.containsKey("userId"))
            userId  = Long.parseLong(map.get("userId").toString());
        Like like = new Like();
        like.setTravelId(travelId);
        like.setUserId(userId);
        likeService.saveLike(like);
        result.put("status", "0");
        result.put("msg", "点赞成功！");
        return result;
    }
    
    @RequestMapping(value="/cancel",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject cancel(@RequestParam Map<String, Object> map){
        JSONObject result = new JSONObject();
        long travelId = 0;
        if(map.containsKey("travelId"))
           travelId  = Long.parseLong(map.get("travelId").toString());
        long userId = 0;
        if(map.containsKey("userId"))
            userId  = Long.parseLong(map.get("userId").toString());
        Like like = new Like();
        like.setTravelId(travelId);
        like.setUserId(userId);
        boolean bool = likeService.deleteLike(like);
        if (bool) {
            result.put("status", "0");
            result.put("msg", "取消赞成功！");
        }
        return result;
    }
}
