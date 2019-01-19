package com.mumu.zhkuconstructparty.service;

import com.mumu.zhkuconstructparty.vo.NewsVo.NewsQueryVo;
import com.mumu.zhkuconstructparty.vo.NewsVo.NewsVo;

import java.util.List;

public interface NewsService {
    List<NewsVo> getNewsList(NewsQueryVo vo);
}
