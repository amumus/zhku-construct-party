package com.mumu.zhkuconstructparty.controller.systemUserController;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.SystemUser;
import com.mumu.zhkuconstructparty.common.ResultObject;
import com.mumu.zhkuconstructparty.serviceImpl.SystemUserServiceImpl;
import com.mumu.zhkuconstructparty.vo.SystemUserVo.SystemUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class SystemUserController {

    @Autowired
    private SystemUserServiceImpl systemUserService;

    @RequestMapping("/portal/login")
    @ResponseBody
    public ResultObject login(SystemUserVo vo){
        if(vo.getPassword() == null || vo.getPassword().equals("")
                || vo.getUsername() == null || vo.getUsername().equals("")){
            return ResultObject.failResult();
        }

        Map<String,Integer> map = systemUserService.login(vo);
        ResultObject resultObject = null;
        if(map.get("result") == 1){
            resultObject = ResultObject.successResult();
            resultObject.setData(map);
        }else{
            resultObject = ResultObject.failResult();
        }
        return resultObject;
    }
}
