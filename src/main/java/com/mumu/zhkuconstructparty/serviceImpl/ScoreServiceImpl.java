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

import java.math.BigDecimal;
import java.util.*;

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
                result = studyNewsOnlineTime(key,userScore,userScoreDetail);
                break;
            //学习视频满10分钟，积分加10分
            case ScoreTaskValue.STUDY_VIDEO_TIME:
                result = studyNewsOnlineTime(key,userScore,userScoreDetail);
                break;
             //学习一篇文章，积分+2分
            case ScoreTaskValue.STUDY_NEWS:
                result = studyTimes(key, userScore, userScoreDetail);
                break;
            //学习一个视频，积分+2分
            case ScoreTaskValue.STUDY_VIDEO:
                result = studyTimes(key, userScore, userScoreDetail);
                break;
            //发布一条评论
            case ScoreTaskValue.PUBLISH_COMMENT:
               result = publishComment(key,userScore,userScoreDetail);
                break;
            default:
        }
        return result;
    }

    private Map publishComment(String key, UserScore userScore, UserScoreDetail userScoreDetail) {
        Map result = new HashMap();
        String keyValue = jedisUtilService.getSTRINGS().get(key);
        if(StringUtils.isEmpty(keyValue)){
            jedisUtilService.getSTRINGS().setEx(key,3600*24,"1");
            result.put("message","+2分，今日次数：1 / 6");
            result.put("display","1");
            myUserScoreMapper.updateUserScoreByIdSelect(userScore);
            userScoreDetailMapper.insert(userScoreDetail);
        }else{
            if(Integer.parseInt(keyValue)>=5){
                result.put("message","次数到达上限");
                result.put("display","0");
            }else{
                jedisUtilService.getSTRINGS().setEx(key,3600*24,String.valueOf(Integer.parseInt(keyValue)+1));
                result.put("message","+2分，今日次数："+String.valueOf(Integer.parseInt(keyValue)+1)+" / 6");
                result.put("display","1");
                myUserScoreMapper.updateUserScoreByIdSelect(userScore);
                userScoreDetailMapper.insert(userScoreDetail);
            }
        }

//        myUserScoreMapper.updateUserScoreByIdSelect(userScore);
//        userScoreDetailMapper.insert(userScoreDetail);
        return result;
    }

    private Map studyTimes(String key, UserScore userScore, UserScoreDetail userScoreDetail) {
        Map result = new HashMap();
        String keyValue = jedisUtilService.getSTRINGS().get(key);
        //空代表第一次
        if(StringUtils.isEmpty(keyValue)){
            jedisUtilService.getSTRINGS().setEx(key,3600*24,"1");
            result.put("message","+2分，今日次数：1 / 6");
            result.put("display","1");
            myUserScoreMapper.updateUserScoreByIdSelect(userScore);
            userScoreDetailMapper.insert(userScoreDetail);
        }else{
            //每天限制加五次
            if(Integer.parseInt(keyValue)>= 5){
                result.put("message","次数到达上限");
                result.put("display","0");
            }else{
                jedisUtilService.getSTRINGS().setEx(key,3600*24,String.valueOf(Integer.parseInt(keyValue)+1));
                result.put("message","+2分，今日次数："+String.valueOf(Integer.parseInt(keyValue)+1)+" / 6");
                result.put("display","1");
                myUserScoreMapper.updateUserScoreByIdSelect(userScore);
                userScoreDetailMapper.insert(userScoreDetail);
            }
        }

        return result;
    }

    private Map studyNewsOnlineTime(String key, UserScore userScore, UserScoreDetail userScoreDetail) {
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
                result.put("message","+2分，今日次数：1 / 6");
                result.put("display","1");
               myUserScoreMapper.updateUserScoreByIdSelect(userScore);
                userScoreDetailMapper.insert(userScoreDetail);
            }else{
                //检查时间差，单位秒
                long currentTime = System.currentTimeMillis() / 1000;
                long oldTime = Long.valueOf(jedisUtilService.getSTRINGS().get(key));
                long timeDifference = currentTime - oldTime ;
                //如果时间差在110秒，说明是正常访问，执行加分或者添加累计时间，时间差小于110秒说明是恶意刷分
                if(timeDifference > 110L) {
                    String s = String.valueOf(Integer.parseInt(flagValue)+1);
                    //已经十二分钟,2分钟请求一次,加积分
                    if(Integer.parseInt(s) >= 6){
                        jedisUtilService.getSTRINGS().setEx(done,3600*24,"1");
                    }
                    jedisUtilService.getSTRINGS().setEx(key,3600*24,String.valueOf(System.currentTimeMillis() / 1000));
                    jedisUtilService.getSTRINGS().setEx(flag,3600*24,s);
                    result.put("message","+10分，今日次数："+s+" / 6");
                    result.put("display","1");
                   myUserScoreMapper.updateUserScoreByIdSelect(userScore);
                    userScoreDetailMapper.insert(userScoreDetail);
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
        result.put("count",myUserScoreMapper.getScoreDetialCount(userScoreDto));
        return result;
    }

    @Override
    public UserScore getUserScoreById(Integer userId) {
        return userScoreMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Integer getUserRank(Integer userId) {
        return myUserScoreMapper.getUserRankByPrimaryKey(userId);
    }

    @Override
    public Map  getUserScoreReport(Integer userId) {
        Map map = new HashMap();
        List<Map> monthList = myUserScoreMapper.getMonthList(userId);
        Map<Integer,Double> monthMap = new HashMap();
        for(Map m :monthList){
            monthMap.put((Integer) m.get("month"),((BigDecimal)m.get("score")).doubleValue());
        }
        double[] monthListArr = new double[12];
        for (int i = 0 ; i < 12 ; i ++){
            if(monthMap.get(i+1) != null){
                monthListArr[i] = monthMap.get(i+1);
            }
        }
       List<Map> typeList = myUserScoreMapper.getTypeList(userId);
//        Map typeMap = new HashMap();
        for(Map m : typeList){
            int score_id = (int) m.get("score_id");
            m.put("value",m.get("score"));
            switch (score_id){
                case 1:
                    m.put("name","登录积分");
                    break;
                case 2:
                    m.put("name","学习文章时长");
                    break;
                case 3:
                    m.put("name","学习视频时长");
                    break;
                case 4:
                    m.put("name","文章数积分");
                    break;
                case 5:
                    m.put("name","视频数积分");
                    break;
                case 6:
                    m.put("name","评论积分");
            }
        }


        map.put("monthList",monthListArr);
        map.put("typeList",typeList);
        return map;
    }

    @Override
    public Map getUserScoreList(UserScoreDto userScoreDto) {
        Map result = new HashMap();
        List<Map> userScoreList = myUserScoreMapper.getUserScoreList(userScoreDto);
        result.put("list",userScoreList);
        Integer count = myUserScoreMapper.getUserScoreListCount(userScoreDto);
        result.put("count",count);
        return result;
    }

    @Override
    public Map getTop10Student(String college, String major) {
        Map result = new HashMap();
        Map query = new HashMap();
        query.put("college",college);
        query.put("major",major);
        List<Map> list = myUserScoreMapper.getTop10Student(query);
        List<String> nameList = new ArrayList<>();
        List<Integer> scoreList = new ArrayList<>();
        for(Map m:list){
            nameList.add((String) m.get("name"));
            scoreList.add((Integer) m.get("score"));
        }
        result.put("scoreList",scoreList);
        result.put("nameList",nameList);
        return result;
    }

    @Override
    public List scorePercentList(String college, String major) {
        Map query = new HashMap();
        query.put("college",college);
        query.put("major",major);
        List<Map> list = myUserScoreMapper.getScorePercentList(query);
//        List<ScoreTask> scoreTaskList = scoreTaskMapper.selectAll();
//        Map scoreTaskMap = new HashMap();
//        for(ScoreTask scoreTask : scoreTaskList){
//            scoreTaskMap.put("id",scoreTask.get);
//        }
        for(Map m :list){
            Integer id = (Integer) m.get("id");
            switch (id){
                case 1:
                    m.put("name","登录积分");
                    break;
                case 2:
                    m.put("name","学习文章时长积分");
                    break;
                case 3:
                    m.put("name","学习视频时长积分");
                    break;
                case 4:
                    m.put("name","学习文章数积分");
                    break;
                case 5:
                    m.put("name","学习视频数积分");
                    break;
                case 6:
                    m.put("name","评论积分");
                    break;
            }
        }
        return list;
    }
}
