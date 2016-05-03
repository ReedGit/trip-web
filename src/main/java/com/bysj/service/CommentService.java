package com.bysj.service;

import com.bysj.dto.CommentDto;
import com.bysj.model.Comment;
import com.bysj.model.PageBean;

public interface CommentService {
    public Comment saveComment(Comment comment);
    public boolean deleteComment(long commentId);
    public PageBean<CommentDto> findCommentByPage(int page,int size,long travelId);
    public int getTotalByTravel(long travelId);
}
