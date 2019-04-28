package com.mumu.zhkuconstructparty.dto.ScoreDto;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.UserScore;

public class UserScoreDto extends UserScore {
    String keyword;
    Integer sort = 1;//1代表降序，0代表升序
    String college;
    String major;
    int pageNum = 10;
    int pageStart = 0;
    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }
}
