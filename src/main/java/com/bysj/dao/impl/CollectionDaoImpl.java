package com.bysj.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bysj.dao.CollectionDao;
import com.bysj.model.Collection;

@Repository(value="collectionDao")
public class CollectionDaoImpl extends BaseDaoImpl<Collection> implements CollectionDao {

    @Override
    public boolean isCollectedByUser(long userId, long travelId) {
        StringBuilder hql = new StringBuilder();
        hql.append(" from Collection c ")
           .append(" where c.userId = :userId and c.travelId = :travelId ");
        int total = getSession().createQuery(hql.toString())
                .setLong("travelId", travelId)
                .setLong("userId", userId)
                .list().size();
        return (total == 0 ? false : true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Collection> findTravelByUser(long userId) {
        StringBuilder hql = new StringBuilder();
        hql.append(" from Collection c ")
           .append(" where c.userId = :userId ");
        List<Collection> collections = getSession().createQuery(hql.toString())
                .setLong("userId", userId)
                .list();
        return collections;
    }

    @Override
    public int getTotalByTravel(long travelId) {
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from Collection c ")
           .append(" where c.travelId = :travelId ");
        Object total = getSession().createQuery(hql.toString())
                .setLong("travelId", travelId).uniqueResult();
        return Integer.parseInt(total.toString());
    }

    @Override
    public boolean deleteCollection(long travelId, long userId) {
        StringBuilder hql = new StringBuilder();
        hql.append("delete from Collection c ").append(" where c.userId = :userId and c.travelId = :travelId");
        int delete = getSession().createQuery(hql.toString())
                .setLong("travelId", travelId)
                .setLong("userId", userId)
                .executeUpdate();
        return (delete == 0 ? false : true);
    }

    @Override
    public int getTotalByUser(long userId) {
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from Collection c ")
           .append(" where c.userId = :userId ");
        Object total = getSession().createQuery(hql.toString())
                .setLong("userId", userId).uniqueResult();
        return Integer.parseInt(total.toString());
    }
    
}
