package com.junior.stouring;

import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DatabaseUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentTouringList extends ListFragment {

	private static String url = "http://stouring.mobi/?db_api=place&request=ALL_PLACES";

	private static final String TAG_CONTACTS = "places";
	private static final String TAG_NID = "nid";
	private static final String TAG_NAME = "name";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_PHONE = "phone";
	private static final String TIMER = "";

	JSONArray contacts = null;

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

		new GetPlaces().execute();


	}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			// retrieve theListView item
			TouringPlace item = items.get(position);
			int cId = item.getId();
			
			Log.d("on list item click ", "id : " + cId + " position : " + position);
			// do something
			// Toast.makeText(getActivity(), item.getName(),
			// Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(getActivity(),
					TouringPlaceDisplayerActivity.class);
			intent.putExtra("placeId", cId);
			getActivity().startActivityForResult(intent, 1);
		}


	private class GetPlaces extends AsyncTask<Void, Void, Void> {


		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(getActivity());
			// Set progressdialog title
			mProgressDialog.setTitle("Mise à jour de la liste des établissements");
			// Set progressdialog message
			mProgressDialog.setMessage("Chargement...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			items = new ArrayList<TouringPlace>();
			dbHelper = new TouringPlaceDatabaseHelper(getActivity());
			// Creating service handler class instance


			Calendar cal = Calendar.getInstance();
			int day = cal.get(Calendar.DAY_OF_MONTH);

			SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
			int lastDayOfRefresh = sharedPref.getInt("refreshTimer",0);

			if(day!=lastDayOfRefresh){

				SharedPreferences.Editor editor = sharedPref.edit();
				editor.putInt("refreshTimer",day);
				editor.commit();

				ServiceHandler sh = new ServiceHandler();


				// Making a request to url and getting response
				String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

				if (jsonStr != null) {
					try {
						JSONObject jsonObj = new JSONObject(jsonStr);

						// Getting JSON Array node
						contacts = jsonObj.getJSONArray(TAG_CONTACTS);

						// looping through All Contacts
						for (int i = 0; i < contacts.length(); i++) {
							JSONObject c = contacts.getJSONObject(i);


							String name = c.getString(TAG_NAME);
							int id = c.getInt(TAG_NID);
							String address = c.getString(TAG_ADDRESS);
							c.getString(TAG_PHONE);


							TouringPlace newTP = new TouringPlace(id, DatabaseUtils.sqlEscapeString(name),
									4,"place", 12, 12);

							//ajout pour affichage
							items.add(newTP);

							//ajout à la db
							if(!dbHelper.checkIfTouringPlaceExists(newTP.getName())){
								dbHelper.addItemNoImage(newTP);
							}




						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					Log.e("ServiceHandler", "Couldn't get any data from the url");
				}


			}

			else{
				items = dbHelper.getAllItems();
				
				
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog

			/**
			 * Updating parsed JSON data into ListView
			 * */
			// initialize and set the list adapter
			mAdapter = new CustomStouringListFragmentAdapter(getActivity(),
					items);
			setListAdapter(mAdapter);
			mProgressDialog.dismiss();
			// remove the dividers from the ListView of the ListFragment
			getListView().setDivider(null);
		}



	}
}

