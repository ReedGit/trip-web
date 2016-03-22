package com.bysj.dto;

import com.bysj.model.User;

public class UserDto {

    private long userId;
    private String email;
    private String nickName;
    private String headImage;
    private int sex;//1表示男，0表示女
    private String introduction;
    private String token;
    
    public UserDto(User user){
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.nickName = user.getNickName();
        this.headImage = user.getHeadImage();
        this.introduction = user.getIntroduction();
        this.sex = user.getSex();
        this.token = user.getToken();
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getHeadImage() {
        return headImage;
    }
    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }
    public int getSex() {
        return sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getIntroduction() {
        return introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
