package com.food.alan12rpl012018;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ForgetPassActivity extends AppCompatActivity {
    private TextView textView; Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
         textView= findViewById(R.id.back);
         button = findViewById(R.id.enter);
         textView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(ForgetPassActivity.this,LogInActivity.class);
                 startActivity(intent);
             }
         });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "your request has been sent", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}