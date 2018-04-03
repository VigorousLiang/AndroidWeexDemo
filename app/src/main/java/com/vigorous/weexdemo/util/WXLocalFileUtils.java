package com.vigorous.weexdemo.util;

import android.content.Context;
import android.text.TextUtils;

import com.taobao.weex.utils.WXLogUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by vigorousliang
 * Created on 2018/3/19
 */

public class WXLocalFileUtils {

    /**
     * Load file in asset directory.
     * @param absolutePath FilePath
     * @param context Weex Context
     * @return the Content of the file
     */
    public static String loadPrivateDirBundleJS(String absolutePath, Context context) {
        if (context == null || TextUtils.isEmpty(absolutePath)) {
            return null;
        }
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            File file = new File(absolutePath);
            inputStream = new FileInputStream(file);
            StringBuilder builder = new StringBuilder(inputStream.available() + 10);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] data = new char[4096];
            int len = -1;
            while ((len = bufferedReader.read(data)) > 0) {
                builder.append(data, 0, len);
            }

            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            WXLogUtils.e("", e);
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                WXLogUtils.e("WXFileUtils loadAsset: ", e);
            }
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                WXLogUtils.e("WXFileUtils loadAsset: ", e);
            }
        }

        return "";
    }
}
