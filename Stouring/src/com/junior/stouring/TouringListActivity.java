package com.junior.stouring;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.junior.stouring.drawer.DrawerActivity;
import com.junior.stouring.slidingmenumodel.NavDrawerItem;
import com.junior.stouring.slidingmenuadapter.NavDrawerListAdapter;
import com.junior.stouring.slidingmenuadapter.NavDrawerListMaker;

public class TouringListActivity extends DrawerActivity {
	
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

	Button btnLogout;
	SessionManager session;
	NavDrawerListMaker mNavDrawerMaker;

	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.touringlist);
		Log.i("testMaker", "MAKER LIST");
		
		
 

		session = new SessionManager(getApplicationContext());
		/*if (session.isLoggedIn()) {
			// Button logout
			btnLogout = (Button) findViewById(R.id.btnLogout);

			btnLogout.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// Clear the session data
					// This will clear all session data and
					// redirect user to LoginActivity
					session.logoutUser();
					finish();
				}
			});
		}*/
		
		
		
		
		
	
		
		

	}
	
	

}
