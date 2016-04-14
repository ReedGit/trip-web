package com.bysj.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bysj.dao.ContentImageDao;
import com.bysj.model.ContentImage;

@Repository(value = "contentImageDao")
public class ContentImageDaoImpl extends BaseDaoImpl<ContentImage>
        implements ContentImageDao {

    @Override
    public List<ContentImage> findByContentId(long contentId) {
        StringBuilder hql = new StringBuilder();
        hql.append(" from ContentImage c ").append(" where c.contentId = :id");
        @SuppressWarnings("unchecked")
        List<ContentImage> contents = getSession().createQuery(hql.toString())
                .setLong("id", contentId).list();
        return contents;
    }

}
