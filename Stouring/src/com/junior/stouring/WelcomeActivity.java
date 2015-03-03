package com.junior.stouring;

import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DatabaseUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

public class WelcomeActivity extends Activity {
	
	public static final String PREFS_NAME = "UserPrefs";

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


	SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		session = new SessionManager(getApplicationContext());

		setContentView(R.layout.activity_welcome);
//		Thread timer = new Thread() {
//			public void run() {
//				try {
					// Display for 3 seconds
					new GetPlaces(getApplicationContext()).execute();
//					sleep(3000);
//				} catch (InterruptedException e) {
					// TODO: handle exception
//					e.printStackTrace();
//				}
//				finally {
//					Intent launchHomeActivity = new Intent(
//							WelcomeActivity.this, HomeActivity.class);
//					startActivity(launchHomeActivity);
//					finish();
//
//				}
//			}
//		};
//		if (session.isLoggedIn()) {
//
//			Intent lunchMainActivity = new Intent(WelcomeActivity.this,
//					TouringListActivity.class);
//			startActivity(lunchMainActivity);
//			finish();
//		} else {
//
//			timer.start();
//
//		}

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
//			else{
//			
//				// Create a progressdialog
//				mProgressDialog = new ProgressDialog(getBaseContext());
//				// Set progressdialog title
//				mProgressDialog.setTitle("Mise à jour de la liste des établissements");
//				// Set progressdialog message
//				mProgressDialog.setMessage("Chargement...");
//				mProgressDialog.setIndeterminate(false);
//				// Show progressdialog
//				mProgressDialog.setCancelable(false);
//				mProgressDialog.show();
//			}
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			items = new ArrayList<City>();
			dbHelper = new TouringPlaceDatabaseHelper(getBaseContext());
			// Creating service handler class instance
			
			
			


			Calendar cal = Calendar.getInstance();
			int day = cal.get(Calendar.DAY_OF_MONTH);

			SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
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
			Intent launchHomeActivity = new Intent(
					WelcomeActivity.this, HomeActivity.class);
			startActivity(launchHomeActivity);
			finish();
		}
		
		



	}
	

}
