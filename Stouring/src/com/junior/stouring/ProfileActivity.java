package com.junior.stouring;

import com.junior.stouring.drawer.DrawerActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends DrawerActivity {

	public static final String PREFS_NAME = "UserPrefs";

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_profile);

		// Restore preferences
		final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		

		final TextView addressContent = (TextView) findViewById(R.id.addressContent);
		addressContent.setText(settings.getString("addressContent", "aucune adresse"));

		final TextView name = (TextView) findViewById(R.id.name);
		name.setText(settings.getString("name", "John Doe"));

		final TextView myCity = (TextView) findViewById(R.id.myCity);
		myCity.setText(settings.getString("myCity", "Ici c'est Paris maggle"));


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
				showAlert("Choisir ma ville", myCity, "myCity",settings);
			}


		});
		

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
		
		String content = container.getText().toString();
		
		final SharedPreferences.Editor editor = settings.edit();

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				ProfileActivity.this);

		// set title
		alertDialogBuilder.setTitle(title);

		// Set an EditText view to get user input 
		final EditText input = new EditText(ProfileActivity.this);
		input.setText(content);
		alertDialogBuilder.setView(input);

		// set dialog message
		alertDialogBuilder
		.setCancelable(false)
		.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				String newTxt = input.getText().toString();
				container.setText(newTxt);
				editor.putString(containerStr, newTxt);
				editor.commit();

			}
		})
		.setNegativeButton("Annuler",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// if this button is clicked, just close
				// the dialog box and do nothing
				dialog.cancel();
			}
		});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}

}
