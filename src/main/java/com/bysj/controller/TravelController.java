package com.bysj.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.bysj.dto.ContentDto;
import com.bysj.dto.TravelDto;
import com.bysj.model.Content;
import com.bysj.model.PageBean;
import com.bysj.model.Travel;
import com.bysj.model.User;
import com.bysj.service.CollectionService;
import com.bysj.service.CommentService;
import com.bysj.service.ContentService;
import com.bysj.service.LikeService;
import com.bysj.service.TravelService;
import com.bysj.service.UserService;
import com.bysj.utils.Constants;

/**
 * 
 * <p>Title: TravelController</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * @author zerolu
 * @date 2016年3月25日
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/travel")
public class TravelController {

    @Resource(name = "travelService")
    private TravelService travelService;
    
    @Resource(name = "userService")
    private UserService userService;
    
    @Resource(name = "contentService")
    private ContentService contentService;
    
    @Resource(name = "likeService")
    private LikeService likeService;
    
    @Resource(name = "commentService")
    private CommentService commentService;
    
    @Resource(name = "collectionService")
    private CollectionService collectionService;
    
    @RequestMapping(value = "/total/{travelId}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject total(@PathVariable long travelId
            ,@RequestParam Map<String, Object> map) {
        JSONObject result = new JSONObject();
        if (map.containsKey("userId")){
            Long userId = Long.parseLong(map.get("userId").toString());
            boolean islike = likeService.isLikedByUser(userId, travelId);
            boolean iscollection = collectionService.isCollectedByUser(travelId, userId);
            JSONObject addition = new JSONObject();
            addition.put("isLike", islike);
            addition.put("isCollect", iscollection);
            result.put("addition", addition);
        }
        int comments = commentService.getTotalByTravel(travelId);
        int likes = likeService.getTotalByTravel(travelId);
        int collections = collectionService.getTotalByTravel(travelId);
        JSONObject object = new JSONObject();
        object.put("comments", comments);
        object.put("likes", likes);
        object.put("collections", collections);
        
        result.put("status", "0");
        result.put("data", object);
        return result;
    }
    
    @RequestMapping(value = "/travels/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getUserTravels(@PathVariable long userId,@RequestParam Map<String, Object> map) {
        JSONObject result = new JSONObject();
        int page = 1;
        if (map.containsKey("page"))
            page = Integer.parseInt(map.get("page").toString());
        PageBean<TravelDto> travels = travelService.findByUserId(userId, page, Constants.PAGE_SIZE);
        result.put("status", "0");
        result.put("size", travels.getTotal());
        result.put("data", travels.getList());
        return result;
    }

    @RequestMapping(value = "/explore", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getAllList(@RequestParam Map<String, Object> map) {
        JSONObject result = new JSONObject();
        int page = 1;
        if (map.containsKey("page"))
            page = Integer.parseInt(map.get("page").toString());
        PageBean<Travel> travels = travelService.findAllTravel(page,
                Constants.PAGE_SIZE);
        result.put("status", "0");
        result.put("size", travels.getTotal());
        result.put("data", travels.getList());
        return result;
    }

    @RequestMapping(value = { "/search", "/city" }, method = RequestMethod.GET)
    @ResponseBody
    public JSONObject searchCity(@RequestParam Map<String, Object> map) {
        JSONObject result = new JSONObject();
        String query = "";
        if (map.containsKey("q")) {
            query = map.get("q").toString();
        }
        String[] params = query.split(" ");
        int page = 1;
        if (map.containsKey("page"))
            page = Integer.parseInt(map.get("page").toString());
        PageBean<Travel> pageBean = travelService.findByArea(page,
                Constants.PAGE_SIZE, params);
        result.put("status", "0");
        result.put("size", pageBean.getTotal());
        result.put("data", pageBean.getList());
        return result;
    }

    @RequestMapping(value = { "/title" }, method = RequestMethod.GET)
    @ResponseBody
    public JSONObject searchTitle(@RequestParam Map<String, Object> map) {
        JSONObject result = new JSONObject();
        String query = "";
        if (map.containsKey("q")) {
            query = map.get("q").toString();
        }
        String[] params = query.split(" ");
        int page = 1;
        if (map.containsKey("page"))
            page = Integer.parseInt(map.get("page").toString());
        PageBean<Travel> pageBean = travelService.findByArea(page,
                Constants.PAGE_SIZE, params);
        result.put("status", "0");
        result.put("size", pageBean.getTotal());
        result.put("data", pageBean.getList());
        return result;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject createTravel(@RequestParam Map<String, Object> map,
            @RequestParam(value = "coverimage", required = false) MultipartFile file,
            HttpServletRequest request)
            throws ParseException {
        JSONObject result = new JSONObject();
        Travel travel = new Travel(map);
        if (file != null && !file.isEmpty() && file.getSize() != 0) {
            String fileParentPath = request.getSession().getServletContext().getRealPath("/");
            String coverimage = userService.saveImage(new Random().nextLong(), file, fileParentPath);
            travel.setCoverimage(coverimage);
        }
        Travel saveTravel = travelService.saveTravel(travel);
        result.put("status", "0");
        result.put("msg", "游记创建成功！");
        result.put("data", saveTravel);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteTravel(@RequestParam Map<String, Object> map) {
        JSONObject result = new JSONObject();
        String token = null;
        if (!map.containsKey("token")) {
            result.put("status", "1");
            result.put("msg", "非法修改！");
            return result;
        }
        token = map.get("token").toString();
        long id = Long.parseLong(map.get("userId").toString());
        long travelId = Long.parseLong(map.get("travelId").toString());
        User user = userService.findByIdAndToken(id, token);
        if (user != null) {
            List<Content> contents = contentService.findListById(travelId);
            List<Long> ids = new ArrayList<Long>();
            for(Content content:contents){
                ids.add(content.getContentId());
            }
            boolean bool = true;
            if(!travelService.deleteTravel(travelId))
                bool = false;
            if(!contentService.deleteContent(ids))
                bool = false;
            if(bool){
                result.put("status", "0");
                result.put("msg", "删除成功！");
            }
        }
        return result;
    }
    
    @RequestMapping(value="/content/create",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject createContent(@RequestParam Map<String, Object> map,
            @RequestParam(value = "image", required = false) MultipartFile[] files,
            HttpServletRequest request) throws ParseException{
        JSONObject result = new JSONObject();
        long travelId = Long.parseLong(map.get("travelId").toString());
        String article = null;
        if(map.containsKey("article")){
            article = map.get("article").toString();
        }
        String location = null;
        if(map.containsKey("location")){
            location = map.get("location").toString();
        }
        String coordinate = null;
        if(map.containsKey("coordinate")){
            coordinate = map.get("coordinate").toString();
        }
        int day = 1;
        if(map.containsKey("day")){
            day = Integer.parseInt(map.get("day").toString());
        }
        Content content = new Content();
        content.setArticle(article);
        content.setTravelId(travelId);
        content.setDay(day);
        content.setLocation(location);
        content.setCoordinate(coordinate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        content.setCreatetime(sdf.parse(sdf.format(new Date())));
        Content contentAfter = contentService.saveContent(content);
        contentService.saveImage(contentAfter.getContentId(),files, request);
        result.put("status", "0");
        result.put("msg", "游记详情创建成功！");
        result.put("content", contentAfter);
        return result;
    }
    
    @RequestMapping(value = "/content/addImage", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject uploadImage(
            @RequestParam Map<String, Object> map,
            @RequestParam(value = "image", required = false) MultipartFile[] files,
            HttpServletRequest request) {
        Long contentId = Long.parseLong(map.get("contentId").toString());
        JSONObject result = contentService.saveImage(contentId,files, request);
        return result;
    }
    
    @RequestMapping(value="/content/detail",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject detail(@RequestParam Map<String, Object> map){
        JSONObject result = new JSONObject();
        long travelId = Long.parseLong(map.get("travelId").toString());
        List<ContentDto> contentDtos = travelService.detail(travelId);
        result.put("status", "0");
        result.put("msg", "");
        result.put("content", contentDtos);
        return result;
    }
    
}
