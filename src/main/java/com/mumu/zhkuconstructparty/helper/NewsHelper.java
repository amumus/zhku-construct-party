package com.mumu.zhkuconstructparty.helper;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.News;
import com.mumu.zhkuconstructparty.vo.NewsVo.NewsVo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NewsHelper {
    public List<NewsVo> newsList2voList(List<News> list) {
        List<NewsVo> newsVoList = new ArrayList<>();
        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(News news:list){
            NewsVo newsVo = new NewsVo();
            newsVo.setPublish_data(myFmt.format(news.getCreated()));
            newsVo.setAuthor(news.getAuthor());
            newsVo.setId(news.getId());
            newsVo.setSecondTitle(news.getSecondTitle());
            newsVo.setImage(news.getImage());
            newsVo.setTitle(news.getTitle());
            newsVoList.add(newsVo);
        }

        return newsVoList;
    }
}
