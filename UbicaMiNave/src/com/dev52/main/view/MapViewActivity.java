package com.dev52.main.view;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dev52.main.map.utils.CarItemizedOverlay;
import com.dev52.main.map.utils.LocationUtils;
import com.dev52.main.map.utils.MyCarLocationListener;
import com.dev52.model.UserSession;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapViewActivity extends MapActivity {
	private MapController mapController;
	private MapView mapView;
	private LocationManager locationManager;
	private RelativeLayout linearLayout;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_view);

		linearLayout = (RelativeLayout) findViewById(R.id.mapViewlayout);

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
		
		int latitudeE6 = (int) (currentLocation.getLatitude() * 1e6 ); // Qro. points 20581367;
		int longitudeE6 =(int) (currentLocation.getLongitude() * 1e6 ); // Qro. points -100371094; 
		
		GeoPoint geoPoint = new GeoPoint(latitudeE6, longitudeE6);
		
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.camaro);
		CarItemizedOverlay itemizedoverlay = new CarItemizedOverlay(drawable,this);
		
		OverlayItem overlayitem = new OverlayItem(geoPoint, getString(R.string.overlayItem_title), getString(R.string.overlayItem_message));
		
		itemizedoverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedoverlay);
		
		mapController.animateTo(geoPoint);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}