package com.mumu.zhkuconstructparty.serviceImpl;

import com.mumu.zhkuconstructparty.biz.autoCode.mapper.UserMapper;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.User;
import com.mumu.zhkuconstructparty.biz.mapper.MyUserMapper;
import com.mumu.zhkuconstructparty.service.UserService;
import com.mumu.zhkuconstructparty.vo.UserVo.UserQueryVo;
import com.mumu.zhkuconstructparty.vo.UserVo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService{

    @Autowired
    MyUserMapper myUserMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public Map listUser(UserQueryVo vo) {
        Map map = new HashMap();
        List<User> list = myUserMapper.listUsers(vo);
        int count = myUserMapper.coutListUsers(vo);
        map.put("data",list);
        map.put("count",count);
        return null;
    }

    @Override
    public Integer addUser(UserVo vo) {
        return null;
    }

    @Override
    public Integer updateUser(UserVo vo) {
        return null;
    }
}
