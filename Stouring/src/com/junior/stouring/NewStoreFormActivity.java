package com.junior.stouring;

import java.io.IOException;
import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;
import com.junior.stouring.drawer.DrawerActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewStoreFormActivity extends DrawerActivity {
	
	TouringPlaceDatabaseHelper mDatabaseHelper;
	LatLng tpLatLng;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    // TODO Auto-generated method stub
	    super.onCreate(savedInstanceState);
	    
	    //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	    setContentView(R.layout.activity_newstoreform);
	    
	    final EditText etName = (EditText)findViewById(R.id.et_name);
	    
	    final EditText etAddress = (EditText)findViewById(R.id.et_address);
	    
	    final EditText etEmail = (EditText)findViewById(R.id.et_email);
	    
	    final EditText etCity = (EditText)findViewById(R.id.et_city);
		
	    final Spinner spType = (Spinner)findViewById(R.id.spinnerType);
	    
	    Resources resources = getResources();
		final Bitmap staticImage = (Bitmap)((BitmapDrawable)
		resources.getDrawable(R.drawable.sac_toile_stouring)).getBitmap();
		
		mDatabaseHelper = new TouringPlaceDatabaseHelper(getBaseContext());
		
		Button bValidate = (Button)findViewById(R.id.btnLogin);
		bValidate.setText("Valider");
		bValidate.setOnClickListener(new View.OnClickListener() {
			@Override
				public void onClick(View v) {
					if(isValidEmail(etEmail.getText().toString())) {
						String tpName = etName.getText().toString();
						
						String sType = spType.getSelectedItem().toString();
						
						String tpAddress = etAddress.getText().toString();
						String tpCity = etCity.getText().toString();
						String fAddress = tpAddress+", "+tpCity;
						tpLatLng = getLatLngFromPosition(fAddress);
						
						TouringPlace newTP = new TouringPlace(tpName, (float) 0, sType, staticImage, tpLatLng.latitude, tpLatLng.longitude);
						Toast.makeText(getBaseContext(), fAddress, Toast.LENGTH_SHORT).show();
						mDatabaseHelper.addItem(newTP);
					}else{
						Toast.makeText(
								getBaseContext(),
								"Adresse email invalide",
								Toast.LENGTH_SHORT).show();
					}
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
	
	public final static boolean isValidEmail(CharSequence target) {
		  return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
		}
}

