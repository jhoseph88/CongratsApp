package edu.umich.engin.congrats;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LifeAndActivitiesTab extends PostTab {
    // title of tab for OpportunitiesTab class
    private static final String TAB_LIFE_AND_COMMUNITIES = "life-and-communities";

    protected void onCreate(Bundle savedInstanceState) {
        super.setTabName(TAB_LIFE_AND_COMMUNITIES);
        super.onCreate(savedInstanceState);
        setContentView(edu.umich.engin.congrats.R.layout.tab_activities);
        // set onclick listener to go to info page if info button clicked
        ImageButton infoButton = (ImageButton) findViewById(edu.umich.engin.congrats.R.id.activitiesInfoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToTabActivity = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(switchToTabActivity);
            }
        });
    }
}
