package com.mumu.zhkuconstructparty.dto.VideoDto;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Video;

public class VideoDto extends Video {
    int pageNum = 10;
    int pageStart = 0;

    String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
