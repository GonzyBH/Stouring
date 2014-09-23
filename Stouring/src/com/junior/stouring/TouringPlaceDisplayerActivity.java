package com.junior.stouring;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TouringPlaceDisplayerActivity extends Activity{
	
	TouringPlaceDatabaseHelper mDatabaseHelper;
	int RESULT_DELETE;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpdisplayer);
        
        mDatabaseHelper = new TouringPlaceDatabaseHelper(getBaseContext());
    
        final TouringPlace mTouringPlace = getIntent().getExtras().getParcelable("touringplace");
        TextView displayPlaceInfo = (TextView) findViewById(R.id.displaytp);
        displayPlaceInfo.setText(
        "User : " + "\n"
        + " FirstName : " + mTouringPlace.getName() + "\n"
        + " LastName : " + mTouringPlace.getMark()  + "\n" 
        + " Type : " + mTouringPlace.getType()  + "\n"
        + " Latitude : " + mTouringPlace.getLatitude()  + "\n" 
        + " Longitude : " + mTouringPlace.getLongitude());
        
        ImageView displayPlaceIcon = (ImageView) findViewById(R.id.placeIcon);
        displayPlaceIcon.setImageBitmap(mTouringPlace.getImage());
        
        Button bDelete = (Button)findViewById(R.id.btndelete);
        bDelete.setText("Supprimez");
        bDelete.setOnClickListener(new View.OnClickListener() {
			@Override
				public void onClick(View v) {
					//Toast.makeText(getBaseContext(), "YOLO", Toast.LENGTH_SHORT).show();
					mDatabaseHelper.deleteItem(mTouringPlace);
					Intent returnIntent = new Intent();
					setResult(RESULT_DELETE, returnIntent);
					finish();
					//TouringPlaceDisplayerActivity.this.finish();
			}
		});
    }
}
