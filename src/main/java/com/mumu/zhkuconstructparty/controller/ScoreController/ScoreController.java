package com.mumu.zhkuconstructparty.controller.ScoreController;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.UserScore;
import com.mumu.zhkuconstructparty.common.ResultObject;
import com.mumu.zhkuconstructparty.dto.ScoreDto.UserScoreDto;
import com.mumu.zhkuconstructparty.service.NewsService;
import com.mumu.zhkuconstructparty.service.ScoreService;
import com.mumu.zhkuconstructparty.vo.NewsVo.NewsQueryVo;
import com.mumu.zhkuconstructparty.vo.NewsVo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/uniApp/score/getScoreDetial")
    @ResponseBody
    public ResultObject getScoreDetial(UserScoreDto userScoreDto){
        Map map = scoreService.getScoreDetial(userScoreDto);
        ResultObject resultObject = ResultObject.successResult() ;
        resultObject.setData(map);
        return resultObject;
    }

    @RequestMapping("/uniApp/score/getUserScore")
    @ResponseBody
    public ResultObject getUserScore(Integer userId){
        ResultObject resultObject = ResultObject.successResult() ;
        resultObject.setData(scoreService.getUserScoreById(userId));
        return resultObject;
    }
    @RequestMapping("/uniApp/score/getUserRank")
    @ResponseBody
    public ResultObject getUserRank(Integer userId){
        ResultObject resultObject = ResultObject.successResult() ;
        resultObject.setData(scoreService.getUserRank(userId));
        return resultObject;
    }

    @RequestMapping("/uniApp/score/addUserScore")
    @ResponseBody
    public ResultObject addUserScore(Integer userId,Integer type){
        ResultObject resultObject = ResultObject.successResult() ;
        resultObject.setData(scoreService.addScore(userId,type));
        return resultObject;
    }
    @RequestMapping("/uniApp/score/getUserScoreReport")
    @ResponseBody
    public ResultObject getUserScoreReport(Integer userId){
        ResultObject resultObject = ResultObject.successResult() ;
        Map map = scoreService.getUserScoreReport(userId);
        resultObject.setData(map);
        return resultObject;
    }

    @RequestMapping("/portal/score/getUserScoreList")
    @ResponseBody
    public ResultObject getUserScoreList(UserScoreDto userScoreDto){
        ResultObject resultObject = ResultObject.successResult();
        userScoreDto.setPageStart(userScoreDto.getPageNum()*userScoreDto.getPageStart());
        Map result = scoreService.getUserScoreList(userScoreDto);
        resultObject.setData(result);
        return resultObject;
    }

    @RequestMapping("/portal/score/getTop10Student")
    @ResponseBody
    public ResultObject getTop10Student(String college,String major){
        ResultObject resultObject = ResultObject.successResult();
        resultObject.setData(scoreService.getTop10Student(college,major));
        return resultObject;
    }
    @RequestMapping("/portal/score/scorePercentList")
    @ResponseBody
    public ResultObject scorePercentList(String college,String major){
        ResultObject resultObject = ResultObject.successResult();
        resultObject.setData(scoreService.scorePercentList(college,major));
        return resultObject;
    }

}
