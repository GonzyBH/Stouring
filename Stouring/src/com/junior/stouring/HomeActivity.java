package com.junior.stouring;

import com.junior.stouring.drawer.DrawerActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends DrawerActivity {

	public static final String PREFS_NAME = "UserPrefs";
	
	@Override
	protected void onCreate(Bundle arg0) {
		
		super.onCreate(arg0);
		setContentView(R.layout.activity_home);
		
		
		final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		
		

		
		Typeface latoLight = Typeface.createFromAsset(getAssets(), "Lato-Light.ttf");
		
		Button buttonMaVille = (Button) findViewById(R.id.buttonmaville);
		buttonMaVille.setText("Ma ville");
		buttonMaVille.setTypeface(latoLight);
		buttonMaVille.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(settings.contains("myCity")){
					Intent launchMaVille = new Intent(HomeActivity.this,
							MaVilleActivity.class);
					startActivity(launchMaVille);
					
				}
				else{
					Intent launchMaVilleUndef = new Intent(HomeActivity.this,
							MaVilleUndefActivity.class);
					startActivity(launchMaVilleUndef);
				}
			
				
			}
		});
		
		Button buttonDecouvrir = (Button) findViewById(R.id.buttondecouvrir);
		buttonDecouvrir.setText("Découvrir");
		buttonDecouvrir.setTypeface(latoLight);
		buttonDecouvrir.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			
				Intent launchDecouvrir = new Intent(HomeActivity.this,
						CityListActivity.class);
				startActivity(launchDecouvrir);
			}
		});
		
		Button buttonAchats = (Button) findViewById(R.id.buttonachats);
		buttonAchats.setText("Achats");
		buttonAchats.setTypeface(latoLight);
		buttonAchats.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			
				Toast.makeText(getBaseContext(), "Unused at this time", Toast.LENGTH_LONG).show();

				/*Intent launchTest = new Intent(HomeActivity.this,
						ListPlacesActivity.class);
				startActivity(launchTest);*/
			}
		});
		
		Button buttonProfil = (Button) findViewById(R.id.buttonprofil);
		buttonProfil.setText("Mon Profil");
		buttonProfil.setTypeface(latoLight);
		buttonProfil.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			
				Intent launchProfile = new Intent(HomeActivity.this,
						ProfileActivity.class);
				startActivity(launchProfile);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
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
}
