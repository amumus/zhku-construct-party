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

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/news/getNewsList")
    @ResponseBody
    public ResultObject getNewsList(NewsQueryVo vo){
        List<NewsVo> list = newsService.getNewsList(vo);
        ResultObject resultObject = ResultObject.successResult() ;
        resultObject.setData(list);
        return resultObject;
    }
}
