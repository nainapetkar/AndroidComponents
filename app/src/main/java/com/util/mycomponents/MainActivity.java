package com.util.mycomponents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.util.androidcomponents.NetworkControllerUtil;
import com.util.androidcomponents.SharedPreferencesUtil;

public class MainActivity extends AppCompatActivity {

    private SharedPreferencesUtil sharedPreferencesUtils;
    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){

        if (!NetworkControllerUtil.getInstance().isNetworkConnected()) {
            Toast.makeText(this, "Device is not connected to internet", Toast.LENGTH_SHORT).show();
        }
        sharedPreferencesUtils = new SharedPreferencesUtil(this);
        sharedPreferencesUtils.setObjectAsBoolean("isLoggedIn",true);
        boolean isLoggedIn = sharedPreferencesUtils.getObjectAsBoolean("isLoggedIn");
        Log.d(TAG, "init(): isLoggedIn=" + isLoggedIn);
        sharedPreferencesUtils.removeSharedPref();
    }
}