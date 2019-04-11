package com.mumu.zhkuconstructparty.biz.mapper;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.User;
import com.mumu.zhkuconstructparty.vo.UserVo.UserQueryVo;

import java.util.List;

public interface MyUserMapper {
    List<User> listUsers(UserQueryVo vo);
    User login(UserQueryVo vo);
    int updateUserBySelect(UserQueryVo user);

    int countListUsers(UserQueryVo vo);
    int deleteByIdentityCode(Integer id);

    List<User> findByIds(List<Integer> list);

    Integer insert(User record);
}
