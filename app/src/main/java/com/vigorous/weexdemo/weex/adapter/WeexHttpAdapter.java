package com.vigorous.weexdemo.weex.adapter;

import android.annotation.TargetApi;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.taobao.weex.adapter.IWXHttpAdapter;
import com.taobao.weex.common.WXRequest;
import com.taobao.weex.common.WXResponse;


import java.io.IOException;
import java.util.Set;

/**
 * Created by vigorousliang
 * Created on 2018/3/6
 */

public class WeexHttpAdapter implements IWXHttpAdapter {

    private OkHttpClient mOkHttpClient;

    public WeexHttpAdapter(OkHttpClient okHttpClient) {
        mOkHttpClient = okHttpClient;
    }

    @Override
    public void sendRequest(WXRequest request, OnHttpListener listener) {

        if (listener != null) {
            listener.onHttpStart();
        }

        if (request == null) {
            if (listener != null) {
                WXResponse wxResponse = new WXResponse();
                wxResponse.errorMsg = "WXRequest为空";
                listener.onHttpFinish(wxResponse);
            }
            return;
        }

        Request okHttpRequest;
        if ("POST".equalsIgnoreCase(request.method)) {
            okHttpRequest = (new Request.Builder())
                    .headers(getHeaders(request))
                    .url(request.url)
                    .post(RequestBody.create(MediaType.parse(request.body), request.body))
                    .build();
        } else {
            okHttpRequest = (new Request.Builder()).url(request.url).build();
        }

        mOkHttpClient.newCall(okHttpRequest)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        if (listener != null) {
                            WXResponse wxResponse = new WXResponse();
                            wxResponse.errorCode = String.valueOf(-100);
                            wxResponse.statusCode = String.valueOf(-100);
                            wxResponse.errorMsg = e.getMessage();
                            try {
                                listener.onHttpFinish(wxResponse);
                            } catch (Exception e1) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        if (listener != null) {
                            WXResponse wxResponse = new WXResponse();
                            wxResponse.statusCode = String.valueOf(response.code());
                            ResponseBody body = response.body();
                            if (body == null) {
                                wxResponse.errorMsg = "body为空";
                            } else {
                                wxResponse.originalData = body.bytes();
                            }
                            try {
                                listener.onHttpFinish(wxResponse);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    @TargetApi(24)
    private Headers getHeaders(WXRequest request) {
        Headers.Builder builder = new Headers.Builder();
        if (request.paramMap != null) {
            Set<String> keySet = request.paramMap.keySet();
            keySet.forEach(key -> builder.add(key, request.paramMap.get(key)));
        }
        return builder.build();
    }

}
