package com.mumu.zhkuconstructparty.controller.newsController;

import com.mumu.zhkuconstructparty.common.ResultObject;
import com.mumu.zhkuconstructparty.service.NewsService;
import com.mumu.zhkuconstructparty.vo.NewsVo.NewsQueryVo;
import com.mumu.zhkuconstructparty.vo.NewsVo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/uniApp/news/getNewsList")
    @ResponseBody
    public ResultObject getNewsList(NewsQueryVo vo){
        Map map = newsService.getNewsList(vo);
        ResultObject resultObject = ResultObject.successResult() ;
        resultObject.setData(map);
        return resultObject;
    }

    @RequestMapping("/uniApp/news/getNews")
    @ResponseBody
    public ResultObject getNews(Integer id){
        if(id == null){
            return ResultObject.successResult();
        }
        NewsVo vo = newsService.getNews(id);
        ResultObject resultObject = ResultObject.successResult();
        resultObject.setData(vo);
        return resultObject;
    }

}
