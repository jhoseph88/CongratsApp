package com.example.congratsapp;


import android.os.Bundle;

public class LifeAndActivitiesTab extends PostTab {
    // title of tab for OpportunitiesTab class
    private static final String TAB_LIFE_AND_COMMUNITIES = "life-and-communities";

    protected void onCreate(Bundle savedInstanceState) {
        super.setTabName(TAB_LIFE_AND_COMMUNITIES);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_opportunities);
    }
}
