package com.example.congratsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class TabActivity extends android.app.TabActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        //TabHost stuff
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this.getLocalActivityManager() );

        Intent intent = new Intent(this, CheckListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("LIST");
        tabSpec.setContent(R.id.checklist_tab);
        tabSpec.setContent(intent);
        tabSpec.setIndicator("LIST");
        tabHost.addTab(tabSpec);

        intent = new Intent(this, AcademicsTab.class);
        tabSpec = tabHost.newTabSpec("ACADEMICS");
        tabSpec.setContent(intent);
        tabSpec.setIndicator("ACADEMICS");
        tabHost.addTab(tabSpec);


        intent = new Intent(this, OpportunitiesTab.class);
        tabSpec = tabHost.newTabSpec("OPPORTUNITIES");
        tabSpec.setContent(intent);
        tabSpec.setIndicator("OPPORTUNITIES");
        tabHost.addTab(tabSpec);

        intent = new Intent(this, LifeAndActivitiesTab.class);
        tabSpec = tabHost.newTabSpec("LIFE & ACTIVITIES");
        tabSpec.setContent(intent);
        tabSpec.setIndicator("LIFE & ACTIVITIES");
        tabHost.addTab(tabSpec);
    }

}
