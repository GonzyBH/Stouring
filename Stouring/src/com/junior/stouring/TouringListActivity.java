package com.junior.stouring;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class TouringListActivity extends FragmentActivity {

	Button btnLogout;
	SessionManager session;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.touringlist);

		session = new SessionManager(getApplicationContext());

		// Button logout
		btnLogout = (Button) findViewById(R.id.btnLogout);

		/**
		 * Logout button click event
		 * */
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
