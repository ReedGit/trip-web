package com.bysj.dao;

import com.bysj.model.Comment;
import com.bysj.model.PageBean;

public interface CommentDao extends BaseDao<Comment>{
    
    public boolean deleteComment(long commentId);
    public PageBean<Comment> findCommentByPage(int page,int size,long travelId);
    public int getTotal(int page, int size,long travelId);
}
