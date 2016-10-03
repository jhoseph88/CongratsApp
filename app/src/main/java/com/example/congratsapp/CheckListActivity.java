package com.example.congratsapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class CheckListActivity extends Activity {
    ListView listView;
    ToDo[] toDoItems;
    //Seven steps in admissions checklist from assets
    int admissionsItems = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);
        listView = (ListView) findViewById(R.id.listView);
        toDoItems = new ToDo[admissionsItems];
        toDoItems[0] = new ToDo("Receive your acceptance letter and view admitted student website.",
                                0);
        toDoItems[1] = new ToDo("Register for Campus Day. This program is held January through " +
                                "April, and registration starts in January", 0);
        toDoItems[2] = new ToDo("Complete your CSS Profile and FAFSA: The early deadline is " +
                                "February 15. April 30 is the final deadline.", 0);
        toDoItems[3] = new ToDo("Pay your $300 enrollment deposit. Deposit is due by May 1." +
                                "Payment can be made via Wolverine Access, or post marked by " +
                                "May 1, 2016. Please login to Wolvering Access for more" +
                                "information about making your deposit.", 0);
        toDoItems[4] = new ToDo("Check scholarship status. Admitted first-year students are " +
                                "automatically considered for College of Engineering merit-based" +
                                "scholarships, and notification of awards are made before " +
                                "mid-April. U-M Scholarships are administered by the Office of " +
                                "Financial Aid and most recipients are notified by April 15", 0);
        toDoItems[5] = new ToDo("Submit your Housing/Michigan Learning Community Application." +
                                "Due in mid May. If applying for a Michigan Learning Community, " +
                                "additional essays are required. Please verify the requirements " +
                                "for your selection", 0);
        toDoItems[6] = new ToDo("Register for Orientation. Registration slots made available in " +
                                "early April", 0);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
    }
}