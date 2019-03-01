package com.mumu.zhkuconstructparty.vo.UserVo;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.User;

public class UserQueryVo extends User {
    int pageNum = 10;
    int pageStart = 0;
    String orderByColumn;

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

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
