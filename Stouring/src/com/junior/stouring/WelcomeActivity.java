package com.junior.stouring;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class WelcomeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    // TODO Auto-generated method stub
	    super.onCreate(savedInstanceState);
	    
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	    setContentView(R.layout.activity_welcome);
	    Thread timer= new Thread()
	    {
	        public void run()
	        {
	            try
	            {
	                //Display for 3 seconds
	                sleep(3000);
	            }
	            catch (InterruptedException e) 
	            {
	                // TODO: handle exception
	                e.printStackTrace();
	            }
	            finally
	            {   

	            	Intent lunchLoginActivity = new Intent(WelcomeActivity.this, LoginActivity.class);
					startActivity(lunchLoginActivity);
	            }
	        }
	    };
	    timer.start();
	}
}

