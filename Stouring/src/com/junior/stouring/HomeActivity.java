package com.junior.stouring;

import com.junior.stouring.drawer.DrawerActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends DrawerActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		
		super.onCreate(arg0);
		setContentView(R.layout.activity_home);
		
		
		
		Typeface latoLight = Typeface.createFromAsset(getAssets(), "Lato-Light.ttf");
		
		Button buttonMaVille = (Button) findViewById(R.id.buttonmaville);
		buttonMaVille.setText("Ma ville");
		buttonMaVille.setTypeface(latoLight);
		
		Button buttonDecouvrir = (Button) findViewById(R.id.buttondecouvrir);
		buttonDecouvrir.setText("D�couvrir");
		buttonDecouvrir.setTypeface(latoLight);
		buttonDecouvrir.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			
				Intent launchDecouvrir = new Intent(HomeActivity.this,
						TouringListActivity.class);
				startActivity(launchDecouvrir);
			}
		});
		
		Button buttonAchats = (Button) findViewById(R.id.buttonachats);
		buttonAchats.setText("Achats");
		buttonAchats.setTypeface(latoLight);
		
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
