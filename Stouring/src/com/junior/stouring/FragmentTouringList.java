package com.junior.stouring;

import java.util.ArrayList;
import org.json.JSONArray;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentTouringList extends ListFragment {

	JSONArray places = null;
	JSONArray cities = null;

	ProgressDialog mProgressDialog;

	TouringPlaceDatabaseHelper dbHelper;

	ArrayList<TouringPlace> items;

	TouringPlaceDatabaseHelper mDatabaseHelper;

	CustomStouringListFragmentAdapter mAdapter;

	int RESULT_DELETE, RESULT_CANCELED;


	// OULALADIDON

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_fragment_touringlist,
				container, false);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		

		
		final String cityName = getActivity().getIntent().getExtras().getString("cityName");
		final PlaceType placeType = getArguments().getParcelable("placeType");


		dbHelper = new TouringPlaceDatabaseHelper(getActivity());
		if(placeType == null){
			items = dbHelper.getCityItems(cityName);
		}
		else{
			items = dbHelper.getAllByCityAndType(cityName,placeType);
		}

		// initialize and set the list adapter
					mAdapter = new CustomStouringListFragmentAdapter(getActivity(),
							items);
					setListAdapter(mAdapter);
					// remove the dividers from the ListView of the ListFragment
					getListView().setDivider(null);

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			// retrieve theListView item
			TouringPlace item = items.get(position);
			int cId = item.getId();
			
			Log.d("on list item click ", "id : " + cId + " position : " + position);
			Intent intent = new Intent(getActivity(),
					TouringPlaceDisplayerActivity.class);
			intent.putExtra("placeId", cId);
			getActivity().startActivityForResult(intent, 1);
		}



}

