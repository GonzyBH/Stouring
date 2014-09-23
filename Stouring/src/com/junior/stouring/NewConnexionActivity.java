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
			buttonstouring.setText("Nouveau Commer�ant");
			buttonstouring.setOnClickListener(new View.OnClickListener() {
				@Override
					public void onClick(View v) {

					Intent lunchTouringList = new Intent(NewConnexionActivity.this, NewStoreFormActivity.class);
					startActivity(lunchTouringList);
				} 
			});
			
			Button buttonprivate = (Button)findViewById(R.id.buttonprivate);
			buttonprivate.setText("Nouveau Client");
	}

}
