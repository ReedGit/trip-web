package com.bysj.dto;

import java.util.Date;

import com.bysj.model.Travel;
import com.bysj.model.User;

public class TravelDto {
	private long travelId;
	private String title;
	private Date startTime;
	private Date endTime;
	private int days;
	private Date createTime;
	private long userId;
	private String coverImage;
	private String headImage;
	private int liked;// 喜欢数
	private int collection;// 收藏数
	private int comment;// 评论数
	private String nickname;
	private String introduction;

	public void setUser(User user) {
		this.headImage = user.getHeadImage();
		this.userId = user.getUserId();
		this.nickname = user.getNickName();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
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

	public String getCoverImage() {
		return coverImage;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public void setCoveriImage(String coverImage) {
		this.coverImage = coverImage;
	}

	// private String introduction;
	// private long labelid;
	public TravelDto() {

	}

	public TravelDto(Travel travel) {
		this.createTime = travel.getCreateTime();
		this.startTime = travel.getStartTime();
		this.endTime = travel.getEndTime();
		this.userId = travel.getUserId();
		this.title = travel.getTitle();
		this.travelId = travel.getTravelId();
		this.coverImage = travel.getCoverimage();
		this.introduction = travel.getIntroduction();
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
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

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	

}
