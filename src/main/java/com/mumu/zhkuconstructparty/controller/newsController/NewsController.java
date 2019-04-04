package com.mumu.zhkuconstructparty.controller.newsController;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.NewsContent;
import com.mumu.zhkuconstructparty.common.ResultObject;
import com.mumu.zhkuconstructparty.service.NewsService;
import com.mumu.zhkuconstructparty.vo.NewsVo.NewsQueryVo;
import com.mumu.zhkuconstructparty.vo.NewsVo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*", maxAge = 3600)
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
    @RequestMapping("/portal/news/addNews")
    @ResponseBody
    @Transactional
    public ResultObject addNews(@RequestBody NewsQueryVo vo){
        ResultObject resultObject = ResultObject.successResult();
        vo.setCreated(new Date());
        Integer r = newsService.addNews(vo);
        if(r != 1){
            resultObject = ResultObject.failResult();
        }
        return resultObject;
    }
    @RequestMapping("/portal/news/getNewsList")
    @ResponseBody
    public ResultObject vueGetNewsList(NewsQueryVo vo){
        Map map = newsService.getNewsList(vo);
        ResultObject resultObject = ResultObject.successResult() ;
        resultObject.setData(map);
        return resultObject;
    }
    @RequestMapping("/portal/news/editNewsById")
    @ResponseBody
    @Transactional
    public ResultObject vueEditNews(NewsQueryVo vo){
        Integer result = newsService.editNewsById(vo);
        ResultObject resultObject = ResultObject.successResult() ;
        resultObject.setData(result);
        return resultObject;
    }
    @RequestMapping("/portal/news/getNews")
    @ResponseBody
    public ResultObject vueGetNews(Integer id){
        if(id == null){
            return ResultObject.successResult();
        }
        NewsVo vo = newsService.getNews(id);
        ResultObject resultObject = ResultObject.successResult();
        resultObject.setData(vo);
        return resultObject;
    }
}
