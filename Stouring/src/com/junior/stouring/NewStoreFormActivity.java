package com.junior.stouring;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewStoreFormActivity extends Activity {
	
	TouringPlaceDatabaseHelper mDatabaseHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    // TODO Auto-generated method stub
	    super.onCreate(savedInstanceState);
	    
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	    setContentView(R.layout.activity_newstoreform);
	    
	    final EditText etName = (EditText)findViewById(R.id.et_name);
		
	    final Spinner spType = (Spinner)findViewById(R.id.spinnerType);
		
		mDatabaseHelper = new TouringPlaceDatabaseHelper(getBaseContext());
		
		Button bValidate = (Button)findViewById(R.id.btnLogin);
		bValidate.setText("Validez");
		bValidate.setOnClickListener(new View.OnClickListener() {
			@Override
				public void onClick(View v) {
					String tpName = etName.getText().toString();
					String sType = spType.getSelectedItem().toString();
					//Toast.makeText(getBaseContext(), tpName, Toast.LENGTH_SHORT).show();
					TouringPlace newTP = new TouringPlace(tpName, (float) 0, sType, 0.0, 0.0);
					mDatabaseHelper.addItem(newTP);
			}
		});
	}
}

