package com.mumu.zhkuconstructparty.serviceImpl;

import com.alibaba.druid.support.json.JSONUtils;
import com.mumu.zhkuconstructparty.biz.autoCode.mapper.NewsContentMapper;
import com.mumu.zhkuconstructparty.biz.autoCode.mapper.NewsMapper;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.News;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.NewsContent;
import com.mumu.zhkuconstructparty.biz.mapper.MyNewsContentMapper;
import com.mumu.zhkuconstructparty.biz.mapper.MyNewsMapper;
import com.mumu.zhkuconstructparty.helper.NewsHelper;
import com.mumu.zhkuconstructparty.service.NewsService;
import com.mumu.zhkuconstructparty.util.JsonUtils;
import com.mumu.zhkuconstructparty.vo.NewsVo.NewsQueryVo;
import com.mumu.zhkuconstructparty.vo.NewsVo.NewsVo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private MyNewsMapper myNewsMapper;
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private NewsContentMapper newsContentMapper;
    @Autowired
    private MyNewsContentMapper myNewsContentMapper;


    @Override
    public Map getNewsList(NewsQueryVo vo) {
        vo.setPageStart(vo.getPageStart() * vo.getPageNum());
        List<News> list = myNewsMapper.selectNews(vo);
        NewsHelper helper = new NewsHelper();
        List<NewsVo> newsVoList = helper.newsList2voList(list);

        int count = myNewsMapper.selectNewsCount(vo);

        Map map = new HashMap();
        map.put("data",newsVoList);
        map.put("count",count);
        return map;
    }

    @Override
    public NewsVo getNews(Integer id) {
        News news = newsMapper.selectByPrimaryKey(id);
        NewsContent newsContent =myNewsContentMapper.findNewsContentByNewsId(id);
        String newsString = JsonUtils.toJSONString(news);
        NewsVo newsVo = JsonUtils.jsonToBean(newsString,NewsVo.class);
        newsVo.setNewsContent(newsContent);
        return newsVo;
    }

    @Override
    public Integer addNews(NewsQueryVo vo) {
        myNewsMapper.insertInto(vo);
        NewsContent newsContent = new NewsContent();
        newsContent.setNewsId(vo.getId());
        newsContent.setContent(vo.getContent());
        Integer r = myNewsContentMapper.insert(newsContent);
        return r;
    }
}
