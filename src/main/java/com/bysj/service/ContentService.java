package com.bysj.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.bysj.model.Content;
import com.bysj.model.ContentImage;

public interface ContentService {

    public Content saveContent(Content content);
    
    public boolean deleteContent(List<Long> contentIds);
    
    public List<Content> findListById(long travelId);
    
    public Content findById(long contentId);
    
    public ContentImage save(ContentImage contentImage);
    
    public JSONObject saveImage(Long id , MultipartFile[] files,HttpServletRequest request);
}
