package com.mumu.zhkuconstructparty.service;

import com.mumu.zhkuconstructparty.vo.NewsVo.NewsQueryVo;
import com.mumu.zhkuconstructparty.vo.NewsVo.NewsVo;

import java.util.List;
import java.util.Map;

public interface NewsService {
    Map getNewsList(NewsQueryVo vo);

    NewsVo getNews(Integer id);
    Integer addNews(NewsQueryVo vo);

    Integer editNewsById(NewsQueryVo vo);

    Integer deleteNews(Integer id);
}
