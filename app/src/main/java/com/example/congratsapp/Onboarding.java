package com.example.congratsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.zip.Inflater;

public class Onboarding extends FragmentActivity {
    ViewPager viewpager;
    Button toDoListButton;
    Context context;

    public Onboarding(Context context) {
        this.context = context;
    }
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        viewpager = (ViewPager)findViewById(R.id.pager);
        PagerAdapter padapter = new PagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(padapter);
        //FIXME: figure out how to switch to CheckListActivity by clicking the skipToChecklistButton button
       /* LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_two_layout, false);
        toDoListButton = (Button) findViewById( (R.layout.fragment_two_layout).skipToChecklistButton);*/

        toDoListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToAdmissionsChecklist = new Intent(Onboarding.this,
                                                                CheckListActivity.class);
                startActivity(switchToAdmissionsChecklist);
            }
        });

}