package edu.umich.engin.congrats;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import edu.umich.engin.congrats.OnboardingFragments.FragmentFive;
import edu.umich.engin.congrats.OnboardingFragments.FragmentFour;
import edu.umich.engin.congrats.OnboardingFragments.FragmentOne;
import edu.umich.engin.congrats.OnboardingFragments.FragmentThree;
import edu.umich.engin.congrats.OnboardingFragments.FragmentTwo;


public class PagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private final String ONBOARDING_STATUS = "onboardingDone";

    public PagerAdapter(FragmentManager fm, Context ctx) {
        super(fm);
        this.context = ctx;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentOne();
            case 1:
                return new FragmentTwo();
            case 2:
                return new FragmentThree();
            case 3:
                return new FragmentFour();
            // This is the video tab -- if video was viewed, set ONBOARDING_STATUS to true
            // so that onboarding intro screens aren't shown again.
            case 4:
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(ONBOARDING_STATUS, true);
                editor.apply();
                return new FragmentFive();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        // Return number of fragments to swipe through in onboarding sequence
        return 5;
    }

}
