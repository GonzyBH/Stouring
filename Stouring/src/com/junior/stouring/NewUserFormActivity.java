package com.junior.stouring;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class NewUserFormActivity extends Activity{
	
	TouringPlaceDatabaseHelper mDatabaseHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    // TODO Auto-generated method stub
	    super.onCreate(savedInstanceState);
	    
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	    setContentView(R.layout.activity_newuserform);
		
		mDatabaseHelper = new TouringPlaceDatabaseHelper(getBaseContext());
		
		final EditText etFirstname = (EditText)findViewById(R.id.et_firstname);
		final EditText etLastname = (EditText)findViewById(R.id.et_lastname);
		final EditText etEmail = (EditText)findViewById(R.id.et_email);
		final EditText etPassword = (EditText)findViewById(R.id.et_pw);
		final EditText etPasswordVerif = (EditText)findViewById(R.id.et_pwv);
		
		Button bValidate = (Button)findViewById(R.id.btnLogin);
		bValidate.setText("Validez");
		bValidate.setOnClickListener(new View.OnClickListener() {
			@Override
				public void onClick(View v) {
					String ufirstName = etFirstname.getText().toString();
					String uLastName = etLastname.getText().toString();
					String uEmail = etEmail.getText().toString();
					//Toast.makeText(getBaseContext(), tpName, Toast.LENGTH_SHORT).show();
					User newUser = new User(1, ufirstName, uLastName, uEmail, "pw");
					mDatabaseHelper.addUser(newUser);
			}
		});
	    
	    
	}
	
}
