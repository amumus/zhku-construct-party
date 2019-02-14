package com.mumu.zhkuconstructparty.controller.systemUserController;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.SystemUser;
import com.mumu.zhkuconstructparty.common.ResultObject;
import com.mumu.zhkuconstructparty.vo.SystemUserVo.SystemUserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SystemUserController {

    @RequestMapping("/news/getNewsList")
    @ResponseBody
    public ResultObject login(SystemUserVo vo){
        return ResultObject.successResult();
    }
}
