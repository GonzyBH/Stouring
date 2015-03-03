package com.junior.stouring;

import java.util.ArrayList;

import com.junior.stouring.drawer.DrawerActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends DrawerActivity {
	
	TouringPlaceDatabaseHelper dbHelper;
	ArrayList<String> citiesNames;

	public static final String PREFS_NAME = "UserPrefs";
	public SharedPreferences settings = null ;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_profile);

		// Restore preferences
		settings = getSharedPreferences(PREFS_NAME, 0);
		

		final TextView addressContent = (TextView) findViewById(R.id.addressContent);
		addressContent.setText(settings.getString("addressContent", "aucune adresse"));

		final TextView name = (TextView) findViewById(R.id.name);
		name.setText(settings.getString("name", "John Doe"));

		final TextView myCity = (TextView) findViewById(R.id.myCity);
		myCity.setText(settings.getString("myCity", "Aucune ville définie"));


		addressContent.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				showAlert("Adresse", addressContent, "addressContent", settings);
				
			}


		});
		



		name.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				showAlert("Prénom - Nom", name, "name",settings);
			}


		});
		
		
		myCity.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				dbHelper = new TouringPlaceDatabaseHelper(getBaseContext());
				citiesNames = dbHelper.getAllCitiesNames();
				showAlert("Choisir ma ville", myCity, "myCity",settings);
			}


		});
		

	}

	
	@Override
	public void onResume()
	    {  // After a pause OR at startup
	    super.onResume();
	    //Refresh your stuff here
	    final TextView myCity = (TextView) findViewById(R.id.myCity);
		myCity.setText(settings.getString("myCity", "Aucune ville définie"));
	     }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


public void showAlert(String title, final TextView container, final String containerStr, SharedPreferences settings){

		
		final SharedPreferences.Editor editor = settings.edit();

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				ProfileActivity.this);

		// set title
		alertDialogBuilder.setTitle(title);


		// set dialog message
		ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, citiesNames);
		alertDialogBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            // The 'which' argument contains the index position
            // of the selected item
            	editor.putString(containerStr, citiesNames.get(which));
				editor.commit();
				Toast toast = Toast.makeText(getApplicationContext(), citiesNames.get(which), Toast.LENGTH_SHORT);
				toast.show();
				Intent intent = new Intent(getBaseContext(),
						ListPlacesActivity.class);
				intent.putExtra("cityName", citiesNames.get(which));

				startActivity(intent);
            	
        }
		});
		
		alertDialogBuilder
		.setCancelable(true);


		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}
}
