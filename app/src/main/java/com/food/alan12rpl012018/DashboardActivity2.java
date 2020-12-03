package com.food.alan12rpl012018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DashboardActivity2 extends AppCompatActivity {
    TextView textView2,textView,btnlogout;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);
        textView = findViewById(R.id.customer);
        textView2 = findViewById(R.id.store);
        btnlogout = findViewById(R.id.btnlogout);
        sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity2.this,ListCustomerActivity.class);
                startActivity(intent);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity2.this,ListCustomerActivity.class);
                startActivity(intent);
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity2.this, LogInActivity.class);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(intent);
                finish();
            }
        });

    }
}