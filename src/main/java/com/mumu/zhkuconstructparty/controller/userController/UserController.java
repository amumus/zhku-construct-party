package com.mumu.zhkuconstructparty.controller.userController;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.User;
import com.mumu.zhkuconstructparty.common.CommonException;
import com.mumu.zhkuconstructparty.common.ResultObject;
import com.mumu.zhkuconstructparty.common.ResultStatus;
import com.mumu.zhkuconstructparty.service.UserService;
import com.mumu.zhkuconstructparty.vo.UserVo.UserQueryVo;
import com.mumu.zhkuconstructparty.vo.UserVo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/portal/user/listUser")
    @ResponseBody
    public ResultObject getUserList(UserQueryVo vo){
        ResultObject resultObject = new ResultObject();
        try {
            Map map = userService.listUser(vo);
            resultObject.setStatus(ResultStatus.SUCCESS);
            resultObject.setMessage("成功");
            resultObject.setData(map);
        } catch (CommonException e) {
            resultObject.setMessage("获取用户列表失败，原因："+ e.getMessage());
            resultObject.setStatus(ResultStatus.FAIL);
            e.printStackTrace();
        }
        return resultObject;
    }


    @RequestMapping("/uniApp/user/login")
    @ResponseBody
    public ResultObject login(UserQueryVo user){
        ResultObject resultObject = ResultObject.failResult();

        User result = userService.login(user);
        if(result != null){
            resultObject.setData(result);
            resultObject.setStatus(ResultStatus.SUCCESS);
            resultObject.setMessage("登录成功");
        }
        return resultObject;
    }

    @RequestMapping("/portal/user/addUser")
    @ResponseBody
    public ResultObject addUser(UserQueryVo vo){
        ResultObject resultObject = ResultObject.failResult();
        try {
            Integer result = userService.addUser(vo);
            if(result != 1){
                resultObject.setMessage("插入失败，请重试");
                resultObject.setStatus(ResultStatus.FAIL);
            }
        } catch (CommonException e) {
            resultObject.setMessage("后台系统报错，请重试");
            resultObject.setStatus(ResultStatus.FAIL);
            e.printStackTrace();
        }
        return resultObject;
    }
    @RequestMapping("/portal/user/editUserBySelect")
    @ResponseBody
    public ResultObject editUserBySelect(UserQueryVo vo){
        ResultObject resultObject = ResultObject.successResult();
        try {
            Integer result = userService.updateUser(vo);
            if(result != 1){
                resultObject.setMessage("更新失败，请重试");
                resultObject.setStatus(ResultStatus.FAIL);
            }
        } catch (CommonException e) {
            resultObject.setMessage("后台系统报错，请重试");
            resultObject.setStatus(ResultStatus.FAIL);
            e.printStackTrace();
        }
        return resultObject;
    }


}
