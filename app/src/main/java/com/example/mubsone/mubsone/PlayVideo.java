package com.example.mubsone.mubsone;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by bsaraci on 10/10/2015.
 */
public class PlayVideo extends Activity {
    VideoView vid;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vid = (VideoView)findViewById(R.id.videoView);
        String urlPath = "http://youtube.com/watch?v=tP0DuyS-_H8";
        Uri video = Uri.parse(urlPath);
        vid.setMediaController(new MediaController(this));
        vid.setVideoURI(video);
        vid.requestFocus();
        vid.start();

    }
}
