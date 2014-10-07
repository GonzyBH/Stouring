package com.junior.stouring;

import java.io.IOException;
import java.util.ArrayList;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TouringPlaceDisplayerActivity extends Activity{
	
	TouringPlaceDatabaseHelper mDatabaseHelper;
	int RESULT_DELETE;
	LatLng placePosition;
	// Google Map
    private GoogleMap googleMap;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpdisplayer);
        
        try {
            // Loading map
            initilizeMap();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        mDatabaseHelper = new TouringPlaceDatabaseHelper(getBaseContext());
        
        
        
    
        final TouringPlace mTouringPlace = getIntent().getExtras().getParcelable("touringplace");
        TextView displayPlaceInfo = (TextView) findViewById(R.id.displaytp);
        displayPlaceInfo.setText(
        "User : " + "\n"
        + " FirstName : " + mTouringPlace.getName() + "\n"
        + " Mark : " + mTouringPlace.getMark()  + "\n" 
        + " Type : " + mTouringPlace.getType()  + "\n"
        + " Latitude : " + mTouringPlace.getLatitude()  + "\n" 
        + " Longitude : " + mTouringPlace.getLongitude());
        
        ImageView displayPlaceIcon = (ImageView) findViewById(R.id.placeIcon);
        displayPlaceIcon.setImageBitmap(mTouringPlace.getImage());
        
        placePosition = new LatLng(mTouringPlace.getLatitude(), mTouringPlace.getLongitude());
        
        CameraPosition cameraPosition = new CameraPosition.Builder().target(placePosition).zoom(18).build();
 
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        
        // create marker
        MarkerOptions marker = new MarkerOptions().position(placePosition).title(mTouringPlace.getName());
        // adding marker
        googleMap.addMarker(marker);
        
        Button bDelete = (Button)findViewById(R.id.btndelete);
        bDelete.setText("Supprimer");
        bDelete.setOnClickListener(new View.OnClickListener() {
			@Override
				public void onClick(View v) {
					//Toast.makeText(getBaseContext(), "YOLO", Toast.LENGTH_SHORT).show();
					mDatabaseHelper.deleteItem(mTouringPlace);
					//Intent returnIntent = new Intent();
					//setResult(RESULT_DELETE, returnIntent);
					finish();
					//TouringPlaceDisplayerActivity.this.finish();
			}
		});
    }
	
	 /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
 
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
    
 
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
}
