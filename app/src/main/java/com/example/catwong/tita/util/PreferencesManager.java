package com.example.catwong.tita.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.example.catwong.tita.common.CommonKey.PREF_NAME;

/**
 * Created by orangex on 2017/2/22.
 */

public class PreferencesManager {


    private static volatile PreferencesManager sInstance;
    private final SharedPreferences mPref;


    private PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    public static PreferencesManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (PreferencesManager.class) {
                if (sInstance == null) {
                    sInstance = new PreferencesManager(context);
                }
            }
        }
        return sInstance;
    }

    public void setBoolean(String key, boolean value) {

        mPref.edit().putBoolean(key, value).apply();

    }


    public boolean getBoolean(String key,boolean def) {
        return mPref.getBoolean(key, def);
    }

    public void putString(String key, String value) {
        mPref.edit().putString(key, value).apply();
    }

    public String getString (String key, String def) {
        return mPref.getString(key, def);
    }

}
