package com.example.congratsapp;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class PostTab extends ListActivity {
    // String for intent (for sticky broadcast)
    private static final String INTENT_ACADEMICS = "intent_academics";
    // String for the url for the JSON feed
    //URL for academics tab
    private static String URL_ACADEMICS = "http://coecongrats.wpengine.com/wp-json/wp/v2/posts?" +
                                          "categories=3&per_page=100";
    //URL for opportunities tab
    private static String URL_OPPORTUNITIES = "http://coecongrats.wpengine.com/wp-json/wp/v2/" +
                                              "posts?categories=5&per_page=100";
    //URL for life and activities tab
    private static String URL_LIFE_AND_ACTIVITIES = "http://coecongrats.wpengine.com/wp-json/wp/" +
                                                    "v2/posts?categories=4&per_page=100";
    // Strings for tab titles
    private static final String TAB_OPPORTUNITIES = "opportunities";
    private static final String TAB_ACADEMICS = "academics";
    private static final String TAB_LIFE_AND_COMMUNITIES = "life-and-communities";
    // tag for page link which was sent as extra from MainActivity
    private final static String PAGE_LINK = "PAGE_LINK";

    // array of entries for the tab
    private ArrayList<Entry> entries;
    // string designating which tab this activity represents
    private String tabName;

    // Custom constructor which tells the PostTab object which tab it will represent.
    // This will be useful for deciding which posts will go in entries.
    protected void setTabName(String tabName) {
        this.tabName = tabName;
    }

    // getter for tabName
    public String getTabName() {
        return this.tabName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.tab_opportunities);

        // start asynchronous JSON processing
        new PostTab.ParseJSONAsync().execute();
    }

    // Parse JSON data asynchronously
    public class ParseJSONAsync extends AsyncTask<Void, Void, Void> {

        // Hashmap for ListView (contains entries for all tabs)
        HashMap<String, ArrayList<Entry>> tabList;
        // Array of academics posts
        ArrayList<Entry> academicsPosts;
        // Array of opportunities posts
        ArrayList<Entry> opportunitiesPosts;
        // Array for life and activities posts
        ArrayList<Entry> lifeAndActivitiesPosts;
        //ArrayList<Entry> entries;
        // Dialog for page progress
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Construct HashMap instance for tabList
            tabList = new HashMap<String, ArrayList<Entry>>();
            // Showing progress dialog
            pDialog = new ProgressDialog(PostTab.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Create service handler class instance
            WebRequest webRequest = new WebRequest();

            // Make request to academics posts url and store posts as JSON string
            String jsonStr = webRequest.makeWebServiceCall(URL_ACADEMICS, WebRequest.GET);

            //Log.d("Response: ", "> " + jsonStr);
            // Create JSON parser and parse JSON data from URL
            ParseJSON parser = new ParseJSON();
            // Get a list of academics posts from the json parser
            academicsPosts = parser.ParseJSONString(jsonStr);

            // Make request to opportunities posts url and store posts as JSON string
            jsonStr = webRequest.makeWebServiceCall(URL_OPPORTUNITIES, WebRequest.GET);
            // Get list of opportunities posts
            opportunitiesPosts = parser.ParseJSONString(jsonStr);

            // Get a list of Life & Activities posts and store psots as JSON string
            jsonStr = webRequest.makeWebServiceCall(URL_LIFE_AND_ACTIVITIES, WebRequest.GET);
            // Get list of life and activities posts
            lifeAndActivitiesPosts = parser.ParseJSONString(jsonStr);

            // Populate tab HashMap with (tab title, posts array) data
            tabList.put(TAB_ACADEMICS, academicsPosts);
            tabList.put(TAB_OPPORTUNITIES, opportunitiesPosts);
            tabList.put(TAB_LIFE_AND_COMMUNITIES, lifeAndActivitiesPosts);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            Intent i = new Intent(INTENT_ACADEMICS);
            String thisTabName = getTabName();
            if (thisTabName == TAB_ACADEMICS) {
                entries = tabList.get(TAB_ACADEMICS);
                // send entries for academics to academics tab
                i.putExtra("entries", tabList.get(TAB_ACADEMICS));
            } else if (thisTabName == TAB_OPPORTUNITIES) {
                entries = tabList.get(TAB_OPPORTUNITIES);
                // send entries for academics to opportunities tab
                i.putExtra("entries", tabList.get(TAB_OPPORTUNITIES) );
            } else if (thisTabName == TAB_LIFE_AND_COMMUNITIES) {
                entries = tabList.get(TAB_LIFE_AND_COMMUNITIES);
                i.putExtra("entries", tabList.get(TAB_LIFE_AND_COMMUNITIES) );
            }

            sendStickyBroadcast(i);
            ArrayList<String> thumbnailUrls = new ArrayList<String>();
            ArrayList<String> titles = new ArrayList<String>();
            ArrayList<String> excerpts = new ArrayList<String>();
            final ArrayList<String> pageUrls = new ArrayList<String>();
            final CustomEntryAdapter adapter = new CustomEntryAdapter(PostTab.this, thumbnailUrls,
                    titles, excerpts, pageUrls);

            for (int j = 0; j < entries.size(); j++) {
                Entry entry = (Entry) entries.get(j);
                // Add thumbnail url to array
                thumbnailUrls.add(entry.getThumbnailUrl());
                // Add title to array
                titles.add(entry.getTitle());
                // Add excerpt to array
                excerpts.add(entry.getExcerpt());
                // Add page urls
                pageUrls.add(entry.getPageUrl());

            }
            ListView listView = getListView();
            setListAdapter(adapter);
            listView.setAdapter(adapter);
            // Add functionality for webview when user clicks on list item
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    // switch to webview if item in list is clicked
                    Intent intent = new Intent(PostTab.this, DisplayWebpage.class);
                    // get the link that corresponds to the list item that is being clicked
                    final String pageUrl = pageUrls.get(position);
                    // put this link in intent so that it can be accessed in DisplayWebpage activity
                    intent.putExtra(PAGE_LINK, pageUrl);
                    // start the activity
                    startActivity(intent);
                }
            });
        }
    }
}
