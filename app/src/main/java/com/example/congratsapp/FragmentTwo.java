package com.example.congratsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentTwo extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //FIXME: figure out how to switch to CheckListActivity by clicking the skipToChecklistButton button
        //Fragment checklistFragment = new FragmentTwo();
        View view = inflater.inflate(R.layout.fragment_two_layout, container, false);
        Button toDoListButton = (Button)view.findViewById(R.id.skipToChecklistButton);
        toDoListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToAdmissionsChecklist = new Intent(getActivity(),
                                                                CheckListActivity.class);
                startActivity(switchToAdmissionsChecklist);
            }
        });
        return view;
    }
}