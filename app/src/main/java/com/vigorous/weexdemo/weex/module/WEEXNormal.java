package com.vigorous.weexdemo.weex.module;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.vigorous.weexdemo.ui.WebActivity.WebviewActivity;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

/**
 * Created by vigorousliang
 * Created on 2018/3/7
 */

public class WEEXNormal extends WXModule {

    @JSMethod(uiThread = true)
    public void openURL(String url) {
        Intent intent=new Intent();
        Bundle data=new Bundle();
        data.putString(WebviewActivity.BUNDLE_DATA_TAG_URL,url);
        intent.putExtras(data);
        intent.setClass(mWXSDKInstance.getContext(),WebviewActivity.class);
        mWXSDKInstance.getContext().startActivity(intent);
    }
}