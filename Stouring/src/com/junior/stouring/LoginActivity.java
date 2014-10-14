package com.junior.stouring;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener,
		ConnectionCallbacks, OnConnectionFailedListener {

	private EditText loginStouring;
	private EditText mdpStouring;
	private Button buttonOk;

	SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_login);

		loginStouring = (EditText) findViewById(R.id.login_stouring);
		mdpStouring = (EditText) findViewById(R.id.mdp_stouring);
		buttonOk = (Button) findViewById(R.id.buttonOk);
		session = new SessionManager(getApplicationContext());

		Button accountcreator = (Button) findViewById(R.id.buttonstouring);
		accountcreator.setText("Connexion via Stouring");
		accountcreator.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				loginStouring.setVisibility(View.VISIBLE);
				mdpStouring.setVisibility(View.VISIBLE);
				buttonOk.setVisibility(View.VISIBLE);

			}
		});

		buttonOk.setText("OK");
		buttonOk.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// Get email, password from EditText
				String email = loginStouring.getText().toString();
				String password = mdpStouring.getText().toString();

				// Check if email, password is filled
				if (email.trim().length() > 0 && password.trim().length() > 0) {
					// For testing purpose email, password is checked with
					// sample data
					// email = test
					// password = test
					if (email.equals("test") && password.equals("test")) {

						// Creating user login session
						// For testing i am storing name, email as follow
						// Use user real data
						session.createLoginSession("Username", email);

						// Staring MainActivity
						Intent i = new Intent(getApplicationContext(),
								TouringListActivity.class);
						startActivity(i);
						finish();

					} else {
						// email / password doesn't match
						Toast.makeText(
								getBaseContext(),
								"Login failed... Username/Password is incorrect",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					// user didn't entered email or password
					// Show alert asking him to enter the details
					Toast.makeText(
							getBaseContext(),
							"Login failed... Please enter username and password",
							Toast.LENGTH_SHORT).show();
				}

			}

		});

		Button login = (Button) findViewById(R.id.buttonfacebook);
		login.setText("Connexion via Facebook");
		login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent launchTouringList = new Intent(LoginActivity.this,
						TouringListActivity.class);
				startActivity(launchTouringList);
			}
		});

		Button loginTwitter = (Button) findViewById(R.id.buttontwitter);
		loginTwitter.setText("Connexion via Twitter");

		Button loginGoogle = (Button) findViewById(R.id.buttongoogle);
		loginGoogle.setText("Connexion via Google");

		Button loginLinkedin = (Button) findViewById(R.id.buttonlinked);
		loginLinkedin.setText("Connexion via LinkedIn");
		loginLinkedin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent launchUserList = new Intent(LoginActivity.this,
						UserListActivity.class);
				startActivity(launchUserList);
			}
		});

	}

	@Override
	public void onBackPressed() {

		loginStouring = (EditText) findViewById(R.id.login_stouring);
		mdpStouring = (EditText) findViewById(R.id.mdp_stouring);
		buttonOk = (Button) findViewById(R.id.buttonOk);

		if (loginStouring.getVisibility() == View.VISIBLE) {
			loginStouring.setVisibility(View.GONE);
			mdpStouring.setVisibility(View.GONE);
			buttonOk.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub

	}

}
