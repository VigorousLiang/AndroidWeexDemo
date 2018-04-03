package com.vigorous.weexdemo.weex.jump;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vigorousliang
 * Created on 2018/3/16
 */

public class WeexPageJumpRule {

    @SerializedName("type")
    private String jumpTypeName;

    @SerializedName("page")
    //当前字段只有在weex跳转native时有效
    private String jumpNativePage;


    @SerializedName("url")
    //当前字段只有在native跳转weex时有效
    private String weexJsUrl;

    @SerializedName("md5")
    //当前字段只有在native跳转weex时有效
    private String weexJsMD5;

    @SerializedName("h5")
    //当前字段只有在weex或者native跳转h5时有效
    private String h5;

    @SerializedName("version")
    //当前跳转支持的最低客户端版本，若当前客户端低于当前版本，则显示默认页面，提示用户升级客户端
    private String version;


    public String getJumpTypeName() {
        return jumpTypeName;
    }

    public void setJumpTypeName(String jumpTypeName) {
        this.jumpTypeName = jumpTypeName;
    }

    public String getJumpNativePage() {
        return jumpNativePage;
    }

    public void setJumpNativePage(String jumpNativePage) {
        this.jumpNativePage = jumpNativePage;
    }

    public String getWeexJsUrl() {
        return weexJsUrl;
    }

    public void setWeexJsUrl(String weexJsUrl) {
        this.weexJsUrl = weexJsUrl;
    }

    public String getWeexJsMD5() {
        return weexJsMD5;
    }

    public void setWeexJsMD5(String weexJsMD5) {
        this.weexJsMD5 = weexJsMD5;
    }

    public String getH5() {
        return h5;
    }

    public void setH5(String h5) {
        this.h5 = h5;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
