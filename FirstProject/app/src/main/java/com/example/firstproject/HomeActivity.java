package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView profileTxt;
    String user,mob;
    Button sql_btn,fragmentBtn,videoBtn,audioBtn,recorder_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //set ids

        profileTxt = findViewById(R.id.profile_txt);
        sql_btn = findViewById(R.id.sql_btn);
        fragmentBtn=findViewById(R.id.fragment_btn);
        videoBtn  =findViewById(R.id.video_btn);
        audioBtn=findViewById(R.id.audio_btn);
      recorder_btn=findViewById(R.id.record_btn);

        user = getIntent().getStringExtra("username");
        mob = getIntent().getStringExtra("mobile");

       recorder_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(HomeActivity.this,RecorderActivity.class);
               startActivity(intent);
           }
       });

        profileTxt.setText(user+" "+mob);
        audioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,AudioActivity.class);
                startActivity(intent);
            }
        });

        videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HomeActivity.this,VideoActivity.class);
                startActivity(intent1);
            }
        });

        fragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HomeActivity.this,FragmentActivity.class);
                startActivity(intent1);
            }
        });
        sql_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HomeActivity.this,DatabaseActivity.class);
                startActivity(intent1);

            }
        });
        profileTxt.setText(getIntent().getStringExtra("username")+" "+getIntent().getStringExtra("mobile"));
    }
    public void open_listview(View view) {
        Intent intent1 = new Intent(HomeActivity.this,ListActivity.class);
        startActivity(intent1);
    }
    public void open_gridview(View view) {

        Intent intent = new Intent(HomeActivity.this,GridActivity.class);
        startActivity(intent);
    }
    public void open_menu(View view) {
        Intent intent = new Intent(HomeActivity.this,
                MenuActivity.class);
        startActivity(intent);

    }
    public void link(View view){
        String link = "https://www.w3schools.com/mySQl/default.asp";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }
    public void open_blueetooth(View view) {
        Intent intent = new Intent (HomeActivity.this , BluetoothActivity.class);
        startActivity(intent);
    }

    public void open_wifi(View view) {
        Intent intent = new Intent(HomeActivity.this, WifiActivity.class);
        startActivity(intent);
    }
}