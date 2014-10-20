package com.junior.stouring;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.junior.stouring.drawer.DrawerActivity;

public class TouringPlaceModifierActivity extends DrawerActivity{
	
	TouringPlaceDatabaseHelper mDatabaseHelper;
	
	SessionManager session;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp_modifier);
        
        mDatabaseHelper = new TouringPlaceDatabaseHelper(getBaseContext());
        
        
        final String mTouringPlaceName = getIntent().getExtras().getString("touringplace");
        
        TouringPlace mTouringPlace = mDatabaseHelper.getTouringPlaceFromDB(mTouringPlaceName);
        
        TextView displayPlaceInfo = (TextView) findViewById(R.id.displaytp);
        displayPlaceInfo.setText(
        "User : " + "\n"
        + " FirstName : " + mTouringPlace.getName() + "\n"
        + " Mark : " + mTouringPlace.getMark()  + "\n" 
        + " Type : " + mTouringPlace.getType()  + "\n"
        + " Latitude : " + mTouringPlace.getLatitude()  + "\n" 
        + " Longitude : " + mTouringPlace.getLongitude());
        
        
        session = new SessionManager(getApplicationContext());
		if (session.isLoggedIn()) {
			// Button logout
			Button btnLogout = (Button) findViewById(R.id.btnunlog);

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
		}
        
	}

}
