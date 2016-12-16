package edu.umich.engin.congrats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LaunchScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.umich.engin.congrats.R.layout.activity_launch_screen);
        Intent intent = new Intent(LaunchScreenActivity.this, Onboarding.class);
        startActivity(intent);
        finish();
    }

}
