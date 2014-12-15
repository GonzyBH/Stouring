package com.junior.stouring;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import com.junior.stouring.drawer.DrawerActivity;

public class TouringListActivity extends DrawerActivity {
	

	Button btnLogout;
	SessionManager session;

	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);


		setContentView(R.layout.touringlist);
		Log.i("testMaker", "MAKER LIST");
		
		session = new SessionManager(getApplicationContext());

		
		
		
		
	
		
		

	}

	
	
}
