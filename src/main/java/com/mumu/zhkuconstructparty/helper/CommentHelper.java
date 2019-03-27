package com.mumu.zhkuconstructparty.helper;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Comment;
import com.mumu.zhkuconstructparty.util.DateUtil;
import com.mumu.zhkuconstructparty.vo.CommentVo.CommentVo;

public class CommentHelper {
    public CommentVo comment2Vo(Comment comment){
        CommentVo vo = new CommentVo();
        vo.setCommentContent(comment.getCommentContent());
        vo.setPublishDate(DateUtil.stringtodate(comment.getCreated()));
        vo.setId(comment.getId());
        vo.setParentId(comment.getParentId());
        vo.setRootId(comment.getRootId());
        vo.setTargetId(comment.getTargetId());
        vo.setType(comment.getType());
        vo.setUserId(comment.getUserId());
        vo.setParentUserId(comment.getParentUserId());
        return  vo;
    }
}
