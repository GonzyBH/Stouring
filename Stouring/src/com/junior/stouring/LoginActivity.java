package com.junior.stouring;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class LoginActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_login);

		Button accountcreator = (Button) findViewById(R.id.buttonstouring);
		accountcreator.setText("Connexion via Stouring");
		accountcreator.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent launchStouringLogin = new Intent(LoginActivity.this,
						LoginActivity2.class);
				startActivity(launchStouringLogin);
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

		Button loginLinkedin = (Button) findViewById(R.id.buttonlinkedin);
		loginLinkedin.setText("Connexion via LinkedIn");

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
