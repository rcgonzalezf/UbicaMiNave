package com.dev52.main.map.utils;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import com.dev52.model.UserSession;
/**
 * Listener of the Car Location
 * @author rgonzalez
 *
 */
public class CarLocationListener implements LocationListener {

	@Override
	public void onLocationChanged(Location location) {
		UserSession.getUserSession().setLocation(location);
	}

	@Override
	public void onProviderDisabled(String provider) {}

	@Override
	public void onProviderEnabled(String provider) {}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {}

}
