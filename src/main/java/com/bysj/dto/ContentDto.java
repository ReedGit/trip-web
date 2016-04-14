package com.bysj.dto;

import java.util.List;

import com.bysj.model.Content;

public class ContentDto {
    
    private long contentId;
    private String article;
    private String location;
    private long travelId;
    private String coordinate;
    private int day;
    private List<String> imageurl;
    
    public void setContent(Content content){
        this.contentId = content.getContentId();
        this.travelId = content.getTravelId();
        this.article = content.getArticle();
        this.location = content.getLocation();
        this.coordinate = content.getCoordinate();
        this.day = content.getDay();
    }
    public long getContentId() {
        return contentId;
    }
    public void setContentId(long contentId) {
        this.contentId = contentId;
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
    public long getTravelId() {
        return travelId;
    }
    public void setTravelId(long travelId) {
        this.travelId = travelId;
    }
    public String getCoordinate() {
        return coordinate;
    }
    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public List<String> getImageurl() {
        return imageurl;
    }
    public void setImageurl(List<String> imageurl) {
        this.imageurl = imageurl;
    }
    
}
