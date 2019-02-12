package com.mumu.zhkuconstructparty.biz.autoCode.mapper;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}