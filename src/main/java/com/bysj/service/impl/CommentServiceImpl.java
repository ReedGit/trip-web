package com.bysj.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bysj.dao.CommentDao;
import com.bysj.dao.UserDao;
import com.bysj.dto.CommentDto;
import com.bysj.model.Comment;
import com.bysj.model.PageBean;
import com.bysj.model.User;
import com.bysj.service.CommentService;

@Service(value="commentService")
@Transactional
public class CommentServiceImpl implements CommentService{
    
    @Resource(name="commentDao")
    private CommentDao commentDao;
    
    @Resource(name="userDao")
    private UserDao userDao;

    @Override
    public Comment saveComment(Comment comment) {
        return commentDao.save(comment);
    }
    @Override
    public int getTotalByTravel(long travelId) {
        return commentDao.getTotal(travelId);
    }
    @Override
    public boolean deleteComment(long commentId) {
        return commentDao.deleteComment(commentId);
    }

    @Override
    public PageBean<CommentDto> findCommentByPage(int page, int size,
            long travelId) {
        PageBean<CommentDto> pageBean = new PageBean<>(page, size);
        List<CommentDto> commentDtos = new ArrayList<>();
        PageBean<Comment> comments = commentDao.findCommentByPage(page, size, travelId);
        for(Comment comment:comments.getList()){
            User user = userDao.findById(comment.getUserId());
            CommentDto commentDto = new CommentDto();
            commentDto.setComment(comment);
            commentDto.setUser(user);
            commentDtos.add(commentDto);
        }
        pageBean.setTotal(comments.getTotal());
        pageBean.setList(commentDtos);
        return pageBean;
    }
    
}
