package edu.umich.engin.congratsapp.MainTabs;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import edu.umich.engin.congratsapp.InfoActivity;
import edu.umich.engin.congratsapp.PostTab;

public class OpportunitiesTab extends PostTab {
    // title of tab for OpportunitiesTab class
    private static final String TAB_OPPORTUNITIES = "opportunities";

    protected void onCreate(Bundle savedInstanceState) {
        super.setTabName(TAB_OPPORTUNITIES);
        super.onCreate(savedInstanceState);
        setContentView(edu.umich.engin.congratsapp.R.layout.tab_opportunities);

        // set onclick listener to go to info page if info button clicked
        ImageButton infoButton = (ImageButton) findViewById(edu.umich.engin.congratsapp.R.id.opportunitiesInfoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToTabActivity = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(switchToTabActivity);
            }
        });
    }
}
