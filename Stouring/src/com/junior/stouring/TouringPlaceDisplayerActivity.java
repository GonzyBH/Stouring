package com.junior.stouring;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TouringPlaceDisplayerActivity extends Activity{

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpdisplayer);
    
        TouringPlace mTouringPlace = getIntent().getExtras().getParcelable("touringplace");
        TextView displayPlaceInfo = (TextView) findViewById(R.id.displaytp);
        displayPlaceInfo.setText("User : " + "\n" + " FirstName : " + mTouringPlace.getName() + "\n" + " LastName : " + mTouringPlace.getMark());
        
        ImageView displayPlaceIcon = (ImageView) findViewById(R.id.placeIcon);
        displayPlaceIcon.setImageBitmap(mTouringPlace.getImage());
    }
}
