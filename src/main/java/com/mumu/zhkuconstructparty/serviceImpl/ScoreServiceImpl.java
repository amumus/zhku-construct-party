package com.mumu.zhkuconstructparty.serviceImpl;

import com.mumu.zhkuconstructparty.biz.autoCode.mapper.ScoreTaskMapper;
import com.mumu.zhkuconstructparty.biz.autoCode.mapper.UserScoreDetailMapper;
import com.mumu.zhkuconstructparty.biz.autoCode.mapper.UserScoreMapper;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.ScoreTask;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.UserScore;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.UserScoreDetail;
import com.mumu.zhkuconstructparty.biz.mapper.MyUserScoreDetailMapper;
import com.mumu.zhkuconstructparty.biz.mapper.MyUserScoreMapper;
import com.mumu.zhkuconstructparty.common.ScoreTaskValue;
import com.mumu.zhkuconstructparty.service.ScoreService;
import com.mumu.zhkuconstructparty.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    UserScoreMapper userScoreMapper;
    @Autowired
    MyUserScoreMapper myUserScoreMapper;
    @Autowired
    UserScoreDetailMapper userScoreDetailMapper;
    @Autowired
    MyUserScoreDetailMapper myUserScoreDetailMapper;
    @Autowired
    ScoreTaskMapper scoreTaskMapper;
    @Autowired
    JedisUtilService jedisUtilService;

    public static final String loginKey = "login";
    public static final String studyNew = "studyNew";
    public static final String studyVideo = "studyVideo";

    @Override
    public void addScore(Integer userId, Integer scoreTaskId) {
        //获取积分字典表
        List<ScoreTask> scoreTaskList = scoreTaskMapper.selectAll();
        Map<Integer,ScoreTask> scoreTaskMap = new HashMap<>();
        for(ScoreTask scoreTask : scoreTaskList){
            scoreTaskMap.put(scoreTask.getId(),scoreTask);
        }
        UserScoreDetail userScoreDetail = new UserScoreDetail();
        userScoreDetail.setUserId(userId);
        userScoreDetail.setOccurTime(new Date());
        userScoreDetail.setRemark(scoreTaskMap.get(scoreTaskId).getRemark());
        userScoreDetail.setScoreId(scoreTaskId);
        userScoreDetail.setIntegralScore(scoreTaskMap.get(scoreTaskId).getScore());
        String key = DateUtil.getDay()+userId+"SCORE_TYPE"+scoreTaskId;
        UserScore userScore = userScoreMapper.selectByPrimaryKey(userId);
        userScore.setScore(userScore.getScore()+scoreTaskMap.get(scoreTaskId).getScore());
        userScore.setLastModData(new Date());
        //加分
        switch(scoreTaskId){
            //每日登录APP，积分+10
            case ScoreTaskValue.LOGIN :
                String login = jedisUtilService.getSTRINGS().get(key);
                //空就加分
                if(StringUtils.isEmpty(login)){
                    jedisUtilService.getSTRINGS().setEx(key,3600*24,"1");
                    myUserScoreMapper.updateUserScoreByIdSelect(userScore);
                    userScoreDetailMapper.insert(userScoreDetail);
                }else{
                    //非空不加分
                }
                break;
             //学习新闻满10分钟，积分加10分
            case ScoreTaskValue.STUDY_NEWS_TIME:



                break;
            //学习视频满10分钟，积分加10分
            case ScoreTaskValue.STUDY_VIDEO_TIME:
                ;
                break;
             //学习一篇文章，积分+2分
            case ScoreTaskValue.STUDY_NEWS:
                ;
                break;
            //学习一个视频，积分+2分
            case ScoreTaskValue.STUDY_VIDEO:
                ;
                break;
            //发布一条评论
            case ScoreTaskValue.PUBLISH_COMMENT:
                myUserScoreMapper.updateUserScoreByIdSelect(userScore);
                userScoreDetailMapper.insert(userScoreDetail);
                break;
            default:
        }
    }
}
