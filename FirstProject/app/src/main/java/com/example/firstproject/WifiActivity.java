package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WifiActivity extends AppCompatActivity {

    Button turnonBtn,turnofBtn;
    TextView statusTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        //set ids
        turnofBtn = findViewById(R.id.turnoff_btn);
        turnonBtn = findViewById(R.id.turnon_btn);
        statusTxt = findViewById(R.id.status_txt);

        turnonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(true);
                statusTxt.setText("Wifi Status:Turned ON");
            }
        });
        turnofBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(false);
                statusTxt.setText("Wifi Status:Turned OFF");
            }
        });
    }
}