package edu.umich.engin.congrats;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

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
        setContentView(edu.umich.engin.congrats.R.layout.checklist_tab);
        listView = (ListView) findViewById(edu.umich.engin.congrats.R.id.listView);

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
        toDoItems[0] = new ToDo("Receive your acceptance letter (done!) Share your success on " +
                                "<a href=\"http://coecongrats.wpengine.com/to-do/social/\">" +
                                "social media</a>—there’s images for friends and family too!", 0);
        toDoItems[1] = new ToDo("<a href=\"https://regstg.com/Registration/(S(vcupsm" +
                                "i11o3fwbxj41x1mwdt))/Registration.aspx?rid=cb24633f-4087-497f-8b" +
                                "80-c2ee95468638\">Register for Campus Day</a>. This program is " +
                                "held January through April, and registration starts in January. " +
                                "In the meantime, review <a href=\"http://www.engin.umich.edu/" +
                                "college/admissions/undergrad/admitted\">admitted student website" +
                                "</a>.", 0);
        toDoItems[2] = new ToDo("Complete your <a href=\"http://www.finaid.umich.edu/Home/HowtoAp" +
                                "plyforAid/NewProspectiveUndergraduates.aspx#application\">CSS " +
                                "Profile and FAFSA</a>: Students applying for Fall 2017, the CSS " +
                                "and FAFSA will be available on Oct. 1, 2016. The early deadline " +
                                "is February 15. April 30 is the final deadline.", 0);
        toDoItems[3] = new ToDo("Pay your $300 enrollment deposit. Deposit is due by May 1. " +
                                "Payment can be made via <a href=\"http://wolverineaccess.umich." +
                                "edu/render.userLayoutRootNode.uP?uP_root=root&uP_sparam=activeTa" +
                                "b&activeTab=2\">Wolverine Access</a>, or post marked by May 1, " +
                                "2016. Please login to <a href=\"http://wolverineaccess.umich.edu" +
                                "/render.userLayoutRootNode.uP?uP_root=root&uP_sparam=activeTab&a" +
                                "ctiveTab=2\">Wolverine Access</a> for more information about " +
                                "making your deposit.", 0);
        toDoItems[4] = new ToDo("Check scholarship status. Admitted first-year students are " +
                                "automatically considered for <a href=\"http://www.engin.umich.ed" +
                                "u/college/admissions/finances/scholarships\">College of " +
                                "Engineering merit-based scholarships</a>, and notification of " +
                                "awards are made before mid-April. <a href=\"http://www.finaid.um" +
                                "ich.edu/Home/TypesofAid/ScholarshipsandGrants/OFAScholarshipList" +
                                "ing.aspx\">U-M Scholarships</a> are administered by the Office " +
                                "of Financial Aid and most recipients are notified by April 15.", 0);
        toDoItems[5] = new ToDo("Submit your <a href=\"http://housing.umich.edu/applications/" +
                                "freshman\">Housing/Michigan Learning Community Application</a>. " +
                                "Due in mid May. If applying for a Michigan Learning Community, " +
                                "additional essays are required. Please verify the requirements " +
                                "for your selection.", 0);
        toDoItems[6] = new ToDo("<a href=\"http://onsp.umich.edu/orientation/incoming-freshman\">" +
                                "Register for Orientation</a>. Registration slots made available " +
                                "in early April ", 0);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        ToDoAdapter toDoAdapter = new ToDoAdapter(this, toDoItems, stepNumberHeadings);
        listView.setAdapter(toDoAdapter);
        // set onclick listener to go to info page if info button clicked
        ImageButton infoButton = (ImageButton) findViewById(R.id.checklistInfoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToTabActivity = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(switchToTabActivity);
            }
        });

    }
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Save state of listView via boolCheckBoxes variable
        editor.putString("thisCheckedList", String.valueOf(boolCheckBoxes) );
        editor.apply();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Save state of listView via boolCheckBoxes variable
        editor.putString("thisCheckedList", String.valueOf(boolCheckBoxes) );
        editor.apply();
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