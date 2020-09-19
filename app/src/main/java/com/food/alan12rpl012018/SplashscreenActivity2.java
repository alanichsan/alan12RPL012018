package com.food.alan12rpl012018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import com.wang.avi.AVLoadingIndicatorView;

public class SplashscreenActivity2 extends AppCompatActivity {
    private static int SplashScreen = 1150;

    private String id;
    //pembuatan variabel untuk linearlayout
    private LinearLayout lv_loading;
    private AVLoadingIndicatorView avi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen2);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        id = sharedPreferences.getString("id", "");
        lv_loading = (LinearLayout) findViewById(R.id.lv_loading);
        avi= (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator("BallClipRotateMultipleIndicator");
        //membuat sebuah proses yang ter delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (id.isEmpty()) {
                    Intent intent = new Intent(SplashscreenActivity2.this, LogInActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashscreenActivity2.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SplashScreen);
    }
}