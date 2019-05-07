package com.mumu.zhkuconstructparty.service;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.UserScore;
import com.mumu.zhkuconstructparty.dto.ScoreDto.UserScoreDto;

import java.util.List;
import java.util.Map;

public interface ScoreService {
    Map addScore(Integer userId,Integer scoreId);

    Map getScoreDetial(UserScoreDto userScoreDto);

    UserScore getUserScoreById(Integer userId);

    Object getUserRank(Integer userId);

    Map getUserScoreReport(Integer userId);

    Map getUserScoreList(UserScoreDto userScoreDto);

    Map getTop10Student(String college, String major);

    List scorePercentList(String college, String major);
}
