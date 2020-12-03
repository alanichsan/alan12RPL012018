package com.food.alan12rpl012018;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ModelAdapter adapter;
    private List<ModelAdapter> models = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new ModelAdapter(getApplicationContext(),models);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = findViewById(R.id.refreshlist);

        swipeRefreshLayout.setRefreshing(false);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                models.clear();
                getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    private void getData(){
        AndroidNetworking.post("http://192.168.43.65/sekolah/alan12RPL012018API/show_master.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            String status = response.getString("STATUS");
                            String message = response.getString("MESSAGE");
                            if (status.equals("SUCCESS")) {
                                JSONObject data = response.getJSONObject("PAYLOAD");
                                JSONArray customers = data.getJSONArray("DATA");
                                for (int i = 0; i<customers.length(); i++){
                                    JSONObject payload = customers.getJSONObject(i);
                                    String id = payload.optString("ID");
                                    String kode = payload.optString("KODE");
                                    String jenis = payload.optString("JENIS");
                                    String merk = payload.optString("MERK");
                                    String hargasewa = payload.optString("HARGASEWA");
                                    String warna = payload.optString("WARNA");

                                    ModelList cum = new ModelList();
                                    cum.setId(id);
                                    cum.setCode(kode);
                                    cum.setJenis(jenis);
                                    cum.setMerk(merk);
                                    cum.setProfile(R.drawable.customer);
                                    cum.setHargasewa(hargasewa);
                                    cum.setWarna(warna);

                                    models.add(cum);
                                    System.out.println("opo" + models.size());
                                }
                                ModelAdapter.notifyDataSetChanged();

                                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("ALN", String.valueOf(response));
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.i("anError", "["+error+"]");
                        Log.d("anError", error.getLocalizedMessage());
                    }
                });
    }
    public void editData(String id, String kode, String jenis, String hargasewa,String warna,String merk) {
        HashMap<String, String> body = new HashMap<>();
        body.put("id_auth", "2");
        body.put("id", id);
        body.put("kode", kode);
        body.put("jenis", jenis);
        body.put("warna", warna);
        body.put("merk", merk);
        body.put("hargasewa", hargasewa);

        AndroidNetworking.post("http://192.168.43.65/sekolah/alan12RPL012018API/edit_master.php")
                .addBodyParameter(body)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("ABR", "respon : " + response);
                        String status = response.optString("STATUS");
                        String message = response.optString("MESSAGE");
                        if (status.equalsIgnoreCase("SUCCESS")) {
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,ListCustomerActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MainActivity.this, "Kesalahan Internal", Toast.LENGTH_SHORT).show();
                        Log.d("ALN", "onError: " + anError.getErrorBody());
                        Log.d("ALN", "onError: " + anError.getLocalizedMessage());
                        Log.d("ALN", "onError: " + anError.getErrorDetail());
                        Log.d("ALN", "onError: " + anError.getResponse());
                        Log.d("ALN", "onError: " + anError.getErrorCode());
                        Log.d("ALN", "onError: " + anError.toString());
                    }
                });
    }

    public void deleteData(String id) {
        HashMap<String, String> body = new HashMap<>();
        body.put("id", id);
        AndroidNetworking.post("http://192.168.43.65/sekolah/alan12RPL012018API/delete_master.php")
                .addBodyParameter(body)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("ABR", "respon : " + response);
                        String status = response.optString("STATUS");
                        String message = response.optString("MESSAGE");
                        if (status.equalsIgnoreCase("SUCCESS")) {
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,ListCustomerActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MainActivity.this, "Kesalahan Internal", Toast.LENGTH_SHORT).show();
                        Log.d("ALN", "onError: " + anError.getErrorBody());
                        Log.d("ALN", "onError: " + anError.getLocalizedMessage());
                        Log.d("ALN", "onError: " + anError.getErrorDetail());
                        Log.d("ALN", "onError: " + anError.getResponse());
                        Log.d("ALN", "onError: " + anError.getErrorCode());
                    }
                });
    }
}
