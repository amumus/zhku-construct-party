package com.mumu.zhkuconstructparty.biz.autoCode.mapper;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.UserScoreDetail;
import java.util.List;

public interface UserScoreDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserScoreDetail record);

    UserScoreDetail selectByPrimaryKey(Integer id);

    List<UserScoreDetail> selectAll();

    int updateByPrimaryKey(UserScoreDetail record);
}