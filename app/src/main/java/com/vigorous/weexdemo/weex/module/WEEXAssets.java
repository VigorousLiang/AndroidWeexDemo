package com.vigorous.weexdemo.weex.module;

import android.util.Log;

import com.google.gson.Gson;
import com.vigorous.weexdemo.model.StocksAssets;
import com.vigorous.weexdemo.model.TotalAssets;
import com.vigorous.weexdemo.model.base.BaseResp;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

/**
 * Created by vigorousliang
 * Created on 2018/3/13
 */

public class WEEXAssets extends WXModule {
    private final static String TAG = com.vigorous.weexdemo.weex.module.WEEXAssets.class.getName();

    @JSMethod(uiThread = false)
    public void getTotalAssets(JSCallback callback) {
        //fixme mock data
        TotalAssets totalAssets = new TotalAssets();
        totalAssets.setAccountNumber("123456");
        totalAssets.setBankName("建设银行");
        totalAssets.setTotalAssets("10000");
        totalAssets.setCurrency("0");
        totalAssets.setFundBalance("5555.58");
        totalAssets.setRepurchaseAvailableFund("19.48");
        totalAssets.setLastestMarketValue("10000.00");
        totalAssets.setMainAccountType("主");
        totalAssets.setPendingSecAssets("1222.22");
        totalAssets.setAvailableFund("23456.33");
        totalAssets.setWithdrawash("2000.00");
        totalAssets.setProfitOrLossAmount("5324.23");

        Object[] data = new Object[1];
        data[0] = totalAssets;
        BaseResp baseResp = new BaseResp();
        baseResp.setData(data);

        Gson gson = new Gson();
        String result = gson.toJson(baseResp);
        Log.e(TAG, "getTotalAssets" + result);
        callback.invoke(result);
    }


    @JSMethod(uiThread = false)
    public void getStocksAssets(int pageSize, int pageNum, JSCallback callback) {
        //fixme mock data
        StocksAssets stocksAssets = new StocksAssets();
        stocksAssets.setAccountType("2");
        stocksAssets.setProfitOrLossAmount("27.00");
        stocksAssets.setStockAmount("100");
        stocksAssets.setStocksBalance("100");
        stocksAssets.setCurrency("0");
        stocksAssets.setStocksExchange("深圳A股");
        stocksAssets.setBuyAverageCost("5.740");
        stocksAssets.setCost("5.740");
        stocksAssets.setCurrentPrice("6.010");
        stocksAssets.setStockName("京东方");
        stocksAssets.setStockCode("000783");
        stocksAssets.setStockType("0");
        stocksAssets.setProfitOrLossRatio("4.70%");
        stocksAssets.setLatestMarketPrice("601.00");
        stocksAssets.setMarketableStockAmount("100");

        Object[] data = new Object[9];
        for(int i=0;i<9;i++){
            data[i] = stocksAssets;
        }
        BaseResp baseResp = new BaseResp();
        baseResp.setData(data);

        Gson gson = new Gson();
        String result = gson.toJson(baseResp);
        Log.e(TAG, "getStocksAssets" + result);
        callback.invoke(result);
    }


}
