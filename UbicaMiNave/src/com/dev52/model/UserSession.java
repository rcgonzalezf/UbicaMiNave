package com.dev52.model;

import java.io.Serializable;

import com.dev52.main.map.utils.LocationUtils;

import android.content.ContentResolver;
import android.location.Location;
import android.provider.Settings.Secure;

/**
 * Singleton Object to handle the UserSession values.
 * @author rgonzalez
 *
 */
public class UserSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 351430974016418741L;
	
	private UserSession(){
	}
	
	public static synchronized UserSession getUserSession(){
		
		if(session == null){
			 session = new UserSession();
		}
		
		return session;
	}
	
	public Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}
	

	/**
	 * A 64-bit number (as a hex string) that is randomly generated on the 
	 * device's first boot and should remain constant for the lifetime 
	 * of the device. (The value may change if a factory reset is performed on the device.)
	 */
	private String sessionId;
	

	private Location location;
	
	private static UserSession session;
	
	public String getSessionId(ContentResolver context){
		this.sessionId = Secure.getString(context, Secure.ANDROID_ID );
		return this.sessionId;
	}
	
	public void setLocation(Location newLocation) {
		// change the location only if its better, if not will keep the same
		if(LocationUtils.isBetterLocation(newLocation, getLocation())){
			this.location = newLocation;	
		}
		
	}

	public Location getLocation() {
		return location;
	}



}
