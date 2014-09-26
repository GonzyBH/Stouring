package com.junior.stouring;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class NewConnexionActivity extends Activity {
	@Override
		protected void onCreate(Bundle arg0) {
			super.onCreate(arg0);
			
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			
			setContentView(R.layout.activity_newconnexion);
			
			Button buttonstouring = (Button)findViewById(R.id.buttonstore);
			buttonstouring.setText("Nouveau Commerçant");
			buttonstouring.setOnClickListener(new View.OnClickListener() {
				@Override
					public void onClick(View v) {

					Intent lunchStoreForm = new Intent(NewConnexionActivity.this, NewStoreFormActivity.class);
					startActivity(lunchStoreForm);
				} 
			});
			
			Button buttonprivate = (Button)findViewById(R.id.buttonprivate);
			buttonprivate.setText("Nouveau Client");
			buttonprivate.setOnClickListener(new View.OnClickListener() {
				@Override
					public void onClick(View v) {

					Intent lunchUserForm = new Intent(NewConnexionActivity.this, NewUserFormActivity.class);
					startActivity(lunchUserForm);
				} 
			});
	}

}
