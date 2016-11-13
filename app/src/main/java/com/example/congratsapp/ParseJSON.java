package com.example.congratsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.HashMap;

public class ParseJSON {

    // JSON tags
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_RENDERED = "rendered";
    private static final String TAG_EXCERPT = "excerpt";
    private static final String TAG_LINK = "link";
    private static final String TAG_FEATURED_MEDIA = "wp:featuredmedia";
    private static final String TAG_HREF = "href";
    private static final String TAG_LINKS = "_links";
    private static final String TAG_GUID = "guid";

    public ArrayList<Entry> ParseJSONString(String json) {
        if (json != null) {
            try {
                // Hashmap for ListView
                ArrayList<HashMap<String, String>> postList = new ArrayList<HashMap<String, String>>();
                // Get array of posts from JSON feed and store it in JSONArray
                JSONArray posts = new JSONArray(json);
                // Array for posts that will be sent back for rendering
                ArrayList<Entry> postsInTab = new ArrayList<Entry>();

                // loop through posts
                for (int i = 0; i < posts.length(); i++) {
                    try {
                        // o will be a single post
                        JSONObject o = posts.getJSONObject(i);

                        // get id for entry
                        String id = o.getString(TAG_ID);

                        // "title" is an object--extract it from "title" object with "rendered" key
                        String title = o.getJSONObject(TAG_TITLE).getString(TAG_RENDERED);

                        // "excerpt" is an object--extract if from "content" object with "rendered"
                        // key
                        String excerpt = o.getJSONObject(TAG_EXCERPT).getString(TAG_RENDERED);
                        // "excerpt" will have html tags, so must remove them with Jsoup
                        excerpt = Jsoup.parse(excerpt).text();

                        // Get link for WebView object
                        String link = o.getString(TAG_LINK);

                        // Get link for page from which we can extract thumbnail image data
                        String thumbnailJSONUrl = o.getJSONObject(TAG_LINKS)
                                .getJSONArray(TAG_FEATURED_MEDIA)
                                .getJSONObject(0)
                                .getString(TAG_HREF);

                        // WebRequest object to get thumbnail data
                        WebRequest webRequest = new WebRequest();
                        // Make request to url and store response as JSON string
                        String jsonStr = webRequest.makeWebServiceCall(thumbnailJSONUrl,
                                                                       WebRequest.GET);
                        JSONObject thumbnailJSONData = new JSONObject(jsonStr);
                        // Get thumbnail URL
                        String thumbnailUrl = thumbnailJSONData.getJSONObject(TAG_GUID)
                                                               .getString(TAG_RENDERED);

                        // hashmap for single entry
                        HashMap<String, String> entry = new HashMap<String, String>();
                        // Make new entry to hold the data for given entry
                        Entry toAdd = new Entry(id, title, excerpt, thumbnailUrl, link);
                        // Add entry to the posts array
                        postsInTab.add(toAdd);
                    }
                    catch (JSONException e) {
                        continue;
                    }
                }
                return postsInTab;

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
            return null;
        }
    }

}
