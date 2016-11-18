package edu.umich.engin.congrats;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class FragmentFive extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(edu.umich.engin.congrats.R.layout.fragment_five_layout, container, false);

        WebView webView = (WebView) view.findViewById(edu.umich.engin.congrats.R.id.verticalVid);
        String webData = "<iframe class=\"youtube-player\" frameborder=\"0\" " +
                "allowfullscreen src=\"https://www.youtube.com/embed/8WHFY6XxZ_8\"" +
                "style=\"width:100%;height:100%;\"></iframe>";
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadData(webData, "text/html", "utf-8");

        return view;
    }
}