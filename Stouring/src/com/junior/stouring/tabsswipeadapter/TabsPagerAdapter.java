package com.junior.stouring.tabsswipeadapter;

import com.junior.stouring.TouringPlace;
import com.junior.stouring.tabsswipefragments.OffersFragment;
import com.junior.stouring.tabsswipefragments.StoreProfilFragment;
import com.junior.stouring.tabsswipefragments.MapSwipeFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	
	TouringPlace currentTP;
	 
    public TabsPagerAdapter(FragmentManager fm, TouringPlace pCurrentTP) {
        super(fm);
        currentTP = pCurrentTP;
    }
 
    @Override
    public Fragment getItem(int index) {
    	
    	Bundle bundle = new Bundle();
    	bundle.putParcelable("TP", currentTP);
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
        	Fragment OFrag = new StoreProfilFragment();
        	OFrag.setArguments(bundle);
            return OFrag;
        case 1:
            // Games fragment activity
        	Fragment SIFrag = new OffersFragment();
        	SIFrag.setArguments(bundle);
            return SIFrag;
        case 2:
            // Movies fragment activity
        	Fragment MSFrag = new MapSwipeFragment();
        	MSFrag.setArguments(bundle);
            return MSFrag;
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}