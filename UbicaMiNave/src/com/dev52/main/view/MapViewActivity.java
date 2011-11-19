package com.dev52.main.view;

import java.io.IOException;
import java.util.List;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dev52.main.map.utils.CarItemizedOverlay;
import com.dev52.main.map.utils.CustomOverlayItem;
import com.dev52.main.map.utils.LocationUtils;
import com.dev52.model.UserSession;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;


public class MapViewActivity extends MapActivity {
	

	private static final String THREAD_NAME = "MagentoBackground";
	
	private MapController mapController;
	private MapView mapView;
	private ProgressDialog m_doneProgressDialog = null;    
    private Geocoder geocoder;
    CustomOverlayItem overlayitem = null;
	
	private Thread requestThread;
	private Runnable retrievingAddressThread;
    private Runnable returnFromDoneAction;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_view);

		mapView = (MapView) findViewById(R.id.mapview);
		
		if(mapView == null){
			Toast.makeText(getApplicationContext(), "No se encontro el GMaps API", Toast.LENGTH_SHORT);
		}else{
			mapSetUp();
		}

	}
	
	private void mapSetUp(){
		mapView.setBuiltInZoomControls(true);
		mapView.setStreetView(true);
		mapController = mapView.getController();
		mapController.setZoom(LocationUtils.ZOOM_LEVEL); // Zoom 1 is world view
		
		Location currentLocation = UserSession.getUserSession().getLocation();
    	geocoder = new Geocoder(this);
    	
    	/*
    	// To test with other place 
    	
    	currentLocation = new Location((String)null);

    	currentLocation.setLatitude(19.414590062756346);
    	currentLocation.setLongitude(-99.08676624298096); //Somewhere in MC.
    	*/
    	
    	String address = retrieveAddress(currentLocation);
		
		currentLocation = insertThisLocation(currentLocation,address);
		
		int latitudeE6 = (int) (currentLocation.getLatitude() * 1e6 ); // Qro. points 20581367;
		int longitudeE6 =(int) (currentLocation.getLongitude() * 1e6 ); // Qro. points -100371094; 
		
		GeoPoint geoPoint = new GeoPoint(latitudeE6, longitudeE6);
		
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.camaro);
		CarItemizedOverlay itemizedoverlay = new CarItemizedOverlay(drawable,this);
		
		overlayitem = new CustomOverlayItem(geoPoint, getString(R.string.overlayItem_title),null);
		
		itemizedoverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedoverlay);
		
		mapController.animateTo(geoPoint);
	}

	private String retrieveAddress(final Location currentLocation) {
		final StringBuffer sb = new StringBuffer();
		
		returnFromDoneAction = new Runnable() {

            @Override
            public void run() {
            	overlayitem.setNewSnippet(
            			String.format(getString(R.string.overlayItem_message), sb.toString())
            			);
            	m_doneProgressDialog.dismiss();
            }
		};
		
		retrievingAddressThread = new Runnable(){
            @Override
            public void run() {
            	
        		List<Address> addresses;
				try {
					addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 10);
					
					for( int i = 0; i < addresses.size(); ++i){
						
						Address address = addresses.get(i);
						
						if(i == (addresses.size() - 1) ){ // The last line of addresses
							sb.append(address.getAddressLine(0) + ".");
						}else{
		        	        sb.append(address.getAddressLine(0) + ", ");
						}
						
					}
        	      
				} catch (IOException e) {
					Log.e(MapViewActivity.class.getSimpleName(), "Could not get Geocoder data", e);
				}
				runOnUiThread(returnFromDoneAction);
            }
        };
		
		requestThread = new Thread(null, retrievingAddressThread, THREAD_NAME);
		requestThread.start();
		m_doneProgressDialog = ProgressDialog.show(MapViewActivity.this,    
                getString(R.string.map_pleaseWait), getString(R.string.map_retrievingAddress), true);
		
		return sb.toString();
	}

	private Location insertThisLocation(Location currentLocation, String address) {
		
		// TODO Use the DAO's 
		// if the Location is null, retrieve the last one (maybe after the user can select his location)
		// if not insert the new location
		// TODO Save a Note to this location, include the address into the note
		
	
		return currentLocation;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}