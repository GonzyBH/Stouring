package com.junior.stouring;

import java.util.ArrayList;

import com.junior.stouring.drawer.DrawerActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MaVilleActivity extends DrawerActivity {
	
	public static final String PREFS_NAME = "UserPrefs";
	
	TouringPlaceDatabaseHelper dbHelper;
	ArrayList<String> citiesNames;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Typeface latoLight = Typeface.createFromAsset(getAssets(), "Lato-Light.ttf");
		
		final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		final TextView myCity = null;
		
		
		
		
		if(settings.contains("myCity")){
			setContentView(R.layout.activity_ma_ville);
		}
		else{
			setContentView(R.layout.activity_ma_ville_undef);
			dbHelper = new TouringPlaceDatabaseHelper(getBaseContext());
			citiesNames = dbHelper.getAllCitiesNames();
			
			Button buttonUndef = (Button) findViewById(R.id.buttonUndef);
			buttonUndef.setText("Choisir");
			buttonUndef.setTypeface(latoLight);
			buttonUndef.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					showAlert("Choisir ma ville", myCity, "myCity",settings);
//					Intent launchDecouvrir = new Intent(HomeActivity.this,
//							CityListActivity.class);
//					startActivity(launchDecouvrir);
				}
			});
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ma_ville, menu);
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
				MaVilleActivity.this);

		// set title
		alertDialogBuilder.setTitle(title);
//
//		// Set an EditText view to get user input 
//		final EditText input = new EditText(MaVilleActivity.this);
//		input.setText("");
//		alertDialogBuilder.setView(input);

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
				finish();
            	
        }
		});
		
		alertDialogBuilder
		.setCancelable(true);
//		.setPositiveButton("OK",new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog,int id) {
////				String newTxt = input.getText().toString();
//				String newTxt = "lolol";
//				editor.putString(containerStr, newTxt);
//				editor.commit();
//
//			}
//		})
//		.setNegativeButton("Annuler",new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog,int id) {
//				// if this button is clicked, just close
//				// the dialog box and do nothing
//				dialog.cancel();
//			}
//		});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}

}
