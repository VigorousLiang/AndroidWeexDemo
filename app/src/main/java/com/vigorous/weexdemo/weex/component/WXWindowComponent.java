package com.vigorous.weexdemo.weex.component;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXPerformance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXDiv;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;

/**
 * Created by vigorousliang
 * Created on 2018/3/19
 */

//浮窗的weex组件
@Component(lazyload = false)
public class WXWindowComponent extends WXDiv implements WXSDKInstance.OnInstanceVisibleListener,
        View.OnTouchListener, FloatViewInterface {

    private WXSDKInstance mViewInstance;
    private String src;
    private boolean mIsVisible = true;
    private String originUrl;
    private FloatViewRenderListener mListener;
    private WindowManager mWm;
    private View mWindowView;
    private WindowManager.LayoutParams mLayoutParams;
    private int mGravity = Gravity.CENTER;
    private int mFlag = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
            | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
    private float mTouchX;
    private float mTouchY;
    private int mLeft = 0;
    private int mTop = 0;
    private int mDeviceWidth;
    private boolean mDisableFloat = false;

    public WXWindowComponent(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
        mListener = new FloatViewRenderListener(this);

    }


    private void updateViewPosition() {
        this.mLayoutParams.x = (int) (mTouchX - mLeft);
        this.mLayoutParams.y = (int) (mTouchY - mTop);
        mWm.updateViewLayout(mWindowView, this.mLayoutParams);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        getHostView().requestDisallowInterceptTouchEvent(true);
        if (mDisableFloat) {
            return false;
        }
        mTouchX = event.getRawX();
        mTouchY = event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                updateViewPosition();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mTouchX >= this.mDeviceWidth >> 1) {
                    mTouchX = this.mDeviceWidth;
                } else {
                    mTouchX = 0;
                }
                updateViewPosition();
                break;
        }
        return true;
    }

    public void init(WindowManager windowManager, View windowView) {
        this.mDeviceWidth = WXViewUtils.getScreenWidth(getContext());
        this.mLayoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        this.mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        this.mLayoutParams.format = PixelFormat.TRANSLUCENT;
        this.mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;
        this.mLayoutParams.gravity = this.mGravity;
        this.mLayoutParams.flags = this.mFlag;
        this.mWm = windowManager;
        this.mWindowView = windowView;
        ((WXFloatFrameLayout) getHostView()).setIntercept(true);
        this.mWindowView.setOnTouchListener(this);
    }

    void loadInstance() {
        mViewInstance = createInstance();
    }

    @Override
    public void onAppear() {
        if (mIsVisible && mViewInstance != null) {
            WXComponent comp = mViewInstance.getRootComponent();
            show();
            if (comp != null) {
                mViewInstance.fireEvent(comp.getRef(), Constants.Event.VIEWAPPEAR, null, null);
            }
        }
    }

    @Override
    public void onDisappear() {
        if (mIsVisible && mViewInstance != null) {
            WXComponent comp = mViewInstance.getRootComponent();
            hide();
            if (comp != null)
                mViewInstance.fireEvent(comp.getRef(), Constants.Event.VIEWDISAPPEAR, null, null);
        }
    }

    public void renderNewURL(String url) {
        this.src = url;
        loadInstance();
    }

    public ViewGroup getViewContainer() {
        return getHostView();
    }

    private WXSDKInstance createInstance() {
        WXSDKInstance sdkInstance = new WXSDKInstance(getContext());
        getInstance().addOnInstanceVisibleListener(this);
        sdkInstance.registerRenderListener(mListener);
        final String url = src;
        if (TextUtils.isEmpty(url)) {
            return sdkInstance;
        }
        ViewGroup.LayoutParams layoutParams = getHostView().getLayoutParams();
        sdkInstance.renderByUrl(WXPerformance.DEFAULT,
                url,
                null, null, layoutParams.width,
                layoutParams.height,
                WXRenderStrategy.APPEND_ASYNC);
        return sdkInstance;
    }

    static class FloatViewRenderListener implements IWXRenderListener {
        WXWindowComponent mComponent;

        public FloatViewRenderListener(WXWindowComponent wxWindowComponent) {
            this.mComponent = wxWindowComponent;
        }

        @Override
        public void onViewCreated(WXSDKInstance instance, View view) {
            FrameLayout hostView = this.mComponent.getHostView();
            view.invalidate();
            hostView.removeAllViews();
            hostView.addView(view);
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

    @Override
    protected boolean setProperty(String key, Object param) {
        switch (key) {
            case Constants.Name.SRC:
                String src = WXUtils.getString(param, null);
                if (src != null)
                    setSrc(src);
                return true;
        }
        return super.setProperty(key, param);
    }

    @WXComponentProp(name = Constants.Name.SRC)
    public void setSrc(String src) {
        originUrl = this.src;
        this.src = src;
        if (mViewInstance != null) {
            mViewInstance.destroy();
            mViewInstance = null;
        }
        loadInstance();
    }

    @WXComponentProp(name = "GRAVITY")
    public void setGravity(int gravity) {
        this.mGravity = gravity;
        this.mLayoutParams.gravity = this.mGravity;
        show();
    }

    @WXComponentProp(name = "DISPlAY_WINDOW")
    public void displayWindow(boolean displayWindow) {
        if (displayWindow) {
            show();
        } else {
            hide();
        }
    }

    @WXComponentProp(name = "DISABLE_FLOAT")
    public void disableFloat(boolean disableFloat) {
        this.mDisableFloat = disableFloat;
        if (this.mDisableFloat) {
            ((WXFloatFrameLayout) getHostView()).setIntercept(false);
        } else {
            ((WXFloatFrameLayout) getHostView()).setIntercept(true);
        }
    }

    @WXComponentProp(name = "FLAG")
    public void setFlag(int flag) {
        this.mFlag = flag;
    }

    public void show() {
        if (this.mWm == null) {
            return;
        }
        if (this.mWindowView.getParent() != null) {
            if (this.mWindowView.getParent() != null) {
                this.mWm.removeView(mWindowView);
            }
        }
        this.mWm.addView(this.mWindowView, this.mLayoutParams);
        this.mWindowView.post(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                mWindowView.getLocationOnScreen(location);
                mLeft = location[0] + (mWindowView.getWidth() >> 1);
                mTop = location[1] + (mWindowView.getHeight() >> 1);
            }
        });
    }

    public void hide() {
        if (this.mWm == null) {
            return;
        }
        if (this.mWindowView != null && this.mWindowView.getParent() != null) {
            this.mWm.removeView(this.mWindowView);
            this.mLayoutParams.x = 0;
            this.mLayoutParams.y = 0;
        }
    }

    public String getSrc() {
        return src;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    @Override
    public void destroy() {
        super.destroy();
        hide();
        if (mViewInstance != null) {
            mViewInstance.destroy();
            mViewInstance = null;
        }
        src = null;
    }

    @Override
    protected WXFloatFrameLayout initComponentHostView(@NonNull Context context) {
        return new WXFloatFrameLayout(context, this);
    }

}