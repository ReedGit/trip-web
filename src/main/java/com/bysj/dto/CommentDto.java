package com.bysj.dto;

import com.bysj.model.Comment;
import com.bysj.model.Travel;
import com.bysj.model.User;

public class CommentDto {

    private long commentId;
    private long userId;
    private String time;
    private String remark;
    private String headImage;
    private String nickName;
    private String title;
    private long travelId;

    public void setUser(User user) {
        this.headImage = user.getHeadImage();
        this.nickName = user.getNickName();
    }

    public void setComment(Comment comment) {
        this.commentId = comment.getCommentId();
        this.userId = comment.getUserId();
        this.remark = comment.getRemark();
    }
    
    public void setTravel(Travel travel){
        this.title = travel.getTitle();
        this.travelId = travel.getTravelId();
    }

    public long getTravelId() {
        return travelId;
    }

    public void setTravelId(long travelId) {
        this.travelId = travelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
