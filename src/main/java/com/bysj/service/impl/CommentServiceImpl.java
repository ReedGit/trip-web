package com.bysj.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bysj.dao.CommentDao;
import com.bysj.model.Comment;
import com.bysj.model.PageBean;
import com.bysj.service.CommentService;

@Service(value="commentService")
@Transactional
public class CommentServiceImpl implements CommentService{
    
    @Resource(name="commentDao")
    private CommentDao commentDao;

    @Override
    public Comment saveComment(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public boolean deleteComment(long commentId) {
        return commentDao.deleteComment(commentId);
    }

    @Override
    public PageBean<Comment> findCommentByPage(int page, int size,
            long travelId) {
        return commentDao.findCommentByPage(page, size, travelId);
    }
    
}
