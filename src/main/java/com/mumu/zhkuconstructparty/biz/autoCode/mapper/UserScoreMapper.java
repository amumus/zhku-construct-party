package com.mumu.zhkuconstructparty.biz.autoCode.mapper;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.UserScore;
import java.util.List;

public interface UserScoreMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserScore record);

    UserScore selectByPrimaryKey(Integer userId);

    List<UserScore> selectAll();

    int updateByPrimaryKey(UserScore record);

}