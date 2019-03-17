package com.mumu.zhkuconstructparty.biz.mapper;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.User;
import com.mumu.zhkuconstructparty.vo.UserVo.UserQueryVo;

import java.util.List;

public interface MyUserMapper {
    List<User> listUsers(UserQueryVo vo);
    User login(UserQueryVo vo);
    int updateUserBySelect(User user);

    int countListUsers(UserQueryVo vo);
    int deleteByIdentityCode();
}
