package com.mumu.zhkuconstructparty.serviceImpl;

import com.mumu.zhkuconstructparty.biz.autoCode.mapper.NewsMapper;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.News;
import com.mumu.zhkuconstructparty.biz.mapper.MyNewsMapper;
import com.mumu.zhkuconstructparty.service.NewsService;
import com.mumu.zhkuconstructparty.vo.NewsVo.NewsQueryVo;
import com.mumu.zhkuconstructparty.vo.NewsVo.NewsVo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private MyNewsMapper myNewsMapper;
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<NewsVo> getNewsList(NewsQueryVo vo) {
//        if(newsMapper == null){
//            System.out.println("kong========================================");
//        }
        List<News> list = myNewsMapper.selectNews(vo);
//        List<News> list= myNewsMapper.selectAllNews();
        int count = myNewsMapper.count();
//        List<News> list = newsMapper.selectAll();
        List<NewsVo> resList = new ArrayList<>();
        try {
            BeanUtils.copyProperties(resList,list);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return resList;
    }
}
