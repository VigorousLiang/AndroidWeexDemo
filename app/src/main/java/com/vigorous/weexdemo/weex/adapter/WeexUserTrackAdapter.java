package com.vigorous.weexdemo.weex.adapter;

import android.content.Context;
import android.util.Log;

import com.taobao.weex.adapter.IWXUserTrackAdapter;
import com.taobao.weex.common.WXPerformance;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by vigorousliang
 * Created on 2018/3/8
 */

public class WeexUserTrackAdapter implements IWXUserTrackAdapter {
    private final static String TAG = WeexUserTrackAdapter.class.getName();

    //Performance
    private final static String LOAD = "load";

    //Alarm
    private final static String JS_FRAMEWORK = "jsFramework";
    private final static String JS_DOWNLOAD = "jsDownload";
    private final static String DOM_MODULE = "domModule";
    private final static String JS_BRIDGE = "jsBridge";
    private final static String STREAM_MODULE = "streamModule";
    @Override
    public void commit(Context context, String eventId, String type, WXPerformance perf,
                       Map<String, Serializable> params) {
        if (type.equals(LOAD) || perf.totalTime > 0) {
            Log.e(TAG, "eventId:" + eventId);
            Log.e(TAG, "type:" + type);
            Log.e(TAG, "WXPerformance.JSLibVersion:" + perf.JSLibVersion);
            Log.e(TAG, "WXPerformance.WXSDKVersion:" + perf.WXSDKVersion);
            Log.e(TAG, "WXPerformance.actualNetworkTime:" + perf.actualNetworkTime);
            Log.e(TAG, "WXPerformance.applyUpdateTime:" + perf.applyUpdateTime);
            Log.e(TAG, "WXPerformance.callNativeTime:" + perf.callNativeTime);
            Log.e(TAG, "WXPerformance.communicateTime:" + perf.communicateTime);

            Log.e(TAG, "WXPerformance.batchTime:" + perf.batchTime);
            Log.e(TAG, "WXPerformance.componentCount:" + perf.componentCount);
            Log.e(TAG, "WXPerformance.cssLayoutTime:" + perf.cssLayoutTime);
            Log.e(TAG, "WXPerformance.firstScreenJSFExecuteTime:" + perf.firstScreenJSFExecuteTime);

            Log.e(TAG, "WXPerformance.packageSpendTime:" + perf.packageSpendTime);
            Log.e(TAG, "WXPerformance.parseJsonTime:" + perf.parseJsonTime);

            Log.e(TAG, "WXPerformance.pureNetworkTime:" + perf.pureNetworkTime);
            Log.e(TAG, "WXPerformance.screenRenderTime:" + perf.screenRenderTime);

            Log.e(TAG, "WXPerformance.templateLoadTime:" + perf.templateLoadTime);
            Log.e(TAG, "WXPerformance.updateDomObjTime:" + perf.updateDomObjTime);
            Log.e(TAG, "WXPerformance.totalTime:" + perf.totalTime);
        }
    }
}
