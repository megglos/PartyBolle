package org.schtief.partybolle.haltestellen;

import org.schtief.partybolle.LocationArea;
import org.schtief.partybolle.LocationCalculations;
import org.schtief.partybolle.PartyBolle;

import android.os.Handler;
import android.widget.Toast;

import com.google.android.maps.MapView;

public class HaltestellenManager {
	private Handler handler;
	private PartyBolle app;
	private MapView mapView;
	
	public HaltestellenManager(PartyBolle a, MapView m, Handler h){
		this.app		=	a;
		this.handler	=	h;
		this.mapView	=	m;
	}
	
	
	public void getHaltestellen() {
		LocationArea area = LocationCalculations.getMapViewAreaCoordinates(mapView);
		final GetStopsTask getStopsTask = new GetStopsTask(app, GetStopsTask.LONG_MAX_DATA_REFRESH_INTERVAL, area) {
			
			@Override
			protected void onParseData() {
				// TODO Auto-generated method stub
				
				
			}
			
			@Override
			protected void onLoadData() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			protected void onError() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			protected void onPostExecute(StopsParser result) {
				app.update(result);
			}
		};
		handler.post(new Runnable() {
			public void run()
			{
				Toast.makeText(app, "get stops", Toast.LENGTH_SHORT).show();
				getStopsTask.execute();
			}
		});

	}

}
