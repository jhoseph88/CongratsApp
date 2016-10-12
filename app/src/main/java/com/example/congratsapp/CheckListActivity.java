package com.example.congratsapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

public class CheckListActivity extends Activity {
    public static final String DEFAULT= "N/A";
    public static boolean firstLaunch = true;
    ListView listView;
    ToDo[] toDoItems;
    String[] stepNumberHeadings;
    char[] boolCheckBoxes = {'0', '0', '0', '0', '0', '0', '0'};
    //Seven steps in admissions checklist from assets
    int admissionsItems = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);
        listView = (ListView) findViewById(R.id.listView);

        //Build step number headings array (red "Step One, Step Two," etc. text)
        stepNumberHeadings = new String[admissionsItems];
        stepNumberHeadings[0] = "STEP ONE";
        stepNumberHeadings[1] = "STEP TWO";
        stepNumberHeadings[2] = "STEP THREE";
        stepNumberHeadings[3] = "STEP FOUR";
        stepNumberHeadings[4] = "STEP FIVE";
        stepNumberHeadings[5] = "STEP SIX";
        stepNumberHeadings[6] = "STEP SEVEN";
        toDoItems = new ToDo[admissionsItems];
        toDoItems[0] = new ToDo("Receive your acceptance letter and view admitted student website.",
                                0);
        toDoItems[1] = new ToDo("Register for Campus Day. This program is held January through " +
                                "April, and registration starts in January", 0);
        toDoItems[2] = new ToDo("Complete your CSS Profile and FAFSA: The early deadline is " +
                                "February 15. April 30 is the final deadline.", 0);
        toDoItems[3] = new ToDo("Pay your $300 enrollment deposit. Deposit is due by May 1. " +
                                "Payment can be made via Wolverine Access, or post marked by " +
                                "May 1, 2016. Please login to Wolvering Access for more" +
                                "information about making your deposit.", 0);
        toDoItems[4] = new ToDo("Check scholarship status. Admitted first-year students are " +
                                "automatically considered for College of Engineering merit-based " +
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
        ToDoAdapter toDoAdapter = new ToDoAdapter(this, toDoItems, stepNumberHeadings);
        listView.setAdapter(toDoAdapter);
    }
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Save state of listView via boolCheckBoxes variable
        editor.putString("thisCheckedList", String.valueOf(boolCheckBoxes) );
        editor.apply();
        Toast.makeText(this, "Data was saved successfully", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Save state of listView via boolCheckBoxes variable
        editor.putString("thisCheckedList", String.valueOf(boolCheckBoxes) );
        editor.apply();
        Toast.makeText(this, "Data was saved successfully", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        //Restore listView state
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String checkList = sharedPreferences.getString("thisCheckedList", DEFAULT);

        if (!firstLaunch && (checkList.matches("[0-9]+") == true) ) {
            this.boolCheckBoxes = checkList.toCharArray();
        }
        firstLaunch = false;
        if (checkList.equals(DEFAULT) ) {
            Toast.makeText(this, "No Data was Found", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Data Loaded Successfully", Toast.LENGTH_LONG).show();
        }
    }

    // Continually build string denoting which checkboxes are checked and unchecked
    public void updateCheckboxString(int idx, boolean checked) {
        this.boolCheckBoxes[idx] = checked ? '1' : '0';
    }
    // Get the value ('1' or '0') representing whether box was checked or not
    public char getCheckboxStringValue(int idx) {
        return this.boolCheckBoxes[idx];
    }
}