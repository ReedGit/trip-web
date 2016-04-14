package com.bysj.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bysj.dao.LikeDao;
import com.bysj.model.Like;

@Repository(value = "likeDao")
public class LikeDaoImpl extends BaseDaoImpl<Like>
        implements LikeDao {

    @Override
    public boolean isLikedByUser(long userId, long travelId) {
        StringBuilder hql = new StringBuilder();
        hql.append(" from Like c ")
           .append(" where c.userId = :userId and c.travelId = :travelId ");
        int total = getSession().createQuery(hql.toString())
                .setLong("travelId", travelId)
                .setLong("userId", userId)
                .list().size();
        return (total == 0 ? false : true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Like> findTravelByUser(long userId) {
        StringBuilder hql = new StringBuilder();
        hql.append(" from Like c ")
           .append(" where c.userId = :userId ");
        List<Like> likes = getSession().createQuery(hql.toString())
                .setLong("userId", userId)
                .list();
        return likes;
    }

    @Override
    public int getTotalByTravel(long travelId) {
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from Like c ")
           .append(" where c.travelId = :travelId ");
        Object total = getSession().createQuery(hql.toString())
                .setLong("travelId", travelId).uniqueResult();
        return Integer.parseInt(total.toString());
    }

    @Override
    public boolean deleteLike(long travelId, long userId) {
        StringBuilder hql = new StringBuilder();
        hql.append("delete from Like c ").append(" where c.userId = :userId and c.travelId = :travelId");
        int delete = getSession().createQuery(hql.toString())
                .setLong("travelId", travelId)
                .setLong("userId", userId)
                .executeUpdate();
        return (delete == 0 ? false : true);
    }

    @Override
    public int getTotalByUser(long userId) {
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from Like c ")
           .append(" where c.userId = :userId ");
        Object total = getSession().createQuery(hql.toString())
                .setLong("userId", userId).uniqueResult();
        return Integer.parseInt(total.toString());
    }

}
