package com.mumu.zhkuconstructparty.serviceImpl;

import com.alibaba.druid.support.json.JSONUtils;
import com.mumu.zhkuconstructparty.biz.autoCode.mapper.NewsContentMapper;
import com.mumu.zhkuconstructparty.biz.autoCode.mapper.NewsMapper;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.News;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.NewsContent;
import com.mumu.zhkuconstructparty.biz.mapper.MyNewsContentMapper;
import com.mumu.zhkuconstructparty.biz.mapper.MyNewsMapper;
import com.mumu.zhkuconstructparty.service.NewsService;
import com.mumu.zhkuconstructparty.util.JsonUtils;
import com.mumu.zhkuconstructparty.vo.NewsVo.NewsQueryVo;
import com.mumu.zhkuconstructparty.vo.NewsVo.NewsVo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//        if(newsMapper == null){
//            System.out.println("kong========================================");
//        }
        List<News> list = myNewsMapper.selectNews(vo);
//        List<News> list= myNewsMapper.selectAllNews();
        int count = myNewsMapper.selectNewsCount(vo);
//        List<News> list = newsMapper.selectAll();
        List<NewsVo> resList = new ArrayList<>();
        try {
            BeanUtils.copyProperties(resList,list);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        map.put("data",resList);
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
}
