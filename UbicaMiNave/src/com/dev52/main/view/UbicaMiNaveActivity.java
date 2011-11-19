package com.dev52.main.view;

import com.dev52.main.map.utils.CarLocationListener;
import com.dev52.model.UserSession;

import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Main activity
 * @author rgonzalez
 *
 */
public class UbicaMiNaveActivity extends Activity {
	
	private static String tag = "UbicaMiNaveActivity";
	
	private Button aquiEstoyButton;
	
	private LocationManager locationManager;
	
	private CarLocationListener locationListener;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        
        locationListener = new CarLocationListener(); 
        
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        
 
        aquiEstoyButton = (Button) findViewById(R.id.aquiEstoyButton);
        
        aquiEstoyButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(UbicaMiNaveActivity.this, MapViewActivity.class);
            	try{
            		UserSession userSession = UserSession.getUserSession();
            		
            		if(userSession.getLocation() == null){
            			Toast.makeText(getBaseContext(), getString(R.string.overlayItem_message), Toast.LENGTH_LONG);
            		}else{
            			startActivity(intent);	
            		}
            		
            	}catch(Exception e){
            		e.printStackTrace();
            		Log.d(tag, e.getMessage());
            	}
            }
        });
    }
    
}