package edu.umich.engin.congrats;

import android.content.Context;
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

    public PagerAdapter(FragmentManager fm) {
        super(fm);
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
            case 4:
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
