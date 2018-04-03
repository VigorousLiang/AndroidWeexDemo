package com.vigorous.weexdemo.weex.component;

import android.content.Context;
import android.view.MotionEvent;

import com.taobao.weex.ui.view.WXFrameLayout;

/**
 * Created by vigorousliang
 * Created on 2018/3/19
 */

public class WXFloatFrameLayout extends WXFrameLayout {
    private FloatViewInterface mFloatWindow;
    private boolean isIntercept=true;

    public WXFloatFrameLayout(Context context, FloatViewInterface floatWindow) {
        super(context);
        this.mFloatWindow = floatWindow;
    }

    public FloatViewInterface getFloatWindow() {
        return mFloatWindow;
    }

    public void setFloatWindow(FloatViewInterface floatWindow) {
        mFloatWindow = floatWindow;
    }

    public boolean isIntercept() {
        return isIntercept;
    }

    public void setIntercept(boolean intercept) {
        isIntercept = intercept;
    }



    public WXFloatFrameLayout(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(isIntercept){
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

}
