package com.vigorous.weexdemo.weex.component;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.widget.TextView;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixinke on 2017/2/17.
 */

public class RichText extends WXComponent<TextView> {

    private WXSDKInstance mInstance;

    public RichText(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
        super(instance, dom, parent);
        mInstance = instance;
    }

    @Override
    protected TextView initComponentHostView(@NonNull Context context) {
        TextView textView = new TextView(context);
        textView.setTextSize(15);
        textView.setTextColor(Color.BLACK);
        return textView;
    }

    @WXComponentProp(name = "tel")
    public void setTel(String number) {
        SpannableString spannableString = new SpannableString(number);
        spannableString.setSpan(new URLSpan("tel:" + number), 0, number.length(), Spanned
                .SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, number.length(), Spanned
                .SPAN_EXCLUSIVE_EXCLUSIVE);
        getHostView().setText(spannableString);

        HashMap<String, Object> parm = new HashMap<>();
        //mInstance 是WXSDKInstance对象
        mInstance.fireGlobalEventCallback("gtja_weex_demo", parm);


    }

    public void fireSuperEventCallback(String eventName, Map<String, Object> params) {
        List<WXSDKInstance> instances = WXSDKManager.getInstance().getWXRenderManager()
                .getAllInstances();
        for (WXSDKInstance instance : instances) {
            instance.fireGlobalEventCallback(eventName, params);
        }
    }
}
