package com.mumu.zhkuconstructparty.common;

import java.io.Serializable;

public class ResultObject implements Serializable{
    public String message;
    public int status;
    public Object data;

    public ResultObject() {
    }

    public ResultObject(String message, int status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public static ResultObject successResult(){
        return new ResultObject("成功",ResultStatus.SUCCESS,null);
    }
    public static ResultObject successResult(Object data){
        return new ResultObject("成功",ResultStatus.SUCCESS,data);
    }
    public static ResultObject failResult(){
        return new ResultObject("失败",ResultStatus.FAIL,null);
    }
    public static ResultObject failResult(Object data){
        return new ResultObject("失败",ResultStatus.FAIL,data);
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
