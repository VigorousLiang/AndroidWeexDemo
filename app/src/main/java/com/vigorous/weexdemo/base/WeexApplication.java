package com.vigorous.weexdemo.base;

import android.app.Application;


import com.squareup.okhttp.OkHttpClient;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

/**
 * Created by vigorousliang on 2018/3/2.
 */

public class WeexApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient client = new OkHttpClient();
        InitConfig config = new InitConfig.Builder().setUtAdapter(new com.vigorous.weexdemo.weex.adapter.WeexUserTrackAdapter())
                .setHttpAdapter(new com.vigorous.weexdemo.weex.adapter.WeexHttpAdapter(client))
                .setStorageAdapter(new com.vigorous.weexdemo.weex.adapter.WeexStorageAdapter(getApplicationContext()))
                .setImgAdapter(new com.vigorous.weexdemo.weex.adapter.WeexImageAdapter()).build();
        WXSDKEngine.initialize(this, config);
        try {
            WXSDKEngine.registerModule("WEEXNormal", com.vigorous.weexdemo.weex.module.WEEXNormal.class);
            WXSDKEngine.registerModule("WEEXAssets", com.vigorous.weexdemo.weex.module.WEEXAssets.class);
            WXSDKEngine.registerComponent("RichText", com.vigorous.weexdemo.weex.component.RichText.class, false);
            WXSDKEngine.registerComponent("Float", com.vigorous.weexdemo.weex.component.WXWindowComponent.class,true);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }
}