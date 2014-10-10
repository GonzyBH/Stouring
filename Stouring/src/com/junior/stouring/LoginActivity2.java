package com.junior.stouring;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity2 extends Activity {

	// Email, password edittext
	EditText txtUsername, txtPassword;

	// login button
	Button btnLogin;

	// Session Manager Class
	SessionManager session;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_login2);

		// Session Manager
		session = new SessionManager(getApplicationContext());

		// Email, Password input text
		txtUsername = (EditText) findViewById(R.id.txtUsername);
		txtPassword = (EditText) findViewById(R.id.txtPassword);

		Toast.makeText(getApplicationContext(),
				"User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG)
				.show();

		// Login button
		btnLogin = (Button) findViewById(R.id.btnLogin);

		// Login button click event
		btnLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// Get username, password from EditText
				String username = txtUsername.getText().toString();
				String password = txtPassword.getText().toString();

				// Check if username, password is filled
				if (username.trim().length() > 0
						&& password.trim().length() > 0) {
					// For testing puspose username, password is checked with
					// sample data
					// username = test
					// password = test
					if (username.equals("test") && password.equals("test")) {

						// Creating user login session
						// For testing i am stroing name, email as follow
						// Use user real data
						session.createLoginSession("Android Hive",
								"anroidhive@gmail.com");

						// Staring MainActivity
						Intent i = new Intent(getApplicationContext(),
								TouringListActivity.class);
						startActivity(i);
						finish();

					} else {
						// username / password doesn't match
						Toast.makeText(
								getBaseContext(),
								"Login failed... Username/Password is incorrect",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					// user didn't entered username or password
					// Show alert asking him to enter the details
					Toast.makeText(
							getBaseContext(),
							"Login failed... Please enter username and password",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
	}
}