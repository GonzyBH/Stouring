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
		
		dbHelper = new TouringPlaceDatabaseHelper(getActivity());
		items = dbHelper.getCityItems(cityName);
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
		
		

		//new GetPlaces(getActivity().getApplicationContext()).execute();


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


//	private class GetPlaces extends AsyncTask<Void, Void, Void> {
//		
//		Context mContext;
//		public GetPlaces (Context context){
//	         mContext = context;
//	    }
//
//
//		@Override
//		protected void onPreExecute() {
//			super.onPreExecute();
//			
//			ConnectivityManager cm =
//			        (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
//			 
//			NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//			boolean isConnected = activeNetwork != null &&
//			                      activeNetwork.isConnectedOrConnecting();
//			
//			if(!isConnected){
//				
//				int duration = Toast.LENGTH_SHORT;
//
//				Toast toast = Toast.makeText(mContext, "Aucune connexion : impossible de rafraîchir la liste.", duration);
//				toast.show();
//				cancel(true);
//			}
//			else{
//			
//				// Create a progressdialog
//				mProgressDialog = new ProgressDialog(getActivity());
//				// Set progressdialog title
//				mProgressDialog.setTitle("Mise à jour de la liste des établissements");
//				// Set progressdialog message
//				mProgressDialog.setMessage("Chargement...");
//				mProgressDialog.setIndeterminate(false);
//				// Show progressdialog
//				mProgressDialog.setCancelable(false);
//				mProgressDialog.show();
//			}
//		}
//
//		@Override
//		protected Void doInBackground(Void... params) {
//			// TODO Auto-generated method stub
//
//			items = new ArrayList<TouringPlace>();
//			dbHelper = new TouringPlaceDatabaseHelper(getActivity());
//			// Creating service handler class instance
//
//
//			Calendar cal = Calendar.getInstance();
//			int day = cal.get(Calendar.DAY_OF_MONTH);
//
//			SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
//			int lastDayOfRefresh = sharedPref.getInt("refreshTimer",0);
//
//			if(day!=lastDayOfRefresh){
//
//				SharedPreferences.Editor editor = sharedPref.edit();
//				editor.putInt("refreshTimer",day);
//				editor.commit();
//
//				ServiceHandler sh = new ServiceHandler();
//
//
//				// Making a request to url and getting response
//				String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
//
//				if (jsonStr != null) {
//					try {
//						JSONObject jsonObj = new JSONObject(jsonStr);
//
//						// Getting JSON Array node
//						places = jsonObj.getJSONArray(TAG_PLACES);
//
//						// looping through All Contacts
//						for (int i = 0; i < places.length(); i++) {
//							JSONObject p = places.getJSONObject(i);
//
//
//							String name = p.getString(TAG_NAME);
//							int id = p.getInt(TAG_NID);
//							//String address = c.getString(TAG_ADDRESS);
//							p.getString(TAG_PHONE);
//							
//							int rating = p.getInt(TAG_RATING)/20;
//							String city = p.getString(TAG_CITY);
//
//
//							TouringPlace newTP = new TouringPlace(id, DatabaseUtils.sqlEscapeString(name),
//									rating,"place", city, 12, 12);
//
//							//ajout pour affichage
//							items.add(newTP);
//
//							//ajout à la db
//							if(!dbHelper.checkIfTouringPlaceExists(newTP.getName())){
//								dbHelper.addItemNoImage(newTP);
//							}
//						
//						
//						
//
//
//						}
//						
//						cities = jsonObj.getJSONArray(TAG_CITIES);
//						
//						for (int i = 0; i < cities.length(); i++) {	
//							JSONObject c = cities.getJSONObject(i);
//							
//							int id = c.getInt(TAG_TID);
//							String name = c.getString(TAG_NAME);
//							
//							City newCity = new City(id, name);
//							
//							if(!dbHelper.checkIfCityExists(newCity.getId())){
//								dbHelper.addCity(newCity);
//							}
//						}
//						
//						
//						
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//				} else {
//					Log.e("ServiceHandler", "Couldn't get any data from the url");
//				}
//
//
//			}
//
//			else{
//				items = dbHelper.getAllItems();
//				
//				
//			}
//
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(Void result) {
//			super.onPostExecute(result);
//			// Dismiss the progress dialog
//
//			/**
//			 * Updating parsed JSON data into ListView
//			 * */
//			// initialize and set the list adapter
//			mAdapter = new CustomStouringListFragmentAdapter(getActivity(),
//					items);
//			setListAdapter(mAdapter);
//			mProgressDialog.dismiss();
//			// remove the dividers from the ListView of the ListFragment
//			getListView().setDivider(null);
//		}
//		
//		
//
//
//
//	}
//	


}

