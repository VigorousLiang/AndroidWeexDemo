package com.vigorous.weexdemo.model.base;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by vigorousliang
 * Created on 2018/3/13
 */

public class BaseResp implements Serializable {
    @SerializedName("errorCode")
    private int errorCode = 0;
    @SerializedName("errorMsg")
    private String errorMsg = "";
    @SerializedName("data")
    private Object[] data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }
}
