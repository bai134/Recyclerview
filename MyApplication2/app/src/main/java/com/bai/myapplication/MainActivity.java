package com.bai.myapplication;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> data;
    private RecyclerView view;
    private adapater adapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.recycler);
        data = new ArrayList<>();
        for (int i =0;i<20;i++){
            data.add("fjalf;adflajfk;aj;fdj;af");
        }

        adapater = new adapater(MainActivity.this,data);
        view.setAdapter(adapater);
//        view.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        view.setLayoutManager(new LinearLayoutManager(this));
        view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0, 0, 0, 20);
            }
        });
//        CustomSnapHelper mMySnapHelper = new CustomSnapHelper();
//        mMySnapHelper.attachToRecyclerView(view);
    }
}
