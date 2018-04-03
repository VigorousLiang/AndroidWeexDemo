package com.vigorous.weexdemo.ui.WeexActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vigorous.weexdemo.R;
import com.vigorous.weexdemo.util.FileUtils;
import com.vigorous.weexdemo.util.WXLocalFileUtils;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vigorousliang
 * Created on 2018/3/6
 */

public class WeexLocalActivity extends AppCompatActivity implements IWXRenderListener {

    private Context mContext;
    private WXSDKInstance mWXSDKInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getBaseContext();
        setContentView(R.layout.activity_local_weex);

        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);




        /**
         * WXSample 可以替换成自定义的字符串，针对埋点有效。
         * template 是.we transform 后的 js文件。
         * option 可以为空，或者通过option传入 js需要的参数。例如bundle js的地址等。
         * jsonInitData 可以为空。
         */

        mWXSDKInstance.render("GTJAWeexLocal", WXFileUtils.loadAsset("test.js", this),
                null, null, WXRenderStrategy
                        .APPEND_ASYNC);

//        try {
//            FileUtils.copyAssetsFile(mContext, "test.js", mContext.getFilesDir().getAbsolutePath
//                    () + "/test.js");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        mWXSDKInstance.render("GTJAWeexLocal", WXLocalFileUtils.loadPrivateDirBundleJS(mContext
//                        .getFilesDir().getAbsolutePath() + "/test.js",mContext),
//                null, null, WXRenderStrategy
//                        .APPEND_ASYNC);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        setContentView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }
}
