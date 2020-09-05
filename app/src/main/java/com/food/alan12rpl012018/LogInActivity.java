package com.food.alan12rpl012018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONException;
import org.json.JSONObject;

public class LogInActivity extends AppCompatActivity {
    TextView textView,forget; Button button; EditText txtemail,txtpassword;
    public static final String SHARED_PREFS = "sharedPrefs";
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
                AndroidNetworking.post("192.168.43.65/tugas_api/login.php")
                        .addBodyParameter("email", email)
                        .addBodyParameter("password", password)
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String status = response.getString("status");
                                    String message = response.getString("message");
                                    if (status.equals("success")) {
                                        JSONObject data = response.getJSONObject("data");
                                        String name = data.getString("name");
                                        String id = data.getString("id");

                                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();

                                        editor.putString(ID, id);
                                        editor.apply();

                                        Intent intent = new Intent(LogInActivity.this, DashboardActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);

                                        Toast.makeText(LogInActivity.this, "Selamat datang " + name, Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(LogInActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
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
