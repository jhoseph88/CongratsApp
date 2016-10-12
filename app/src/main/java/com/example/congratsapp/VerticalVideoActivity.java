package com.example.congratsapp;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VerticalVideoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vertical_video);
        // new VideoView instance
        VideoView videoView = (VideoView)findViewById(R.id.verticalVid);
        String videoAddr = "https://archive.org/download/ksnn_compilation_master_the_internet/" +
                            "ksnn_compilation_master_the_internet_512kb.mp4";
        Uri verticalVidUri = Uri.parse(videoAddr);
        videoView.setVideoURI(verticalVidUri);

        // Add video controls
        MediaController videoController = new MediaController(this);
        videoController.setAnchorView(videoView);
        videoView.setMediaController(videoController);

        // Start the video
        videoView.start();
    }
}
