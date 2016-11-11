package com.example.congratsapp;
import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class VerticalVideoActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vertical_video);
        // new VideoView instance
        VideoView videoView = (VideoView)findViewById(R.id.verticalVid);
        String videoAddr = "https://www.youtube.com/watch?v=8WHFY6XxZ_8";
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
