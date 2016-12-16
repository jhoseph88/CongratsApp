package edu.umich.engin.congratsapp.OnboardingFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

import edu.umich.engin.congratsapp.R;
import edu.umich.engin.congratsapp.TabActivity;


public class FragmentFive extends android.support.v4.app.Fragment {
    private final String ONBOARDING_STATUS = "onboardingDone";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(edu.umich.engin.congratsapp.R.layout.fragment_five_layout, container, false);

        WebView webView = (WebView) view.findViewById(edu.umich.engin.congratsapp.R.id.verticalVid);
        String webData = "<iframe class=\"youtube-player\" frameborder=\"0\" " +
                "allowfullscreen src=\"https://www.youtube.com/embed/8WHFY6XxZ_8?rel=0\"" +
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

        // Obtain the FirebaseAnalytics instance.
        final FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.getContext() );

        Button toDoListButton = (Button)view.findViewById(R.id.skipToChecklistButton);
        toDoListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle params = new Bundle();
                // send skip_video == true to firebase analytics when a user checks off an item
                params.putBoolean("skip_video", true);
                mFirebaseAnalytics.logEvent("skip_video", params);
                Intent switchToTabActivity = new Intent(getActivity(), TabActivity.class);
                startActivity(switchToTabActivity);
                // pop video activity so it doesn't keep playing when user clicks "let's go"
                getActivity().getFragmentManager().popBackStack();
            }
        });

        return view;
    }
}