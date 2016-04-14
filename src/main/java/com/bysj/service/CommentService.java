package com.bysj.service;

import com.bysj.model.Comment;
import com.bysj.model.PageBean;

public interface CommentService {
    public Comment saveComment(Comment comment);
    public boolean deleteComment(long commentId);
    public PageBean<Comment> findCommentByPage(int page,int size,long travelId);
}
