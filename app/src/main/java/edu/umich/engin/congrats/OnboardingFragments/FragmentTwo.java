package edu.umich.engin.congrats.OnboardingFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

import edu.umich.engin.congrats.TabActivity;


public class FragmentTwo extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(edu.umich.engin.congrats.R.layout.fragment_two_layout, container, false);
        Button toDoListButton = (Button)view.findViewById(edu.umich.engin.congrats.R.id.skipToChecklistButton);
        // Obtain the FirebaseAnalytics instance.
        final FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.getContext() );
        toDoListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle params = new Bundle();
                // send skip_intro == true to firebase analytics when a user checks off an item
                params.putBoolean("skip_intro", true);
                mFirebaseAnalytics.logEvent("skip_intro", params);
                Intent switchToTabActivity = new Intent(getActivity(), TabActivity.class);
                startActivity(switchToTabActivity);
            }
        });
        return view;
    }
}
