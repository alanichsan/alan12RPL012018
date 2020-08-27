package com.food.alan12rpl012018;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ModelAdapter adapter;
    private ArrayList<ModelList> modelListArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new ModelAdapter(modelListArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    void addData(){
        modelListArrayList = new ArrayList<>();
        modelListArrayList.add(new ModelList("sepeda1", "polygon", "12", "4567"));
        modelListArrayList.add(new ModelList("sepeda2", "polygon", "14", "4789"));
        modelListArrayList.add(new ModelList("sepeda3", "polygon", "23", "1234"));
        modelListArrayList.add(new ModelList("sepeda4", "polygon", "3", "6789"));
        modelListArrayList.add(new ModelList("sepeda4", "polygon", "3", "6789"));
        modelListArrayList.add(new ModelList("sepeda2", "polygon", "14", "4789"));
        modelListArrayList.add(new ModelList("sepeda1", "polygon", "12", "4567"));
        modelListArrayList.add(new ModelList("sepeda4", "polygon", "3", "6789"));

    }
}
