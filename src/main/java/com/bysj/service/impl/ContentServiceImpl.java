package com.bysj.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.bysj.dao.ContentDao;
import com.bysj.dao.ContentImageDao;
import com.bysj.model.Content;
import com.bysj.model.ContentImage;
import com.bysj.service.ContentService;

@Service(value="contentService")
public class ContentServiceImpl implements ContentService{
    
    @Resource(name="contentDao")
    private ContentDao contentDao;
    
    @Resource(name="contentImageDao")
    private ContentImageDao contentImageDao;

    @Override
    public Content saveContent(Content content) {
        return contentDao.save(content);
    }
    
    @Override
    public boolean deleteContent(List<Long> contentIds) {
        boolean bool = true;
        if (!contentDao.deleteContentById(contentIds)) {
            bool = false;
        }
        if (!contentDao.deleteContentImageById(contentIds)) {
            bool = false;
        }
        return bool;
    }

    @Override
    public List<Content> findListById(long travelId) {
        return contentDao.findByTravelId(travelId);
    }

    @Override
    public JSONObject saveImage(Long id , MultipartFile[] files, HttpServletRequest request) {
        JSONObject result = new JSONObject();
        String fileParentDirPath = request.getSession().getServletContext().getRealPath("/");
        List<ContentImage> contentImages = new ArrayList<>();
        for(MultipartFile file:files){
            String fileOriginName = file.getOriginalFilename();
            if (fileOriginName.endsWith(".jpg")
                    || fileOriginName.endsWith(".png")
                    || fileOriginName.endsWith(".JPG")
                    || fileOriginName.endsWith(".PNG")
                    || fileOriginName.endsWith(".gif")
                    || fileOriginName.endsWith(".GIF")) {
                StringBuilder fileSavePath = new StringBuilder();
                StringBuilder fileSubDirPath = new StringBuilder()
                        .append("\\upload\\")
                        .append("content\\")
                        .append(id);
                File fileDir = new File(fileParentDirPath + fileSubDirPath.toString());
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                StringBuilder fileName = new StringBuilder()
                        .append(UUID.randomUUID().toString())
                        .append(fileOriginName.substring(fileOriginName.lastIndexOf(".")));
                
                fileSavePath.append(fileParentDirPath)
                            .append(fileSubDirPath)
                            .append("\\")
                            .append(fileName);
                
                File fileSave = new File(fileSavePath.toString());
                
                try {
                    file.transferTo(fileSave);
                    String imagePath = fileSubDirPath.toString() + "\\" + fileName;
                    imagePath = imagePath.replaceAll("\\\\", "/");
                    ContentImage contentImage = new ContentImage();
                    contentImage.setContentId(id);
                    contentImage.setImageUrl(imagePath);
                    contentImages.add(this.save(contentImage));
                } catch (IllegalStateException e) {
                    result.put("status", "1");
                    result.put("msg", e.getMessage());
                } catch (IOException e) {
                    result.put("status", "1");
                    result.put("msg", e.getMessage());
                }
            } else {
                result.put("status", "1");
                result.put("msg", "图片格式只支持jpg/png/gif!");
            }
        }
        result.put("status", "0");
        result.put("msg", "图片上传成功！");
        result.put("data", contentImages);
        return result;
    }

    @Override
    public Content findById(long contentId) {
        return contentDao.findById(contentId);
    }

    @Override
    public ContentImage save(ContentImage contentImage) {
        return contentImageDao.save(contentImage);
    }
    
}
