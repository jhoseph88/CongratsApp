package com.example.congratsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class DisplayWebpage extends Activity {

    // tag for page link which was sent as extra from MainActivity
    private final static String PAGE_LINK = "PAGE_LINK";
    // WebView for rendering page link
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_display_webpage);
        // This will be the link for the WebView
        String pageUrl = "";

        // Get the url from extras sent from MainActivity
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            pageUrl = (String) b.get(PAGE_LINK);
        }
        mWebView = (WebView) findViewById(R.id.web_view);
        // Enable Javascript for the WebView
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // load the url
        mWebView.loadUrl(pageUrl);
    }

}
