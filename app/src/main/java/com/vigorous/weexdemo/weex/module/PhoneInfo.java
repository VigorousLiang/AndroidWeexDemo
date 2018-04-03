package com.vigorous.weexdemo.weex.module;

import android.os.Build;
import android.widget.Toast;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vigorousliang
 * Created on 2018/3/6
 */

public class PhoneInfo extends WXModule {

    @JSMethod(uiThread = true)
    public void showToast(String msg) {
        Toast.makeText(mWXSDKInstance.getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @JSMethod(uiThread = false)
    public void getPhoneInfo(JSCallback callback) {
        Map<String, String> infos = new HashMap<>();
        infos.put("board", Build.BOARD);
        infos.put("brand", Build.BRAND);
        infos.put("device", Build.DEVICE);
        infos.put("model", Build.MODEL);
        callback.invoke(infos);
    }
}
