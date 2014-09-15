package com.junior.stouring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

public class FragmentTouringList extends ListFragment {
	
	private List<TouringPlace> mItems;
	/*String[] countries = {"Belgique", "France", "Allemagne", "Italie", "Russie", "Portugal", "Hollande", "Espagne", "Suisse", "Luxembourg"};
	String[] note = new String[]{"1","2","3","4","5","6","7","8","9","10"};*/
	
	// OULALADIDON
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_fragment_touringlist, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		 mItems = new ArrayList<TouringPlace>();
		 Resources resources = getResources();
		 mItems.add(new TouringPlace("France", (float) 4, 2));
		 mItems.add(new TouringPlace("Allemagne", (float) 3.5, 2));
		 mItems.add(new TouringPlace("Royaume Uni", (float) 2.5, 2));
		 mItems.add(new TouringPlace("Russie", (float) 1.5, 2));
		 mItems.add(new TouringPlace("Italie", (float) 2.7, 2));
		 
		// initialize and set the list adapter
		 setListAdapter(new CustomStouringListFragmentAdapter(getActivity(), mItems));
	}
	
	 @Override
	 public void onViewCreated(View view, Bundle savedInstanceState) {
	 super.onViewCreated(view, savedInstanceState);
	 // remove the dividers from the ListView of the ListFragment
	 getListView().setDivider(null);
	 }





}
