package com.mumu.zhkuconstructparty.vo.CommentVo;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Comment;

public class CommentVo extends Comment {
    String userName ;
    String userImage;
    String publishDate;

    //回复对象的名称
    String answer;
    //文章或视频的标题
    String targetTitle;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTargetTitle() {
        return targetTitle;
    }

    public void setTargetTitle(String targetTitle) {
        this.targetTitle = targetTitle;
    }

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
