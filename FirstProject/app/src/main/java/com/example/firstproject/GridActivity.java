package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class GridActivity extends AppCompatActivity {

    GridView gridView;
    String[]brands={"Blackberry","Samsung","Vivo","Xiamo","Pixel","Oneplus"};
    Integer[] logos = {R.drawable.blackberry,R.drawable.samsung,R.drawable.vivo,R.drawable.xiamo,R.drawable.pixel,R.drawable.oneplus};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        //set ids
        gridView = findViewById(R.id.gridview);


        CustomGridAdapter adapter = new CustomGridAdapter(this,brands,logos);
        gridView.setAdapter(adapter);
    }
}