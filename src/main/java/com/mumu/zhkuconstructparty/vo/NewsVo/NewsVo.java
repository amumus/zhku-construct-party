package com.mumu.zhkuconstructparty.vo.NewsVo;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.News;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.NewsContent;

public class NewsVo extends News{
    NewsContent newsContent;
    String publish_data;

    public String getPublish_data() {
        return publish_data;
    }

    public void setPublish_data(String publish_data) {
        this.publish_data = publish_data;
    }

    public NewsContent getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(NewsContent newsContent) {
        this.newsContent = newsContent;
    }
}
