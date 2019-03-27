package com.mumu.zhkuconstructparty.serviceImpl;

import com.mumu.zhkuconstructparty.biz.autoCode.mapper.CommentMapper;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Comment;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.User;
import com.mumu.zhkuconstructparty.biz.mapper.MyCommentMapper;
import com.mumu.zhkuconstructparty.biz.mapper.MyUserMapper;
import com.mumu.zhkuconstructparty.common.CommonException;
import com.mumu.zhkuconstructparty.common.ScoreTaskValue;
import com.mumu.zhkuconstructparty.dto.CommentDto.CommentDto;
import com.mumu.zhkuconstructparty.helper.CommentHelper;
import com.mumu.zhkuconstructparty.service.CommentService;
import com.mumu.zhkuconstructparty.service.ScoreService;
import com.mumu.zhkuconstructparty.vo.CommentVo.CommentVo;
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
}
