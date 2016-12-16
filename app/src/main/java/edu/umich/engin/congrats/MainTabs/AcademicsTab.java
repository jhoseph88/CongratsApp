package edu.umich.engin.congrats.MainTabs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import edu.umich.engin.congrats.InfoActivity;
import edu.umich.engin.congrats.PostTab;

public class AcademicsTab extends PostTab {

    //title of tab for OpportunitiesTab class
    private static final String TAB_ACADEMICS = "academics";
    protected void onCreate(Bundle savedInstanceState) {
        super.setTabName(TAB_ACADEMICS);
        super.onCreate(savedInstanceState);
        setContentView(edu.umich.engin.congrats.R.layout.tab_academics);

        ImageButton infoButton = (ImageButton) findViewById(edu.umich.engin.congrats.R.id.academicsInfoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToTabActivity = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(switchToTabActivity);
            }
        });
    }

}
