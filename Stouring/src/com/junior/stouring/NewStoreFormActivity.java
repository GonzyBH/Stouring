package com.junior.stouring;

import java.io.IOException;
import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewStoreFormActivity extends Activity {
	
	TouringPlaceDatabaseHelper mDatabaseHelper;
	LatLng tpLatLng;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    // TODO Auto-generated method stub
	    super.onCreate(savedInstanceState);
	    
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	    setContentView(R.layout.activity_newstoreform);
	    
	    final EditText etName = (EditText)findViewById(R.id.et_name);
	    
	    final EditText etAddress = (EditText)findViewById(R.id.et_address);
	    
	    final EditText etCity = (EditText)findViewById(R.id.et_city);
		
	    final Spinner spType = (Spinner)findViewById(R.id.spinnerType);
		
		mDatabaseHelper = new TouringPlaceDatabaseHelper(getBaseContext());
		
		Button bValidate = (Button)findViewById(R.id.btnLogin);
		bValidate.setText("Valider");
		bValidate.setOnClickListener(new View.OnClickListener() {
			@Override
				public void onClick(View v) {
					String tpName = etName.getText().toString();
					
					String sType = spType.getSelectedItem().toString();
					
					String tpAddress = etAddress.getText().toString();
					String tpCity = etCity.getText().toString();
					String fAddress = tpAddress+", "+tpCity;
					tpLatLng = getLatLngFromPosition(fAddress);
					TouringPlace newTP = new TouringPlace(tpName, (float) 0, sType, tpLatLng.latitude, tpLatLng.longitude);
					Toast.makeText(getBaseContext(), fAddress, Toast.LENGTH_SHORT).show();
					mDatabaseHelper.addItem(newTP);
			}
		});
	}
	
	private LatLng getLatLngFromPosition(String address){
  	  Geocoder coder = new Geocoder(this);
  	  double longitude = 0.0, latitude = 0.0;
  	    try {
  	        ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(address, 50);
  	        for(Address add : adresses){
  	           //Controls to ensure it is right address such as country etc.
  	                longitude = add.getLongitude();
  	                latitude = add.getLatitude();
  	            }
  	        
  	    } catch (IOException e) {
  	        e.printStackTrace();
  	    }
  	    
  	    LatLng platlng = new LatLng(latitude, longitude);
  	    return platlng;
  }
}

