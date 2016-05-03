package com.bysj.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bysj.model.Content;
import com.bysj.model.Travel;
import com.bysj.model.User;

public class TravelDto {
    private long travelId;
    private String title;
    private String startTime;
    private String endTime;
    private int days;
    private Date createTime;
    private long userId;
    private String coordinate;
    private String article;
    private String location;
    private String coverimage;
    private String headImage;
    private long views;
    private int liked;//喜欢数
    private int collection;//收藏数
    private int comment;//评论数
    private String username;
    
    public void setUser(User user){
        this.headImage = user.getHeadImage();
        this.userId = user.getUserId();
        this.username = user.getNickName();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
    
    public void setContent(Content content){
        this.article = content.getArticle();
        this.location = content.getLocation();
        this.coordinate = content.getCoordinate();
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public String getCoverimage() {
        return coverimage;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public void setCoverimage(String coverimage) {
        this.coverimage = coverimage;
    }

    // private String introduction;
    // private long labelid;
    public TravelDto() {

    }

    public TravelDto(Travel travel) {
        this.createTime = travel.getCreateTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String starttime = sdf.format(travel.getStartTime());
        this.startTime = starttime;
        this.endTime = sdf.format(travel.getEndTime());
        this.userId = travel.getUserId();
        this.title = travel.getTitle();
        this.travelId = travel.getTravelId();
        this.coverimage = travel.getCoverimage();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getUserId() {
        return userId;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
