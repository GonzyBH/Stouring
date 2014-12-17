package com.junior.stouring.tabsswipeadapter;


import com.junior.stouring.City;
import com.junior.stouring.FragmentTouringList;
import com.junior.stouring.tabsswipefragments.ExampleFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ListPlacesTabsPagerAdapter extends FragmentPagerAdapter {
	
	City currentCity;
	 
    public ListPlacesTabsPagerAdapter(FragmentManager fm, City city) {
        super(fm);
        currentCity = city;
    }
 
    @Override
    public Fragment getItem(int index) {
    	
    	Bundle bundle = new Bundle();
    	bundle.putParcelable("cityName", currentCity);
 
        switch (index) {
        case 0:
        	// Games fragment activity
        	Fragment SIFrag = new FragmentTouringList();
        	SIFrag.setArguments(bundle);
            return SIFrag;
        case 1:
            // Games fragment activity
        	Fragment SIFrag2 = new ExampleFragment();
        	SIFrag2.setArguments(bundle);
            return SIFrag2;
        case 2:
        	// Games fragment activity
        	Fragment SIFrag3 = new ExampleFragment();
        	SIFrag3.setArguments(bundle);
            return SIFrag3;
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}