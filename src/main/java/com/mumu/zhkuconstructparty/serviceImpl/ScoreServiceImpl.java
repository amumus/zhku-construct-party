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
import com.mumu.zhkuconstructparty.dto.ScoreDto.UserScoreDto;
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
    public Map addScore(Integer userId, Integer scoreTaskId) {
        //返回结果，格式：{"message":"...","display":"1"//是否显示信息，1显示、0不显示}
        Map result = null;
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
               result = loginAddScore(key,userScore,userScoreDetail);
               break;
             //学习新闻满10分钟，积分加10分
            case ScoreTaskValue.STUDY_NEWS_TIME:
                result = studyNewsTime(key,userScore,userScoreDetail);
                break;
            //学习视频满10分钟，积分加10分
            case ScoreTaskValue.STUDY_VIDEO_TIME:
                result = studyNewsTime(key,userScore,userScoreDetail);
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
        return result;
    }

    private Map studyNewsTime(String key, UserScore userScore, UserScoreDetail userScoreDetail) {
        Map result = new HashMap();
        String flag = key+"flag";
        String done = key+"done";
        String keyValue = jedisUtilService.getSTRINGS().get(key);
        String flagValue = jedisUtilService.getSTRINGS().get(flag);
        String doneValue = jedisUtilService.getSTRINGS().get(done);
        //空代表还未加分
        if(StringUtils.isEmpty(doneValue)){
            //今天第一次加分
            if(StringUtils.isEmpty(keyValue)){
                jedisUtilService.getSTRINGS().setEx(key,3600*24,String.valueOf(System.currentTimeMillis() / 1000));
                jedisUtilService.getSTRINGS().setEx(flag,3600*24,"1");
                result.put("message","+10分，今日次数：1 / 6");
                result.put("display","1");
            }else{
                //检查时间差，单位秒
                long currentTime = System.currentTimeMillis() / 1000;
                long oldTime = Long.valueOf(jedisUtilService.getSTRINGS().get(key));
                long timeDifference = currentTime - oldTime ;
                //如果时间差在4.5分钟，说明是正常访问，执行加分或者添加累计时间，时间差小于4.5分钟说明是恶意刷分
                if(timeDifference > 270L) {
                    String s = String.valueOf(Integer.parseInt(flagValue)+1);
                    //已经三十分钟,五分钟请求一次,加积分
                    if(Integer.parseInt(s) >= 6){
                        jedisUtilService.getSTRINGS().setEx(done,3600*24,"1");
                    }
                    jedisUtilService.getSTRINGS().setEx(key,3600*24,String.valueOf(System.currentTimeMillis() / 1000));
                    jedisUtilService.getSTRINGS().setEx(flag,3600*24,s);
                    result.put("message","+10分，今日次数："+s+" / 6");
                    result.put("display","1");
                }else{//判定为刷分情节
                    result.put("message","刷分");
                    result.put("display","0");
                }
            }

        }else{ //已加满分
            result.put("message","已加满分");
            result.put("display","0");
        }
        return result;
    }

    private Map loginAddScore(String key,UserScore userScore,UserScoreDetail userScoreDetail){
        Map result = new HashMap();
        String login = jedisUtilService.getSTRINGS().get(key);
        //空就加分
        if(StringUtils.isEmpty(login)){
            jedisUtilService.getSTRINGS().setEx(key,3600*24,"1");
            myUserScoreMapper.updateUserScoreByIdSelect(userScore);
            userScoreDetailMapper.insert(userScoreDetail);
            result.put("message",userScoreDetail.getRemark());
            result.put("display",1);
        }else{
            //非空不加分
            result.put("message","今天已经登录了");
            result.put("display",0);
        }
        return result;
    }


    @Override
    public Map getScoreDetial(UserScoreDto userScoreDto) {
        Map result = new HashMap();
        List<UserScoreDetail> userScoreList= myUserScoreMapper.getScoreDetial(userScoreDto);
        result.put("list",userScoreList);
        return result;
    }

    @Override
    public UserScore getUserScoreById(Integer userId) {
        return userScoreMapper.selectByPrimaryKey(userId);
    }
}
