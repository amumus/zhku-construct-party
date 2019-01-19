package com.mumu.zhkuconstructparty.vo.NewsVo;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.News;

public class NewsQueryVo extends News {
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
