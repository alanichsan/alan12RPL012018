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

import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ID = "id";
    public static final String USERNAME = "username";
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textView = findViewById(R.id.login);
        final EditText txtemail = findViewById(R.id.email);
        final EditText txtusername1 = findViewById(R.id.username);
        final EditText txtnohp = findViewById(R.id.nohp);
        final EditText txtnoktp = findViewById(R.id.noktp);
        final EditText txtalamat = findViewById(R.id.address);
        final EditText txtpass1 = findViewById(R.id.password1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LogInActivity.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtemail.getText().toString();
                String username1 = txtusername1.getText().toString();
                String nohp = txtnohp.getText().toString();
                String noktp = txtnoktp.getText().toString();
                String alamat = txtalamat.getText().toString();
                String pass = txtpass1.getText().toString();

                HashMap<String, String> body = new HashMap<>();
                body.put("noktp", noktp);
                body.put("email", email);
                body.put("password", pass);
                body.put("nama", username1);
                body.put("nohp", nohp);
                body.put("alamat", alamat);
                body.put("role", "1");
                AndroidNetworking.post("http://192.168.6.89/tugas_api/register.php")
                        .addBodyParameter(body)
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("ALN" , response.toString());
                                String status = response.optString("status");
                                String message = response.optString("mesagge");
                                if (status.equalsIgnoreCase("SUCCESS")) {
                                    Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(RegisterActivity.this, "Kesalahan Internal", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
