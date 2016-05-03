package com.bysj.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bysj.dao.CommentDao;
import com.bysj.model.Comment;
import com.bysj.model.PageBean;

@Repository(value="commentDao")
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao {

    @Override
    public boolean deleteComment(long commentId) {
        StringBuilder hql = new StringBuilder();
        hql.append("delete from Comment c ").append(" where c.commentId = :commentId ");
        int delete = getSession().createQuery(hql.toString())
                .setLong("commentId", commentId)
                .executeUpdate();
        return (delete == 0 ? false : true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageBean<Comment> findCommentByPage(int page, int size,
            long travelId) {
        PageBean<Comment> pageBean = new PageBean<>(page, size);
        StringBuilder hql = new StringBuilder();
        hql.append("from Comment c ")
           .append(" where c.travelId = :travelId ")
           .append(" order by c.time desc ");
        
        List<Comment> comments = getSession().createQuery(hql.toString())
        		.setLong("travelId", travelId)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .list();
        pageBean.setList(comments);
        pageBean.setTotal(getTotal(travelId));
        return pageBean;
    }
    
    @Override
    public int getTotal(long travelId) {
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from Comment c ")
           .append(" where c.travelId = :travelId ");
        Object total = getSession().createQuery(hql.toString())
                .setLong("travelId", travelId).uniqueResult();
        return Integer.parseInt(total.toString());
    }

    @Override
    public int getTotalByUser(long userId) {
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from Comment c ")
           .append(" where c.userId = :userId ");
        Object total = getSession().createQuery(hql.toString())
                .setLong("userId", userId).uniqueResult();
        return Integer.parseInt(total.toString());
    }
}
