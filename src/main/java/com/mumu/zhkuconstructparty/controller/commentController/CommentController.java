package com.mumu.zhkuconstructparty.controller.commentController;

import com.mumu.zhkuconstructparty.common.CommonException;
import com.mumu.zhkuconstructparty.common.ResultObject;
import com.mumu.zhkuconstructparty.common.ResultStatus;
import com.mumu.zhkuconstructparty.dto.CommentDto.CommentDto;
import com.mumu.zhkuconstructparty.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/uniApp/comment/getCommentListByIdAndType")
    @ResponseBody
    public ResultObject getCommentListByIDAndType(CommentDto commentDto){
        ResultObject resultObject = ResultObject.successResult();
        Map map = commentService.getCommentListByIdAndType(commentDto);
        resultObject.setData(map);
        return resultObject;
    }

    @RequestMapping("/uniApp/comment/addComment")
    @ResponseBody
    public ResultObject addComment(CommentDto commentDto){
        ResultObject resultObject = ResultObject.successResult();
        try {
            Map map = commentService.addComment(commentDto);
            resultObject.setData(map);
            if(!(boolean)map.get("success")){
                resultObject.setStatus(ResultStatus.FAIL);
            }
            resultObject.setMessage((String) map.get("message"));
        } catch (CommonException e) {
            resultObject = ResultObject.failResult();
            resultObject.setMessage("服务器出错");
        }

        return resultObject;
    }

    @RequestMapping("/uniApp/comment/getMyCommentList")
    @ResponseBody
    public ResultObject getMyCommentList(CommentDto commentDto){
        ResultObject resultObject = ResultObject.successResult();
        Map map = commentService.getMyCommentList(commentDto);
        resultObject.setData(map);
        return  resultObject;
    }




}


