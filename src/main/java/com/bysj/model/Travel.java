package com.bysj.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_travel")
public class Travel {

    private long travelId;
    private String title;
    private Date startTime;
    private Date endTime;
    @Column(name="createtime")
    private Date createTime;
    private long userId;
    private String introduction;
    private String coverimage;

    public Travel(){
        
    }
    
    public Travel(Map<String, Object> map) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (map.get("travelId") != null) {
            this.travelId = Long.parseLong(map.get("travelId").toString());
        }
        if (map.get("title") != null) {
            this.title = map.get("title").toString();
        }
        this.startTime = new Date();
        this.createTime = this.startTime;

        try {
            if (map.get("endTime") != null) {
                Date endTime = sdf.parse(map.get("endTime").toString());
                this.endTime = endTime;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (map.get("userId") != null) {
            this.userId = Long.parseLong(map.get("userId").toString());
        }
        if (map.get("introduction") != null) {
            this.introduction = map.get("introduction").toString();
        }
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "travelid")
    public long getTravelId() {
        return travelId;
    }

    public void setTravelId(long travelId) {
        this.travelId = travelId;
    }
    public String getCoverimage() {
        return coverimage;
    }

    public void setCoverimage(String coverimage) {
        this.coverimage = coverimage;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "starttime")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(name = "endtime")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Column(name = "userid")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Travel [travelId=" + travelId + ", title=" + title
                + ", startTime=" + startTime + ", endTime=" + endTime
                + ", createTime=" + createTime + ", userId=" + userId
                + ", introduction=" + introduction
                + "]";
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
