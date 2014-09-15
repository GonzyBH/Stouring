package com.junior.stouring;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;



public class TouringListActivity extends FragmentActivity {
	@Override
		protected void onCreate(Bundle arg0) {
			super.onCreate(arg0);
			
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			
			setContentView(R.layout.touringlist);
	}

}

