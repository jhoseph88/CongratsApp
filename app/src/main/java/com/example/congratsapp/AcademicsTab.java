package com.example.congratsapp;

import android.os.Bundle;

public class AcademicsTab extends PostTab {
    //title of tab for OpportunitiesTab class
    private static final String TAB_ACADEMICS = "academics";
    protected void onCreate(Bundle savedInstanceState) {
        super.setTabName(TAB_ACADEMICS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_academics);
    }
}
