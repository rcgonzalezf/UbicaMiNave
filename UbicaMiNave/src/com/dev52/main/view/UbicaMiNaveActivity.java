package com.dev52.main.view;

import com.dev52.main.map.utils.MyCarLocationListener;

import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UbicaMiNaveActivity extends Activity {
	
	private static String tag = "UbicaMiNaveActivity";
	
	private Button aquiEstoyButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        
        MyCarLocationListener locationListener = new MyCarLocationListener(); 
        
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        
 
        aquiEstoyButton = (Button) findViewById(R.id.aquiEstoyButton);
        aquiEstoyButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(UbicaMiNaveActivity.this, MapViewActivity.class);
            	try{
                startActivity(intent);
            	}catch(Exception e){
            		e.printStackTrace();
            		Log.d(tag, e.getMessage());
            	}
            }
        });
    }
}