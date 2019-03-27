package com.mumu.zhkuconstructparty.serviceImpl;

import com.mumu.zhkuconstructparty.biz.autoCode.mapper.CommentMapper;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Comment;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.News;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.User;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Video;
import com.mumu.zhkuconstructparty.biz.mapper.MyCommentMapper;
import com.mumu.zhkuconstructparty.biz.mapper.MyNewsMapper;
import com.mumu.zhkuconstructparty.biz.mapper.MyUserMapper;
import com.mumu.zhkuconstructparty.biz.mapper.MyVideoMapper;
import com.mumu.zhkuconstructparty.common.CommonException;
import com.mumu.zhkuconstructparty.common.ScoreTaskValue;
import com.mumu.zhkuconstructparty.dto.CommentDto.CommentDto;
import com.mumu.zhkuconstructparty.helper.CommentHelper;
import com.mumu.zhkuconstructparty.service.CommentService;
import com.mumu.zhkuconstructparty.service.ScoreService;
import com.mumu.zhkuconstructparty.vo.CommentVo.CommentVo;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    MyCommentMapper myCommentMapper;
    @Autowired
    MyUserMapper myUserMapper;
    @Autowired
    ScoreService scoreService;
    @Autowired
    MyNewsMapper myNewsMapper;
    @Autowired
    MyVideoMapper myVideoMapper;



    CommentHelper helper = new CommentHelper();

    @Override
    public Map getCommentListByIdAndType(CommentDto commentDto) {
        Map result = new HashMap();
//        拿到根评论
        List<Comment> list = myCommentMapper.getCommentListByIdAndType(commentDto);
//        根据root_id，拿到所有子评论
        if(list == null || list.isEmpty()){
            result.put("list",null);
            return result;
        }

//        获取所有user_id
        List<Integer> userIds = new ArrayList<>();
        for(Comment comment : list){
            userIds.add(comment.getUserId());
        }
//        根据根评论跟子评论的user_id来获取用户名
        List<User> userList = myUserMapper.findByIds(userIds);
        Map<Integer,User> userMap = new HashMap();
        for(User user:userList){
            userMap.put(user.getId(),user);
        }
        List<CommentVo> commentVoList = new ArrayList<>();
        for(Comment comment:list){
            CommentVo vo = helper.comment2Vo(comment);
            User u = userMap.get(vo.getUserId());
            vo.setUserName( u.getUserName());
            vo.setUserImage( u.getHeadImg());
            commentVoList.add(vo);
        }
        result.put("list",commentVoList);
        return result;
    }

    @Override
    public Map addComment(CommentDto commentDto) throws CommonException {
        if(commentDto.getUserId() == null){
            throw new CommonException("用户id为空");
        }
        commentDto.setCreated(new Date());
        Map map = new HashMap();
        Integer result = commentMapper.insert(commentDto);
        scoreService.addScore(commentDto.getUserId(), ScoreTaskValue.PUBLISH_COMMENT);
        if(result == 1){
            map.put("success",true);
            map.put("message","插入成功");
        }else{
            map.put("success",false);
            map.put("message","插入失败");
        }
        return map;
    }

    @Override
    public Map getMyCommentList(CommentDto commentDto) {
        commentDto.setPageStart(commentDto.getPageStart()*commentDto.getPageNum());
        Map result = new HashMap();
        List<Comment> commentList = myCommentMapper.getMyCommentList(commentDto);
        List<Integer> newsIdList = new ArrayList<>();
        List<Integer> videoIdList = new ArrayList<>();
        List<Integer> userIdList = new ArrayList<>();
        for( Comment  c: commentList ){
            if(c.getType() == 1){
                newsIdList.add(c.getTargetId());
            }else if(c.getType() == 2){
                videoIdList.add(c.getTargetId());
            }
            userIdList.add(c.getParentUserId());
        }
        List<News> newsList = null;
        if(!newsIdList.isEmpty()){
            newsList = myNewsMapper.findByIds(newsIdList);
        }
        List<Video> videoList = null;
        if(!videoIdList.isEmpty()){
            videoList = myVideoMapper.findByIds(videoIdList);
        }
        List<User> userList = myUserMapper.findByIds(userIdList);
        Map<Integer,String> newsMap = new HashMap();
        Map<Integer,String> videoMap = new HashMap();
        Map<Integer,String> userMap = new HashMap();
        if(newsList != null && !newsList.isEmpty()){
            for(News n:newsList){
                newsMap.put(n.getId(),n.getTitle());
            }
        }
        if(videoList != null && !videoList.isEmpty()){
            for(Video v:videoList){
                videoMap.put(v.getId(),v.getName());
            }
        }
        if(userList != null && !userList.isEmpty()){
            for(User u :userList){
                userMap.put(u.getId(),u.getUserName());
            }
        }

        CommentHelper commentHelper = new CommentHelper();

        List<CommentVo> resultList = new ArrayList<>();
        for( Comment c: commentList ){
            CommentVo vo = commentHelper.comment2Vo(c);
            if(vo.getType() == 1){
                vo.setTargetTitle(newsMap.get(vo.getTargetId()));
            }else if(vo.getType() == 2){
                vo.setTargetTitle(videoMap.get(vo.getTargetId()));
            }
            vo.setAnswer(userMap.get(vo.getParentUserId()));
            resultList.add(vo);
        }



        result.put("list",resultList);
        Integer count = myCommentMapper.getMyCommentListCount(commentDto);
        result.put("count",count);
        return result;
    }
}
