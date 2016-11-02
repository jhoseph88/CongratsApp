package com.example.congratsapp;


import android.os.Bundle;

public class OpportunitiesTab extends PostTab {
    // title of tab for OpportunitiesTab class
    private static final String TAB_OPPORTUNITIES = "opportunities";

    protected void onCreate(Bundle savedInstanceState) {
        super.setTabName(TAB_OPPORTUNITIES);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_opportunities);
    }
}
