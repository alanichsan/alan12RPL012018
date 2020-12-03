package com.food.alan12rpl012018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.LocaleData;
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

import java.util.HashMap;

public class LogInActivity extends AppCompatActivity {
    TextView textView,forget; Button button; EditText txtemail,txtpassword;
    public static final String SHARED_PREFS = "sharedPrefs";
    private  SharedPreferences preferences;
    public static final String ID = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        txtemail = findViewById(R.id.email);
        txtpassword = findViewById(R.id.password);
        textView = findViewById(R.id.register);
        button = findViewById(R.id.login);
        forget = findViewById(R.id.forget);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
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

                HashMap<String, String> body = new HashMap<>();
                body.put("email", email);
                body.put("password", password);

                AndroidNetworking.post("http://192.168.43.65/sekolah/alan12RPL012018API/login.php")
                        .addBodyParameter(body)
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("ALN" , response.toString());
                                String status = response.optString("STATUS");
                                String message = response.optString("MESAGGE");
                                if (status.equalsIgnoreCase("SUCCESS")) {
                                    JSONObject payload = response.optJSONObject("PAYLOAD");
                                    String LOGIN_ID = payload.optString("LOGIN_ID");
                                    String LOGIN_NAME = payload.optString("LOGIN_NAME");
                                    String NOMOR_HP = payload.optString("NOMOR_HP");
                                    String NOMOR_KTP = payload.optString("NOMOR_KTP");
                                    String ALAMAT = payload.optString("ALAMAT");
                                    String ROLE = payload.optString("ROLE");

                                    preferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
                                    preferences.edit()
                                            .putString("LOGIN_ID", LOGIN_ID)
                                            .putString(LOGIN_NAME, LOGIN_NAME)
                                            .putString(NOMOR_HP, NOMOR_HP)
                                            .putString(NOMOR_KTP, NOMOR_KTP)
                                            .putString(ALAMAT, ALAMAT)
                                            .putString(ROLE, ROLE)
                                            .apply();
                                    if (ROLE.equalsIgnoreCase("Customer")) {
                                        Intent intent = new Intent(LogInActivity.this, DashboardActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(LogInActivity.this, message, Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else if (ROLE.equalsIgnoreCase("admin")){
                                        Intent intent = new Intent(LogInActivity.this, DashboardActivity2.class);
                                        startActivity(intent);
                                        Toast.makeText(LogInActivity.this, message, Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                                else {
                                    Toast.makeText(LogInActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onError(ANError error) {
                                Log.d("anError", error.getLocalizedMessage());
                                Toast.makeText(getApplicationContext(),"Gagal",Toast. LENGTH_SHORT).show();
                            }
                        });
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
