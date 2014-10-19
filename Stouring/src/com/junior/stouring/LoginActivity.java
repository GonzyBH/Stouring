package com.junior.stouring;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.*;
import com.facebook.model.*;

public class LoginActivity extends Activity implements OnClickListener {

	private EditText loginStouring;
	private EditText mdpStouring;
	private Button buttonOk;

	SessionManager session;

	
	SharedPreferences pref;
    private static String CONSUMER_KEY = "yKOma854G2LHcPKzbd4q1Os6J";
    private static String CONSUMER_SECRET = "hfFJwl3N7QZMDBHfRqQDl0CkVey3AvX26Gh8u41pERjGICXIdM";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_login);
		
		pref = getPreferences(0);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("CONSUMER_KEY", CONSUMER_KEY);
        edit.putString("CONSUMER_SECRET", CONSUMER_SECRET);
        edit.commit();

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
		// login.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		//
		// Intent launchTouringList = new Intent(LoginActivity.this,
		// TouringListActivity.class);
		// startActivity(launchTouringList);
		// }
		// });

		// Twitter Auth
		Button loginTwitter = (Button) findViewById(R.id.buttontwitter);
		loginTwitter.setText("Connexion via Twitter");

		loginTwitter.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				

			}
		});

		

		Button loginGoogle = (Button) findViewById(R.id.buttongoogle);
		loginGoogle.setText("Connexion via Google");

		Button loginLinkedin = (Button) findViewById(R.id.buttonlinked);
		loginLinkedin.setText("Connexion via LinkedIn");

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
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode,
				resultCode, data);
	}

	

}
