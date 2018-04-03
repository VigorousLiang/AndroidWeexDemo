package com.vigorous.weexdemo.util;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class SharedPreferencesUtil {

    private static SharedPreferencesUtil instance;
    private SharedPreferences settings;

    private SharedPreferencesUtil(Context context) {
        settings = context.getSharedPreferences("YYZ_PLUS", Context.MODE_PRIVATE);
    }

    public static SharedPreferencesUtil getInstance(Context context) {

        if (instance == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (context.getApplicationContext() != null) {
                    instance = new SharedPreferencesUtil(context.getApplicationContext());
                } else {
                    instance = new SharedPreferencesUtil(context);
                }
            }
        }

        return instance;
    }

    public void saveString(String key, String value) {
        synchronized (settings) {
            settings.edit().putString(key, value).apply();
        }
    }

    public void saveStringAsync(String key, String value) {
        synchronized (settings) {
            settings.edit().putString(key, value).apply();
        }
    }

    public String getString(String key) {
        synchronized (settings) {
            return settings.getString(key, "");
        }
    }

    public String getString(String key, String defaultValue) {
        synchronized (settings) {
            return settings.getString(key, defaultValue);
        }
    }

    public void saveString(Map<String, String> map) {
        synchronized (settings) {
            for (String key : map.keySet()) {
                settings.edit().putString(key, map.get(key)).apply();
            }
        }
    }

    public void saveInt(String key, int value) {
        synchronized (settings) {
            settings.edit().putInt(key, value).apply();
        }
    }

    public int getInt(String key, int defaultValue) {
        synchronized (settings) {
            return settings.getInt(key, defaultValue);
        }
    }


    public void saveBoolean(String key, boolean bool) {
        synchronized (settings) {
            settings.edit().putBoolean(key, bool).apply();
        }
    }

    public boolean getBoolean(String key) {
        synchronized (settings) {
            return settings.getBoolean(key, false);
        }
    }

    public boolean getBoolean(String key, boolean is) {
        synchronized (settings) {
            return settings.getBoolean(key, is);
        }
    }

    public <T> void saveHashMap(final String key, HashMap<String, T> map) {
        final JSONObject ret = new JSONObject(map);
        synchronized (settings) {
            settings.edit().putString(key, ret.toString()).apply();
        }
    }

    public <T> HashMap<String, T> getHashMapByKey(String key) {
        HashMap<String, T> ret = new HashMap<>();
        synchronized (settings) {
            String mapStr = settings.getString(key, "{}");
            JSONObject mapJson = null;
            try {
                mapJson = new JSONObject(mapStr);
            } catch (Exception e) {
                return ret;
            }

            if (mapJson != null) {
                Iterator<String> it = mapJson.keys();
                while (it.hasNext()) {
                    String theKey = it.next();
                    T theValue = null;
                    try {
                        theValue = (T) mapJson.get(theKey);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ret.put(theKey, theValue);
                }
            }
        }
        return ret;
    }


    public void saveLong(String key, long value) {
        synchronized (settings) {
            settings.edit().putLong(key, value).apply();
        }
    }

    public long getLong(String key, long defaultValue) {
        synchronized (settings) {
            return settings.getLong(key, defaultValue);
        }
    }

    public void removeByKey(String key) {
        settings.edit().remove(key).apply();
    }

    public boolean contains(String key) {
        return settings.contains(key);
    }

    public Map<String,?> getAll(){
        return settings.getAll();
    }
}
