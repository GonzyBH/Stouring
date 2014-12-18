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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentCityList extends ListFragment {

	private static String url = "http://api.stouring.mobi/?db_api=place&request=ALL_PLACES";

	private static final String TAG_PLACES = "places";
	private static final String TAG_CITY = "city";
	private static final String TAG_CITIES = "cities";
	private static final String TAG_LATITUDE = "latitude";
	private static final String TAG_LONGITUDE = "longitude";
	private static final String TAG_TYPES = "types";
	private static final String TAG_TYPE = "type";
	private static final String TAG_TID = "tid";
	private static final String TAG_NID = "nid";
	private static final String TAG_NAME = "name";
	//private static final String TAG_ADDRESS = "address";
	private static final String TAG_PHONE = "phone";
	private static final String TAG_RATING = "rating";
	//private static final String TIMER = "";

	JSONArray places = null;
	JSONArray cities = null;
	JSONArray types = null;

	ProgressDialog mProgressDialog;

	TouringPlaceDatabaseHelper dbHelper;

	ArrayList<City> items;

	TouringPlaceDatabaseHelper mDatabaseHelper;

	CustomCityListFragmentAdapter mAdapter;

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

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		new GetPlaces(getActivity().getApplicationContext()).execute();


	}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			// retrieve theListView item
			City item = items.get(position);
			String cName = item.getName();
			
			Log.d("on list item click ", "name : " + cName + " position : " + position);
			// do something
			// Toast.makeText(getActivity(), item.getName(),
			// Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(getActivity(),
					ListPlacesActivity.class);
			intent.putExtra("cityName", cName);
			getActivity().startActivityForResult(intent, 1);
		}


	private class GetPlaces extends AsyncTask<Void, Void, Void> {
		
		Context mContext;
		public GetPlaces (Context context){
	         mContext = context;
	    }


		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			ConnectivityManager cm =
			        (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
			 
			NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
			boolean isConnected = activeNetwork != null &&
			                      activeNetwork.isConnectedOrConnecting();
			
			if(!isConnected){
				
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(mContext, "Aucune connexion : impossible de rafraîchir la liste.", duration);
				toast.show();
				cancel(true);
			}
			else{
			
				// Create a progressdialog
				mProgressDialog = new ProgressDialog(getActivity());
				// Set progressdialog title
				mProgressDialog.setTitle("Mise à jour de la liste des établissements");
				// Set progressdialog message
				mProgressDialog.setMessage("Chargement...");
				mProgressDialog.setIndeterminate(false);
				// Show progressdialog
				mProgressDialog.setCancelable(false);
				mProgressDialog.show();
			}
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			items = new ArrayList<City>();
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
						places = jsonObj.getJSONArray(TAG_PLACES);

						// looping through All Contacts
						for (int i = 0; i < places.length(); i++) {
							JSONObject p = places.getJSONObject(i);


							String name = p.getString(TAG_NAME);
							int id = p.getInt(TAG_NID);
							//String address = c.getString(TAG_ADDRESS);
							p.getString(TAG_PHONE);
							
							String sLatitude = p.getString(TAG_LATITUDE);
							String sLongitude = p.getString(TAG_LONGITUDE);
							
							double dLatitude = 0;
							double dLongitude = 0;
							
							if(!sLatitude.isEmpty() && !sLongitude.isEmpty()){
								dLatitude = Double.parseDouble(sLatitude);
								dLongitude = Double.parseDouble(sLongitude);
							}
							
							String type = p.getString(TAG_TYPE);
							
							int rating = p.getInt(TAG_RATING)/20;
							String city = p.getString(TAG_CITY);


							TouringPlace newTP = new TouringPlace(id, DatabaseUtils.sqlEscapeString(name),
									rating,type, city, dLatitude, dLongitude);

							

							//ajout à la db
							if(!dbHelper.checkIfTouringPlaceExists(newTP.getName())){
								dbHelper.addItemNoImage(newTP);
							}
						
						
						


						}
						
						cities = jsonObj.getJSONArray(TAG_CITIES);
						
						for (int i = 0; i < cities.length(); i++) {	
							JSONObject c = cities.getJSONObject(i);
							
							int id = c.getInt(TAG_TID);
							String name = c.getString(TAG_NAME);
							City newCity = new City(id, name);
							
							//ajout pour affichage
							items.add(newCity);
							
							if(!dbHelper.checkIfCityExists(newCity.getId())){
								dbHelper.addCity(newCity);
							}
						}
						
						
						types = jsonObj.getJSONArray(TAG_TYPES);
						
						for (int k = 0; k < types.length(); k++) {	
							JSONObject t = types.getJSONObject(k);
							
							int tId = t.getInt(TAG_TID);
							String tName = t.getString(TAG_NAME);
							Log.d("type name", tName);
							PlaceType newType = new PlaceType(tId, tName);

							
							if(!dbHelper.checkIfPlaceTypeExists(newType.getId())){
								dbHelper.addPlaceType(newType);
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
				items = dbHelper.getAllCities();
				
				
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
			mAdapter = new CustomCityListFragmentAdapter(getActivity(),
					items);
			setListAdapter(mAdapter);
			mProgressDialog.dismiss();
			// remove the dividers from the ListView of the ListFragment
			getListView().setDivider(null);
		}
		
		



	}
	


}

