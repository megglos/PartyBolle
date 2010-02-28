package org.schtief.partybolle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class LocationCalculations {
	public static final int LONGITUDE_BERLIN_WEST = 13000000;
	public static final int LONGITUDE_BERLIN_EAST = 14000000;
	public static final int LATITUDE_BERLIN_NORTH = 52000000;
	public static final int LATITUDE_BERLIN_SOUTH = 53000000;
	

	public static LocationArea getMapViewAreaCoordinates(MapView mapView) {
		GeoPoint center = mapView.getMapCenter();
		int latNorth = center.getLatitudeE6() + mapView.getLatitudeSpan() / 2;
		int latSouth = center.getLatitudeE6() - mapView.getLatitudeSpan() / 2;

		int lonWest = center.getLongitudeE6() - mapView.getLongitudeSpan() / 2;
		int lonEast = center.getLongitudeE6() + mapView.getLongitudeSpan() / 2;
		return new LocationArea(lonWest, lonEast, latNorth, latSouth);
	}
}
