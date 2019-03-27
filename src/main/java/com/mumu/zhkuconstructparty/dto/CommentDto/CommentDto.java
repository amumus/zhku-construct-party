package com.mumu.zhkuconstructparty.dto.CommentDto;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Comment;

public class CommentDto extends Comment {
    int pageNum = 10;
    int pageStart = 0;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }
}
