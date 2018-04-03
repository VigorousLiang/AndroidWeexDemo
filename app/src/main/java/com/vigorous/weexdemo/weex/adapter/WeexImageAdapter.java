package com.vigorous.weexdemo.weex.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

/**
 * Created by vigorousliang
 * Created on 2018/3/6
 */

public class WeexImageAdapter implements IWXImgLoaderAdapter {
    @Override
    public void setImage(final String url, final ImageView view, WXImageQuality quality, WXImageStrategy strategy) {
        //实现你自己的图片下载，否则图片无法显示。
        WXSDKManager.getInstance().postOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(url)) {
                    view.setImageBitmap(null);
                    return;
                }
                String temp = url;
                if (url.startsWith("//")) {
                    temp = "http:" + url;
                }
                if (view.getLayoutParams().width <= 0 || view.getLayoutParams().height <= 0) {
                    return;
                }

                Glide.with(WXEnvironment.getApplication())
                        .load(temp)
                        .into(view);
            }
        }, 0);
    }
}
