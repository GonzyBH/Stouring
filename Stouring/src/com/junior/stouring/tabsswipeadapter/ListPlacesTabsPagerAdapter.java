package com.junior.stouring.tabsswipeadapter;


import java.util.ArrayList;

import com.junior.stouring.City;
import com.junior.stouring.FragmentTouringList;
import com.junior.stouring.PlaceType;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class ListPlacesTabsPagerAdapter extends FragmentPagerAdapter {
	
	City currentCity;
	PlaceType placeType;
	ArrayList<PlaceType> typeList;
	

	 
    public ListPlacesTabsPagerAdapter(FragmentManager fm, City city, ArrayList<PlaceType> pTypeList) {
        super(fm);
        currentCity = city;
        typeList = pTypeList;
    }
 
    @Override
    public Fragment getItem(int index) {
    	
    	
    	Bundle bundle = new Bundle();
    	bundle.putParcelable("cityName", currentCity);
    	
//    	Fragment frag = new FragmentTouringList();
//    	placeType = typeList.get(index);
//    	Log.e("index", ""+index);
//    	bundle.putParcelable("placeType", placeType);
//    	frag.setArguments(bundle);
//    	return frag;
 
        switch (index) {

        case 0:
        	Fragment SIFrag = new FragmentTouringList();
        	placeType = typeList.get(0);
        	bundle.putParcelable("placeType", placeType);
        	SIFrag.setArguments(bundle);
            return SIFrag;
        case 1:
        	Fragment SIFrag2 = new FragmentTouringList();
        	placeType = typeList.get(1);
        	bundle.putParcelable("placeType", placeType);
        	SIFrag2.setArguments(bundle);
            return SIFrag2;
        case 2:
        	Fragment SIFrag3 = new FragmentTouringList();
        	placeType = typeList.get(2);
        	bundle.putParcelable("placeType", placeType);
        	SIFrag3.setArguments(bundle);
            return SIFrag3;
        case 3:
        	Fragment SIFrag4 = new FragmentTouringList();
        	placeType = typeList.get(3);
        	bundle.putParcelable("placeType", placeType);
        	SIFrag4.setArguments(bundle);
            return SIFrag4;
        case 4:
        	Fragment SIFrag5 = new FragmentTouringList();
        	placeType = typeList.get(4);
        	bundle.putParcelable("placeType", placeType);
        	SIFrag5.setArguments(bundle);
            return SIFrag5;
        case 5:
        	Fragment SIFrag6 = new FragmentTouringList();
        	placeType = typeList.get(5);
        	bundle.putParcelable("placeType", placeType);
        	SIFrag6.setArguments(bundle);
            return SIFrag6;
        }
		return null;
 

    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 6;
    }
 
}