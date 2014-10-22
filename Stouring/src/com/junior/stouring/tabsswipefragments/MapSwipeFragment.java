package com.junior.stouring.tabsswipefragments;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.MarkerOptions;
import com.junior.stouring.R;
import com.junior.stouring.TouringPlace;
import com.junior.stouring.TouringPlaceModifierActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapSwipeFragment extends Fragment {
	
	private static LatLng placePosition;
	// Google Map
    private static GoogleMap googleMap;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_map, container, false);
		
		TouringPlace tP = getArguments().getParcelable("TP");
		
		setUpMapIfNeeded(tP); // For setting up the MapFragment
		
		return rootView;
	}
	
	/***** Sets up the map if it is possible to do so *****/
	public static void setUpMapIfNeeded(TouringPlace ptP) {
	    // Do a null check to confirm that we have not already instantiated the map.
	    if (googleMap == null) {
	        // Try to obtain the map from the SupportMapFragment.
	        googleMap = ((MapFragment) TouringPlaceModifierActivity.fragmentManager
	                .findFragmentById(R.id.map)).getMap();
	        // Check if we were successful in obtaining the map.
	        if (googleMap != null)
	            setUpMap(ptP);
	    }
	}
	
	private static void setUpMap(TouringPlace ptP) {
	    // For showing a move to my loction button
	    //googleMap.setMyLocationEnabled(true);
	    // For dropping a marker at a point on the Map
	    googleMap.addMarker(new MarkerOptions().position(new LatLng(ptP.getLatitude(), ptP.getLongitude())).title(ptP.getName()).snippet("Home Address"));
	    // For zooming automatically to the Dropped PIN Location
	    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(ptP.getLatitude(),
	            ptP.getLongitude()), 18.0f));
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		TouringPlace tP = getArguments().getParcelable("TP");
	    // TODO Auto-generated method stub
	    if (googleMap != null)
	        setUpMap(tP);

	    if (googleMap == null) {
	        // Try to obtain the map from the SupportMapFragment.
	        googleMap = ((MapFragment) TouringPlaceModifierActivity.fragmentManager
	                .findFragmentById(R.id.map)).getMap();
	        // Check if we were successful in obtaining the map.
	        if (googleMap != null)
	            setUpMap(tP);
	    }
	}
	
	@Override
	public void onDestroyView() {
		Log.i("MapFragment", "OnDestroy Map");
	    super.onDestroyView();
	    if (googleMap != null) {
	    	TouringPlaceModifierActivity.fragmentManager.beginTransaction()
	            .remove(TouringPlaceModifierActivity.fragmentManager.findFragmentById(R.id.map)).commit();
	        googleMap = null;
	    }
	}

	

}