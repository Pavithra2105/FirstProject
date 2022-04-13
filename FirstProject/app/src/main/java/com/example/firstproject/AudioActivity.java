package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class AudioActivity extends AppCompatActivity {
    TextView starttimeTv, endtimeTv;
    SeekBar seekBar;
    Button playBtn, pauseBtn, rewindBtn, forwardBtn;
    private MediaPlayer mediaPlayer;
    private double startTime=0;
    private double endTime=0;
    private int forwardTime=10000;
    private int backwardTime=10000;

    private Handler myHandler=new Handler();
    private static int temp=0;
    private boolean audioPlay=false;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        //set ids
        starttimeTv=findViewById(R.id.starttime_tv);
        endtimeTv=findViewById(R.id.endtime_tv);
        seekBar=findViewById(R.id.seekbar);

        playBtn=findViewById(R.id.play_btn);
        pauseBtn=findViewById(R.id.pause_btn);
        rewindBtn=findViewById(R.id.rewind_btn);
        forwardBtn=findViewById(R.id.forward_btn);

        mediaPlayer=MediaPlayer.create(this, R.raw.electro_ncs);
        //default

        playBtn.setEnabled(true);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (audioPlay==false){
                    mediaPlayer.start();
                    Toast.makeText(AudioActivity.this, "NCS Electro Music Playing", Toast.LENGTH_SHORT).show();

                    audioPlay=true;
                    playBtn.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled_45);

                    endTime=mediaPlayer.getDuration();
                    startTime=mediaPlayer.getCurrentPosition();
                    if (temp==0){
                        seekBar.setMax((int)endTime);
                        temp=1;
                    }

                    starttimeTv.setText(String.format("%d : %d", TimeUnit.MILLISECONDS.toMinutes((long)startTime),
                            TimeUnit.MILLISECONDS.toSeconds((long)startTime)
                                    - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)startTime))));

                    endtimeTv.setText(String.format("%d : %d", TimeUnit.MILLISECONDS.toMinutes((long)endTime),
                            TimeUnit.MILLISECONDS.toSeconds((long)endTime)
                                    -TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)endTime))));

                    seekBar.setProgress((int)startTime);
                    myHandler.postDelayed(SongTrackTimer, 100);

                }else if (audioPlay==true){
                    playBtn.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_45);
                    mediaPlayer.pause();
                    audioPlay=false;
                }


            }
        });
//forward
        forwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=(int) startTime; // a= 4:10 //a+forwardTime // 4:10+0:10 = 4:20
                if((a+forwardTime)<=endTime){       // 1:25 <= 4:15
                    startTime=startTime+forwardTime;  //1:15+0:10
                    mediaPlayer.seekTo((int)startTime);
                }else {
                    Toast.makeText(AudioActivity.this, "Unable to Forward", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //backward
        rewindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int a=(int)startTime;  //a=startime (current audio time) 1:15 -0:10 =1:05  // 0:05 -0:10= -0:05
                if ((a-backwardTime)>0){
                    startTime=startTime-forwardTime;
                    mediaPlayer.seekTo((int)startTime);
                }else {
                    Toast.makeText(AudioActivity.this, "Unable to Backward", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    private Runnable SongTrackTimer=new Runnable() {
        @Override

        public void run() {
            startTime=mediaPlayer.getCurrentPosition();

            starttimeTv.setText(String.format("%d : %d", TimeUnit.MILLISECONDS.toMinutes((long)startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long)startTime)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)startTime))));
            seekBar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };
}