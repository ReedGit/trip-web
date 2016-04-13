package com.bysj.dao.impl;

import org.springframework.stereotype.Repository;

import com.bysj.dao.ContentImageDao;
import com.bysj.model.ContentImage;

@Repository(value = "contentImageDao")
public class ContentImageDaoImpl extends BaseDaoImpl<ContentImage>
        implements ContentImageDao {

}
