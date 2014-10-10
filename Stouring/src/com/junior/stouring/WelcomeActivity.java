package com.junior.stouring;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class WelcomeActivity extends Activity {

	SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		session = new SessionManager(getApplicationContext());

		setContentView(R.layout.activity_welcome);
		Thread timer = new Thread() {
			public void run() {
				try {
					// Display for 3 seconds
					sleep(3000);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					Intent lunchLoginActivity = new Intent(
							WelcomeActivity.this, LoginActivity.class);
					startActivity(lunchLoginActivity);
					finish();

				}
			}
		};
		if (session.isLoggedIn()) {

			Intent lunchMainActivity = new Intent(WelcomeActivity.this,
					TouringListActivity.class);
			startActivity(lunchMainActivity);
			finish();
		} else {

			timer.start();

		}

	}
}
