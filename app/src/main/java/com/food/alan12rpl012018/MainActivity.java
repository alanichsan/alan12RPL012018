package com.food.alan12rpl012018;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import java.util.ArrayList;
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

    void getData(){

    }
}
