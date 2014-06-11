package com.junior.stouring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

public class FragmentTouringList extends ListFragment {
	
	String[] countries = {"Belgique", "France", "Allemagne", "Italie", "Russie", "Portugal", "Hollande", "Espagne", "Suisse", "Luxembourg"};
	String[] note = new String[]{"1","2","3","4","5","6","7","8","9","10"};
	
	// OULALADIDON
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_fragment_touringlist, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		List<Integer> items = new ArrayList<Integer>();
		items.add(R.string.default_lorem);
		items.add(R.string.default_lorem);
		items.add(R.string.default_lorem);
		items.add(R.string.default_lorem);

		CustomStouringListFragmentAdapter adapter = new CustomStouringListFragmentAdapter(getActivity());
		adapter.bind(items);
		setListAdapter(adapter);

	}


	/*@Override
		public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		final String[] items = myArray;
		final ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, items);

		setListAdapter(aa);
	}*/




}
