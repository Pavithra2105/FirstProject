package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    Button turnonBtn, turnoffBtn, devicesBtn;
    TextView statusTxt;
    ListView listView;

    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> devices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        //set ids
        turnonBtn = findViewById(R.id.turnon_btn);
        turnoffBtn = findViewById(R.id.turnoff_btn);
        devicesBtn = findViewById(R.id.devices_btn);
        statusTxt = findViewById(R.id.status_txt);
        listView = findViewById(R.id.listview);

       bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        turnonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!bluetoothAdapter.isEnabled()) {
                    //turn on bluetooth
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    if (ActivityCompat.checkSelfPermission(BluetoothActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivityForResult(intent, 123);
                    Toast.makeText(BluetoothActivity.this, "Turned ON", Toast.LENGTH_SHORT).show();
                    statusTxt.setText("Status: Bluetooth ON");
                } else {
                    Toast.makeText(BluetoothActivity.this, "Already turned on", Toast.LENGTH_SHORT).show();
                }
            }
        });


        turnoffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(BluetoothActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                bluetoothAdapter.disable();
                Toast.makeText(BluetoothActivity.this, "Bluetooth Turned OFF", Toast.LENGTH_SHORT).show();
                statusTxt.setText("Status: Bluetooth OFF");

            }
        });

        devicesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(BluetoothActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                devices = bluetoothAdapter.getBondedDevices();
                ArrayList list = new ArrayList();
                for (BluetoothDevice bt : devices) list.add(bt.getName());
                Toast.makeText(BluetoothActivity.this, "Paired Devices...", Toast.LENGTH_SHORT).show();

                final ArrayAdapter adapter = new ArrayAdapter(BluetoothActivity.this, android.R.layout.simple_list_item_1, list);

                listView.setAdapter(adapter);

            }
        });

    }
}