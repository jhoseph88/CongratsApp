package com.example.congratsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;


public class FragmentFive extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four_layout, container, false);
        super.onCreate(savedInstanceState);
        Intent switchToVerticalVideo = new Intent(getActivity(), VerticalVideoActivity.class);
        startActivity(switchToVerticalVideo);

        return view;
    }
}