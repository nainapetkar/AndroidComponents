package com.util.androidcomponents;

import android.content.Context;

public class SharedPreferencesUtil {
    private android.content.SharedPreferences preferences;
    private android.content.SharedPreferences.Editor editor;


    public SharedPreferencesUtil(Context _context)  {
        Context context = _context;
        String SESSION_NAME = "INeed";
        preferences = context.getSharedPreferences(SESSION_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setObjectAsString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getObjectAsString(String key, String defaultvalue) {
        return preferences.getString(key, defaultvalue);
    }

    public void setObjectAsInteger(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getObjectAsInteger(String key, int defaultvalue) {
        return preferences.getInt(key, defaultvalue);
    }

    public void removeObject(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void setObjectAsBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getObjectAsBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public void setObjectAsDouble(String key , double value ){
        editor.putFloat(key, Double.doubleToRawLongBits(value));
        editor.commit();
    }

    public Double getObjectAsDouble(String key ,float defaultVal){
        return Double.longBitsToDouble(preferences.getLong(key, Double.doubleToRawLongBits(defaultVal)));
    }

    public void removeSharedPref(){
        editor.clear();
        editor.apply();
    }
}
