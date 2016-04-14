package com.bysj.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bysj.dao.ContentDao;
import com.bysj.model.Content;

@Repository(value="contentDao")
public class ContentDaoImpl extends BaseDaoImpl<Content> implements ContentDao {

    @Override
    public boolean deleteContentById(List<Long> ids) {
        StringBuilder hql = new StringBuilder();
        hql.append("delete from Content c ").append(" where c.contentId in :ids");
        int delete = getSession().createQuery(hql.toString())
                .setParameterList("ids", ids).executeUpdate();
        return (delete == 0 ? false : true);
    }

    @Override
    public boolean deleteContentImageById(List<Long> contentIds) {
        StringBuilder hql = new StringBuilder();
        hql.append("delete from ContentImage c ").append(" where c.contentId in :ids");
        int delete = getSession().createQuery(hql.toString())
                .setParameterList("ids", contentIds).executeUpdate();
        return (delete == 0 ? false : true);
    }
    
    @SuppressWarnings("unchecked")
    public List<Content> findByTravelId(long travelId){
        StringBuilder hql = new StringBuilder();
        hql.append(" from Content c ").append(" where c.travelId = :id");
        List<Content> contents = getSession().createQuery(hql.toString())
                .setLong("id", travelId).list();
        return contents;
    }
    
}
