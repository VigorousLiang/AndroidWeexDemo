package com.vigorous.weexdemo.weex.component;

import android.view.View;
import android.view.WindowManager;

/**
 * Created by vigorousliang
 * Created on 2018/3/19
 */

public interface FloatViewInterface {
    void init(WindowManager windowManager, View windowView);
    void show();
    void hide();
}
