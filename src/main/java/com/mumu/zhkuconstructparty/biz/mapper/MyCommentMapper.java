package com.mumu.zhkuconstructparty.biz.mapper;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Comment;
import com.mumu.zhkuconstructparty.dto.CommentDto.CommentDto;

import java.util.List;

public interface MyCommentMapper {
    List<Comment> getCommentListByIdAndType(CommentDto commentDto);


    List<Comment> getMyCommentList(CommentDto commentDto);

    Integer getMyCommentListCount(CommentDto commentDto);
}
