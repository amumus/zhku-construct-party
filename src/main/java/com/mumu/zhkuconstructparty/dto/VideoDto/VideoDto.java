package com.mumu.zhkuconstructparty.dto.VideoDto;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Video;

public class VideoDto extends Video {
    int pageNum = 10;
    int pageStart = 0;

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
