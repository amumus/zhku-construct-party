package com.mumu.zhkuconstructparty.vo.NewsVo;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.News;

public class NewsQueryVo extends News {
    int pageNum = 10;
    int pageStart = 0;

    String startTime;
    String endTime;

    Integer newsContentId;
    String content;

    String keyword;

    public Integer getNewsContentId() {
        return newsContentId;
    }

    public void setNewsContentId(Integer newsContentId) {
        this.newsContentId = newsContentId;
    }
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
