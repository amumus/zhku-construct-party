package com.mumu.zhkuconstructparty.biz.autoCode.mapper;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.ScoreTask;
import java.util.List;

public interface ScoreTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScoreTask record);

    ScoreTask selectByPrimaryKey(Integer id);

    List<ScoreTask> selectAll();

    int updateByPrimaryKey(ScoreTask record);
}