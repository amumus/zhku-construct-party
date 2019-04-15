package com.mumu.zhkuconstructparty.service;

import com.mumu.zhkuconstructparty.common.CommonException;
import com.mumu.zhkuconstructparty.dto.CommentDto.CommentDto;

import java.util.Map;

public interface CommentService {
    Map getCommentListByIdAndType(CommentDto commentDto);

    Map addComment(CommentDto commentDto) throws CommonException;

    Map getMyCommentList(CommentDto commentDto);

    Map getCommontById(Integer id);
}
