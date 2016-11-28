package edu.umich.engin.congrats;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.*;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tagmanager.Container;

public class Onboarding extends ActionBarActivity {

    ViewPager viewpager;
    Context context;
    private final String ONBOARDING_STATUS = "onboardingDone";

    public Onboarding(Context context) {
        this.context = context;
    }
    public Onboarding() {}

    private static final String MSG = "PUSH NOTIFICATION";
    // Will hold ContainerHolderSingleton for Google Play
    private Container container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.umich.engin.congrats.R.layout.activity_onboarding);
        // Create onboarding pager with welcome screens that will be shown only once
        viewpager = (ViewPager)findViewById(edu.umich.engin.congrats.R.id.pager);
        edu.umich.engin.congrats.PagerAdapter padapter = new edu.umich.engin.congrats.PagerAdapter(getSupportFragmentManager(), this);
        viewpager.setAdapter(padapter);
        // Get ContainerHolderSingleton container
        //container = ContainerHolderSingleton.getContainerHolder().getContainer();
        //Receive messages from firebase
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(MSG, "Key: " + key + " Value: " + value);
            }
        }
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