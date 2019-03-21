package com.mumu.zhkuconstructparty.biz.autoCode.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserScore implements Serializable {
    private Integer userId;

    private Integer score;

    private String name;

    private Date lastModData;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getLastModData() {
        return lastModData;
    }

    public void setLastModData(Date lastModData) {
        this.lastModData = lastModData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", score=").append(score);
        sb.append(", name=").append(name);
        sb.append(", lastModData=").append(lastModData);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}