package com.junior.stouring;

import com.junior.stouring.drawer.DrawerActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewUserFormActivity extends DrawerActivity {

	TouringPlaceDatabaseHelper mDatabaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_newuserform);

		mDatabaseHelper = new TouringPlaceDatabaseHelper(getBaseContext());

		final EditText etFirstname = (EditText) findViewById(R.id.et_firstname);
		final EditText etLastname = (EditText) findViewById(R.id.et_lastname);
		final EditText etEmail = (EditText) findViewById(R.id.et_email);
		final EditText etPassword = (EditText) findViewById(R.id.et_pw);
		final EditText etPasswordVerif = (EditText) findViewById(R.id.et_pwv);

		Button bValidate = (Button) findViewById(R.id.btnLogin);
		bValidate.setText("Valider");
		bValidate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String uFirstName = etFirstname.getText().toString();
				String uLastName = etLastname.getText().toString();
				String uEmail = etEmail.getText().toString();
				String uPass1 = etPassword.getText().toString();
				String uPass2 = etPasswordVerif.getText().toString();

				if (uFirstName.equals("") || uLastName.equals("") || uEmail.equals("")
						|| uPass1.equals("")|| uPass2.equals("")) {
					Toast.makeText(getBaseContext(),
							"Veuillez remplir tous les champs",
							Toast.LENGTH_SHORT).show();
				} else {

					if (uPass1.equals(uPass2)) {

						Toast.makeText(getBaseContext(),
								uFirstName + " " + uLastName,
								Toast.LENGTH_SHORT).show();
						User newUser = new User(uFirstName, uLastName,
								uEmail, uPass1);
						mDatabaseHelper.addUser(newUser);
					} else {
						Toast.makeText(getBaseContext(),
								"Les mots de passe ne correspondent pas !",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

	}

}
