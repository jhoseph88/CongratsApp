package com.example.congratsapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class TabActivity extends android.app.TabActivity {


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        //TabHost stuff
        final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this.getLocalActivityManager());

        Intent intent = new Intent(this, CheckListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("LIST");
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

        // Set formatting for tabs (white font and blue background)
        final TabWidget tw = (TabWidget) tabHost.findViewById(android.R.id.tabs);
        // Get width of display to make tabs variable
        Display display = getWindowManager().getDefaultDisplay();
        int width = tw.getWidth();
        int height = tw.getHeight();
        tw.getChildAt(0).setLayoutParams(new LinearLayout.LayoutParams((width/6)-2,(height / 2) - 2) );
        tw.getChildAt(1).setLayoutParams(new LinearLayout.LayoutParams((width/6)-2, (height / 2) - 2) );
        tw.getChildAt(2).setLayoutParams(new LinearLayout.LayoutParams((width/5)-2, (height / 2) - 2) );
        tw.getChildAt(3).setLayoutParams(new LinearLayout.LayoutParams((width/4)-2, (height / 2) -2) );
        for (int i = 0; i < tw.getChildCount(); ++i) {
            final View tabView = tw.getChildTabViewAt(i);
            final TextView tv = (TextView) tabView.findViewById(android.R.id.title);
            tv.setTextSize(12);
            tv.setTextColor(Color.WHITE);
            // Change width for LIST tab (this is the narrowest tab)
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().
                                                                    getColor(R.color.NavyBlue) );
        }// for
        // Change appearance for when tab is selected
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    final View tabView = tw.getChildTabViewAt(i);
                    final TextView tv = (TextView) tabView.findViewById(android.R.id.title);
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().
                                                                    getColor(R.color.NavyBlue) );
                    tv.setTextColor(Color.WHITE);
                }// for
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab() )
                                      .setBackgroundColor(getResources().getColor(R.color.Maize) );

                final View tabView = tw.getChildTabViewAt(tabHost.getCurrentTab() );
                final TextView tv = (TextView) tabView.findViewById(android.R.id.title);
                tv.setTextColor(getResources().getColor(R.color.NavyBlue) );
            }// onTabChanged
        });
    }

}
