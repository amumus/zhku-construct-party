package com.mumu.zhkuconstructparty.serviceImpl;

import com.mumu.zhkuconstructparty.biz.autoCode.mapper.UserMapper;
import com.mumu.zhkuconstructparty.biz.autoCode.mapper.UserScoreMapper;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.User;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.UserScore;
import com.mumu.zhkuconstructparty.biz.mapper.MyUserMapper;
import com.mumu.zhkuconstructparty.common.CommonException;
import com.mumu.zhkuconstructparty.service.UserService;
import com.mumu.zhkuconstructparty.vo.UserVo.UserQueryVo;
import com.mumu.zhkuconstructparty.vo.UserVo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    MyUserMapper myUserMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserScoreMapper userScoreMapper;

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

    @Transactional
    @Override
    public Integer addUser(UserQueryVo vo) {
        Integer id = myUserMapper.insert(vo);
        UserScore userScore = new UserScore();
        userScore.setName(vo.getName());
        userScore.setUserId(id);
        userScore.setScore(0);
        return userScoreMapper.insert(userScore);
    }

    @Override
    public Integer updateUser(UserQueryVo vo) {
        return myUserMapper.updateUserBySelect(vo);
    }

    @Override
    public Integer deleteByIdentityCode(UserQueryVo vo) throws CommonException {
        return myUserMapper.deleteByIdentityCode(vo.getId());
    }

    @Override
    public User login(UserQueryVo user) {

        return myUserMapper.login(user);
    }


}
