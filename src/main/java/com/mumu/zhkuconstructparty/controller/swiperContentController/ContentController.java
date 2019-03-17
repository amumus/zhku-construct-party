package com.mumu.zhkuconstructparty.controller.swiperContentController;


import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Content;
import com.mumu.zhkuconstructparty.common.ResultObject;
import com.mumu.zhkuconstructparty.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class ContentController {

    @Autowired
    ContentService contentService;

    @RequestMapping("/uniApp/content/listContent")
    @ResponseBody
    public ResultObject getUniContent(){
        ResultObject resultObject = ResultObject.successResult();
        List<Content> list = contentService.getUniList();
        resultObject.setData(list);
        return resultObject;
    }



}
