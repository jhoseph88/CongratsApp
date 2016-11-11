package com.example.congratsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AcademicsTab extends PostTab {

    //title of tab for OpportunitiesTab class
    private static final String TAB_ACADEMICS = "academics";
    protected void onCreate(Bundle savedInstanceState) {
        super.setTabName(TAB_ACADEMICS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_academics);

        ImageButton infoButton = (ImageButton) findViewById(R.id.academicsInfoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToTabActivity = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(switchToTabActivity);
            }
        });
    }

}
