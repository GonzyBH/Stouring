package com.junior.stouring.slidingmenuadapter;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.junior.stouring.R;
import com.junior.stouring.slidingmenumodel.NavDrawerItem;
import com.junior.stouring.slidingmenuadapter.NavDrawerListAdapter;

public class NavDrawerListMaker {

	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    
    // nav drawer title
    private CharSequence mDrawerTitle;
 
    // used to store app title
    private CharSequence mTitle;
 
    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
 
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public void DrawerMaker(Activity pActivity, ActionBar pActionBar){
    	Log.i("testMaker", "MAKER 1");
    	final Activity currentActivity = pActivity;
    	
    	mTitle = mDrawerTitle = currentActivity.getTitle();
    	 
        // load slide menu items
        navMenuTitles = currentActivity.getResources().getStringArray(R.array.nav_drawer_items);
 
        // nav drawer icons from resources
        navMenuIcons = currentActivity.getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);
 
        mDrawerLayout = (DrawerLayout) currentActivity.findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) currentActivity.findViewById(R.id.list_slidermenu);
 
        navDrawerItems = new ArrayList<NavDrawerItem>();
 
        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
        // Pages
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // What's hot, We  will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));
         
 
        // Recycle the typed array
        navMenuIcons.recycle();
        Log.i("testMaker", "MAKER 2");
        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(currentActivity.getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);
        Log.i("testMaker", "MAKER 3");
        // enabling action bar app icon and behaving it as toggle button
        pActionBar.setDisplayHomeAsUpEnabled(true);
        pActionBar.setHomeButtonEnabled(true);
        Log.i("testMaker", "MAKER 4");
        mDrawerToggle = new ActionBarDrawerToggle(currentActivity, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                currentActivity.getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                currentActivity.invalidateOptionsMenu();
            }
 
            public void onDrawerOpened(View drawerView) {
            	currentActivity.getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                currentActivity.invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        Log.i("testMaker", "MAKER 5");
    }
	
}
