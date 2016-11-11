package com.example.congratsapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.zip.Inflater;

public class Onboarding extends ActionBarActivity {
    ViewPager viewpager;
    Context context;

    public Onboarding(Context context) {
        this.context = context;
    }
    public Onboarding() {}

    private final String ONBOARDING_STATUS = "onboardingDone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        // Create onboarding pager with welcome screens that will be shown only once
        viewpager = (ViewPager)findViewById(R.id.pager);
        PagerAdapter padapter = new PagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(padapter);

    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Save state of listView via boolCheckBoxes variable
        //editor.putString("thisCheckedList", String.valueOf(boolCheckBoxes) );
        editor.putBoolean(ONBOARDING_STATUS, true);
        editor.apply();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Save state of listView via boolCheckBoxes variable
        editor.putBoolean(ONBOARDING_STATUS, true);
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Get boolean designating whether onboarding screens have been viewed
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean onboardingDone = sharedPreferences.getBoolean(ONBOARDING_STATUS, false);

        // if onboarding has been completed, just go to the tab activity
        if (onboardingDone) {
            Intent switchToTabActivity = new Intent(Onboarding.this, TabActivity.class);
            startActivity(switchToTabActivity);
        }
    }
}