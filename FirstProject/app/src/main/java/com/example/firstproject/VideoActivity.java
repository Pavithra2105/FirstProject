package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Arrays;

public class VideoActivity extends AppCompatActivity {

     VideoView videoView;
    ArrayList<String> video_url=new ArrayList<>(Arrays.asList("https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_1280_10MG.mp4"
            , "https://cdn.videvo.net/videvo_files/video/premium/video0225/small_watermarked/MR_Stock%20Footage%20MR%20(2338)_preview.webm"));


    int indexing=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoView);
        final MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(video_url.get(indexing)));
        videoView.requestFocus();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(VideoActivity.this, "Video Completed", Toast.LENGTH_SHORT).show();

                if (indexing++ ==video_url.size()){
                    indexing=0;
                    mp.release();
                }
                else {
                    videoView.setVideoURI(Uri.parse(video_url.get(indexing)));
                    videoView.start();
                }
            }
        });
    }
}


