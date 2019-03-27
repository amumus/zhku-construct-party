package com.mumu.zhkuconstructparty.vo.CommentVo;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Comment;

public class CommentVo extends Comment {
    String userName ;
    String userImage;
    String publishDate;

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
