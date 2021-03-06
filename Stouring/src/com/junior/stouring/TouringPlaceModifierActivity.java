package com.junior.stouring;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.junior.stouring.drawer.DrawerActivity;
import com.junior.stouring.tabsswipeadapter.TabsPagerAdapter;

public class TouringPlaceModifierActivity extends DrawerActivity implements ActionBar.TabListener{
	
	TouringPlaceDatabaseHelper mDatabaseHelper;
	
	SessionManager session;
	
	private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    
    
    public static FragmentManager fragmentManager;
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp_modifier);
        
        fragmentManager = getFragmentManager();
        
        mDatabaseHelper = new TouringPlaceDatabaseHelper(getBaseContext());
        
        final int mTouringPlaceId = getIntent().getExtras().getInt("placeId");
        TouringPlace mTouringPlace = mDatabaseHelper.getTouringPlaceFromDB(mTouringPlaceId);
        
        tabInitialization(mTouringPlace);
        
	}
        
	
	public void tabInitialization(TouringPlace mTouringPlace) {
		
		// Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager(), mTouringPlace);
 
        viewPager.setAdapter(mAdapter);
        //actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);       
 
        // Adding Tabs
        String[] tabs = { mTouringPlace.getName(), "Offres", "Carte" };
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
		
        }
        
        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
         
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
         
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
         
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
	}
	
	
       
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

}
