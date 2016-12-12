package edu.umich.engin.congrats;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import edu.umich.engin.congrats.MainTabs.AcademicsTab;
import edu.umich.engin.congrats.MainTabs.LifeAndActivitiesTab;
import edu.umich.engin.congrats.MainTabs.OpportunitiesTab;

public class TabActivity extends android.app.TabActivity {


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.umich.engin.congrats.R.layout.activity_tab);

        //TabHost stuff
        final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this.getLocalActivityManager());

        Intent intent = new Intent(this, CheckListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("CHECKLIST");
        tabSpec.setContent(intent);
        tabSpec.setIndicator("CHECKLIST");
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
        tabSpec = tabHost.newTabSpec("LIFE & COMMUNITIES");
        tabSpec.setContent(intent);
        tabSpec.setIndicator("LIFE & COMMUNITIES");
        tabHost.addTab(tabSpec);

        // Set formatting for tabs (white font and blue background)
        final TabWidget tw = (TabWidget) tabHost.findViewById(android.R.id.tabs);
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
                                                                    getColor(edu.umich.engin.congrats.R.color.NavyBlue) );

        }// for
        // Checklist tab will be 'selected' (maize) initially
        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(ContextCompat.getColor(this, R.color.Maize) );
        View tabView = tw.getChildTabViewAt(0);
        TextView tv = (TextView) tabView.findViewById(android.R.id.title);
        tv.setTextColor(ContextCompat.getColor(this, R.color.NavyBlue) );
        // Change appearance for when tab is selected
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    final View tabView = tw.getChildTabViewAt(i);
                    final TextView tv = (TextView) tabView.findViewById(android.R.id.title);
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().
                                                                    getColor(edu.umich.engin.congrats.R.color.NavyBlue) );
                    tv.setTextColor(Color.WHITE);
                }// for
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab() )
                                      .setBackgroundColor(getResources().getColor(edu.umich.engin.congrats.R.color.Maize) );

                final View tabView = tw.getChildTabViewAt(tabHost.getCurrentTab() );
                final TextView tv = (TextView) tabView.findViewById(android.R.id.title);
                tv.setTextColor(getResources().getColor(edu.umich.engin.congrats.R.color.NavyBlue) );
            }// onTabChanged
        });
    }

}
