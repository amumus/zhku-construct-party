package com.mumu.zhkuconstructparty.serviceImpl;

import com.mumu.zhkuconstructparty.biz.autoCode.mapper.UserMapper;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.User;
import com.mumu.zhkuconstructparty.biz.mapper.MyUserMapper;
import com.mumu.zhkuconstructparty.common.CommonException;
import com.mumu.zhkuconstructparty.service.UserService;
import com.mumu.zhkuconstructparty.vo.UserVo.UserQueryVo;
import com.mumu.zhkuconstructparty.vo.UserVo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    MyUserMapper myUserMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public Map listUser(UserQueryVo vo) {
        vo.setPageStart(vo.getPageNum()*vo.getPageStart());
        Map map = new HashMap();
        List<User> list = myUserMapper.listUsers(vo);
        int count = myUserMapper.countListUsers(vo);
        map.put("data",list);
        map.put("count",count);
        map.put("pages",count / vo.getPageNum());
        return map;
    }

    @Override
    public Integer addUser(UserQueryVo vo) {
        return userMapper.insert(vo);
    }

    @Override
    public Integer updateUser(UserQueryVo vo) {
        return myUserMapper.updateUserBySelect(vo);
    }

    @Override
    public Integer deleteByIdentityCode(UserQueryVo vo) throws CommonException {
        return null;
    }
}
