package com.mumu.zhkuconstructparty.helper;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.User;
import com.mumu.zhkuconstructparty.vo.UserVo.UserVo;

public class UserHelper{
    /**
     * 后台user转userVo
     * @param user
     * @return
     */
    public UserVo adminUser2UserVo(User user){
        UserVo userVo = new UserVo();
        userVo.setUserName(user.getUserName());
        userVo.setName(user.getName());
        userVo.setCollege(user.getCollege());
        userVo.setEmail(user.getEmail());
        userVo.setPhone(user.getPhone());
        userVo.setId(user.getId());
        userVo.setIdentityCode(user.getIdentityCode());
        userVo.setMajor(user.getMajor());
        return  userVo;
    }

}
