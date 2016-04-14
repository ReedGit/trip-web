package com.bysj.dao;

import java.util.List;

import com.bysj.model.ContentImage;

public interface ContentImageDao extends BaseDao<ContentImage>{
    public List<ContentImage> findByContentId(long contentId);
}
