package com.junior.stouring;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.junior.stouring.drawer.DrawerActivity;

public class TouringListActivity extends DrawerActivity {
	

	Button btnLogout;
	SessionManager session;

	
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
	
	
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//	    // Inflate the menu items for use in the action bar
//	    MenuInflater inflater = getMenuInflater();
//	    inflater.inflate(R.menu.touringlist_activity_actions, menu);
//	    return super.onCreateOptionsMenu(menu);
//	}
//	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//	    // Handle presses on the action bar items
//	    switch (item.getItemId()) {
//	        case R.id.action_refresh:
//	        	refreshMe();
//	            return true;
//	        default:
//	            return super.onOptionsItemSelected(item);
//	    }
//	}
//	
//	
//	public void refreshMe(){
//	
//		// Create new fragment and transaction
//		Fragment newFragment = new Fragment();
//		FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//		// Replace whatever is in the fragment_container view with this fragment,
//		// and add the transaction to the back stack
//		transaction.replace(R.id.touringlist_fragment, newFragment);
//		transaction.addToBackStack(null);
//
//		// Commit the transaction
//		transaction.commit();
//		
//	}
//	
//	

	
	
}
