package com.junior.stouring;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentTouringList extends ListFragment {

	private List<TouringPlace> mItems;

	private List<TouringPlace> mItemstemps;

	TouringPlaceDatabaseHelper mDatabaseHelper;

	CustomStouringListFragmentAdapter mAdapter;

	int RESULT_DELETE, RESULT_CANCELED;

	// OULALADIDON

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Toast.makeText(getActivity(), "CREATEVIEW", Toast.LENGTH_SHORT).show();
		return inflater.inflate(R.layout.activity_fragment_touringlist,
				container, false);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Toast.makeText(getActivity(), "CREATE", Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Toast.makeText(getActivity(), "VIEW", Toast.LENGTH_SHORT).show();
		// initialize the database helper
		mDatabaseHelper = new TouringPlaceDatabaseHelper(getActivity());


		// initialize the items list
		mItems = mDatabaseHelper.getAllItems();

		mItems = new ArrayList<TouringPlace>();

		Resources resources = getResources();
		
		/*Bitmap bitmap = (Bitmap)((BitmapDrawable)
		resources.getDrawable(R.drawable.sac_toile_stouring)).getBitmap();
		Bitmap bitmap1 = (Bitmap)((BitmapDrawable)
		resources.getDrawable(R.drawable.stouringlogomin)).getBitmap();*/
		

		mDatabaseHelper.addItems(mItems);

		mItemstemps = mDatabaseHelper.getAllItems();
		// mItemstemps = mDatabaseHelper.getPlaceType(type);

		// initialize and set the list adapter
		mAdapter = new CustomStouringListFragmentAdapter(getActivity(),
				mItemstemps);
		setListAdapter(mAdapter);

		// remove the dividers from the ListView of the ListFragment
		getListView().setDivider(null);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// retrieve theListView item
		TouringPlace item = mItemstemps.get(position);
		String cName = item.getName();
		// do something
		// Toast.makeText(getActivity(), item.getName(),
		// Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(getActivity(),
				TouringPlaceDisplayerActivity.class);
		intent.putExtra("touringplace", cName);
		getActivity().startActivityForResult(intent, 1);
	}

	/*
	 * @Override public void onActivityResult(int requestCode, int resultCode,
	 * Intent intent) { super.onActivityResult(requestCode, resultCode, intent);
	 * Toast.makeText(getActivity(), "activityResult",
	 * Toast.LENGTH_SHORT).show(); if (requestCode == 1) { if(resultCode ==
	 * RESULT_DELETE){ Toast.makeText(getActivity(), "onResume()delete",
	 * Toast.LENGTH_SHORT).show(); } if (resultCode == RESULT_CANCELED) {
	 * Toast.makeText(getActivity(), "onResume()cacnceled",
	 * Toast.LENGTH_SHORT).show(); //Write your code if there's no result } }
	 * //Toast.makeText(getActivity(), "onResume()", Toast.LENGTH_SHORT).show();
	 * mAdapter.notifyDataSetChanged(); }
	 */

	public void onResume() {
		super.onResume();
		mAdapter.notifyDataSetChanged();
		setListAdapter(mAdapter);

		// Toast.makeText(getActivity(), "onResume()",
		// Toast.LENGTH_SHORT).show();
	}

}
