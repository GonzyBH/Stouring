package com.junior.stouring.tabsswipefragments;

import com.junior.stouring.R;
import com.junior.stouring.TouringPlace;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StoreInfoFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_storeinfo, container, false);
		
		TouringPlace tP = getArguments().getParcelable("TP");
		
		TextView displayPlaceInfo = (TextView) view.findViewById(R.id.tpname);
		displayPlaceInfo.setText("Les offres de " + tP.getName() + " seront ajoutées ici");
		
		return view;
	}

}