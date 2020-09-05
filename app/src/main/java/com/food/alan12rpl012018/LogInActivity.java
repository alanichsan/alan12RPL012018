package com.food.alan12rpl012018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class LogInActivity extends AppCompatActivity {
    TextView textView,forget; Button button; EditText txtemail,txtpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        txtemail = findViewById(R.id.email);
        txtpassword = findViewById(R.id.password);
        textView = findViewById(R.id.register);
        button = findViewById(R.id.login);
        forget = findViewById(R.id.forget);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtemail.getText().toString().trim();
                String password = txtpassword.getText().toString().trim();

                boolean isEmpty = false;

                if (email.isEmpty()) {
                    isEmpty = true;
                    txtemail.setError("Email harus diisi");
                }

                if (password.isEmpty()) {
                    isEmpty = true;
                    txtpassword.setError("Password harus diisi");
                }

                if (!isEmpty) {
                AndroidNetworking.post("192.168.6.89/tugas_api/login.php")
                        .addBodyParameter("email", email)
                        .addBodyParameter("password", password)
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("test", String.valueOf(response));

                                Intent intent = new Intent(LogInActivity.this,DashboardActivity.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(),"Succes",Toast.LENGTH_LONG).show();
                                finish();
                            }
                            @Override
                            public void onError(ANError error) {
                                Log.d("anError", error.getLocalizedMessage());
                                Toast.makeText(getApplicationContext(),"Gagal",Toast.LENGTH_LONG).show();
                            }
                        });
            }
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this,ForgetPassActivity.class);
                startActivity(intent);
            }
        });
    }
}
