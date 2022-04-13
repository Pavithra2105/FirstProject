package com.example.firstproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class RecorderActivity extends AppCompatActivity {

    Button startRec, stopRec, play, stop;
    TextView statusTxt;

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String fileName=null;
    private static final int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);
        //set ids

        startRec=findViewById(R.id.btn1);
        stopRec=findViewById(R.id.btn2);
        play=findViewById(R.id.btn3);
        stop=findViewById(R.id.btn4);
        statusTxt=findViewById(R.id.record_status_txt);

        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
        mediaRecorder.setOutputFile(fileName);


        startRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecording();
            }
        });

        stopRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopRecording();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playRecording();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopPlaying();
            }
        });

    }



    public void startRecording() {
        if (ChecPermission()){
            
            ContextWrapper contextWrapper  = new ContextWrapper(getApplicationContext());
            File filedir = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
            File file = new File(filedir,"recording001"+".3GP");
            fileName=file.getPath();                               
            fileName= Environment.getExternalStorageDirectory().getAbsolutePath();
            fileName+="/recording001.3gp";

            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

            statusTxt.setText("REC Status: Recording Started");

        }else{
            RequestPermissionUser();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_CODE:
                if (grantResults.length>0){
                    boolean permissionRecord=grantResults[0]== PackageManager.PERMISSION_GRANTED;
                    boolean permissionStore=grantResults[1]==PackageManager.PERMISSION_GRANTED;
                    boolean permissionStoreRead=grantResults[2]==PackageManager.PERMISSION_GRANTED;
                    if (permissionRecord && permissionStore && permissionStoreRead){
                        Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    public boolean ChecPermission() {
        int result= ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result2=ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        int result3=ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return result==PackageManager.PERMISSION_GRANTED && result2==PackageManager.PERMISSION_GRANTED && result3==PackageManager.PERMISSION_GRANTED;
    }
    private void RequestPermissionUser() {
        ActivityCompat.requestPermissions(RecorderActivity.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, REQUEST_CODE);
    }

    public void stopPlaying() {

        mediaPlayer.release();
        mediaPlayer=null;
        statusTxt.setText("Stopped Recorded Audio");
    }

    public void playRecording() {
        mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource(fileName);
            mediaPlayer.prepare();
            mediaPlayer.start();
            statusTxt.setText("Playing Recorded Audio");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRecording() {

        try {
            mediaRecorder.stop();
            mediaRecorder.release();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        mediaRecorder=null;
        statusTxt.setText("REC Status: Recording Stopped");
    }

}