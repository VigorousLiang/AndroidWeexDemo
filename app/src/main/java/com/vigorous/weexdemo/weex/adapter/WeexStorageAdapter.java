package com.vigorous.weexdemo.weex.adapter;

import android.content.Context;

import com.vigorous.weexdemo.util.SharedPreferencesUtil;
import com.taobao.weex.appfram.storage.IWXStorageAdapter;

import java.util.HashMap;

/**
 * Created by vigorousliang
 * Created on 2018/3/16
 */

public class WeexStorageAdapter implements IWXStorageAdapter {

    private Context mContext;
    private SharedPreferencesUtil mSPUtil;
    private HashMap<String,Object> memoryData=new HashMap<>();

    public WeexStorageAdapter(Context context){
        if(context!=null){
            mContext=context;
            mSPUtil=SharedPreferencesUtil.getInstance(mContext.getApplicationContext());
            try {
                memoryData = (HashMap<String, Object>) mSPUtil.getAll();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setItem(String key, String value, OnResultReceivedListener listener) {
        memoryData.put(key,value);
        listener.onReceived(memoryData);
    }

    @Override
    public void getItem(String key, OnResultReceivedListener listener) {
        HashMap<String, Object> temp = new HashMap<>();
        if(memoryData.containsKey(key)) {
            Object result=memoryData.get(key);
            temp.put(key,result);
        }
        listener.onReceived(temp);
    }

    @Override
    public void removeItem(String key, OnResultReceivedListener listener) {
        if(memoryData.containsKey(key)) {
            memoryData.remove(key);
        }
        listener.onReceived(memoryData);
    }

    @Override
    public void length(OnResultReceivedListener listener) {
        listener.onReceived(memoryData);
    }

    @Override
    public void getAllKeys(OnResultReceivedListener listener) {
        listener.onReceived(memoryData);
    }

    @Override
    public void setItemPersistent(String key, String value, OnResultReceivedListener listener) {
        if(mSPUtil!=null){
            mSPUtil.saveString(key,value);
        }
        setItem(key,value,listener);
    }

    @Override
    public void close() {

    }
}
