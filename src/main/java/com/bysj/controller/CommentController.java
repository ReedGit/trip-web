package com.bysj.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bysj.dto.CommentDto;
import com.bysj.model.Comment;
import com.bysj.model.PageBean;
import com.bysj.service.CommentService;
import com.bysj.utils.Constants;

/**
 * 
* <p>Title: CommentController</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2016</p>
* @author zerolu
* @date 2016年4月14日
* @version 1.0
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource(name = "commentService")
    private CommentService commentService;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject addComment(@RequestParam Map<String, Object> map) throws ParseException{
        JSONObject result = new JSONObject();
        long userId = 0;
        if (map.containsKey("userId")) {
            userId = Long.parseLong(map.get("userId").toString());
        }
        long travelId = 0;
        if (map.containsKey("travelId")) {
            travelId = Long.parseLong(map.get("travelId").toString());
        }
        String remark = null;
        if (map.containsKey("remark")) {
            remark = map.get("remark").toString();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        Date createDate = sdf.parse(sdf.format(currentDate));
        Comment comment = new Comment(userId, createDate, remark, travelId);
        Comment saveComment = commentService.saveComment(comment);
        result.put("status", Constants.SUCCESS);
        result.put("msg", "评论成功！");
        result.put("data", saveComment);
        return result;
    }
    
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteComment(@RequestParam Map<String, Object> map) {
        JSONObject result = new JSONObject();
        long commentId = 0;
        if (map.containsKey("commentId")) {
            commentId = Long.parseLong(map.get("commentId").toString());
        }
        if (commentService.deleteComment(commentId)) {
            result.put("status", Constants.SUCCESS);
            result.put("msg", "评论删除成功！");   
        }
        return result;
    }
    
    @RequestMapping(value="/list",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject getComment(@RequestParam Map<String, Object> map) {
        JSONObject result = new JSONObject();
        long travelId = 0;
        if (map.containsKey("travelId")) {
            travelId = Long.parseLong(map.get("travelId").toString());
        }
        int page = 1;
        if (map.containsKey("page")) {
            page = Integer.parseInt(map.get("page").toString());
        }
        PageBean<CommentDto> pageBean = commentService
                .findCommentByPage(page, Constants.PAGE_SIZE, travelId);
        result.put("status", Constants.SUCCESS);
        result.put("msg", "");
        result.put("size", pageBean.getTotal()); 
        result.put("data", pageBean.getList());
        return result;
    }
}
