package com.dev52.main.map.utils;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class CustomOverlayItem extends OverlayItem {

	public CustomOverlayItem(GeoPoint point, String title, String snippet) {
		super(point, title, snippet);
	}
	
	private String newSnippet;
	
	@Override
	public String getSnippet(){
		return getNewSnippet();
	}

	public void setNewSnippet(String newSnippet) {
		this.newSnippet = newSnippet;
	}

	public String getNewSnippet() {
		return newSnippet;
	}
	
}
