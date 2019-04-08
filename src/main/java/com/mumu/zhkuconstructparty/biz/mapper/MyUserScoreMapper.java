package com.mumu.zhkuconstructparty.biz.mapper;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.UserScore;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.UserScoreDetail;
import com.mumu.zhkuconstructparty.dto.ScoreDto.UserScoreDto;

import java.util.List;

public interface MyUserScoreMapper {
    int updateUserScoreByIdSelect(UserScore userScore);

    List<UserScoreDetail> getScoreDetial(UserScoreDto userScoreDto);

    Integer getScoreDetialCount(UserScoreDto userScoreDto);
}
