package com.vigorous.weexdemo.weex.jump;

/**
 * Created by vigorousliang
 * Created on 2018/3/16
 */

public enum WeexJumpType {

    WEEX_JUMP_NATIVE("weex_jump_native",1),
    WEEX_JUMP_H5("weex_jump_h5",2),
    NATIVE_JUMP_WEEX("native_jump_weex",3);

    private String type;
    private int index;

    // 构造方法
    private WeexJumpType(String type, int index) {
        this.type = type;
        this.index = index;
    }

    public String getTypeName(){
        return type;
    }

    public int getTypeInt(){
        return index;
    }
}
