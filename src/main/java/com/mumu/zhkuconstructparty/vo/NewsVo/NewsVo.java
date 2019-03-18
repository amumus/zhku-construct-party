package com.mumu.zhkuconstructparty.vo.NewsVo;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.News;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.NewsContent;

public class NewsVo extends News{
    NewsContent newsContent;

    public NewsContent getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(NewsContent newsContent) {
        this.newsContent = newsContent;
    }
}
