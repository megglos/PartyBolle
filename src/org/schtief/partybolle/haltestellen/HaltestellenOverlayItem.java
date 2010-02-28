package org.schtief.partybolle.haltestellen;

import org.schtief.partybolle.InfoOverlay;
import org.schtief.partybolle.InfoOverlayItem;
import org.schtief.partybolle.PartyBolle;
import org.schtief.partybolle.PartyBolleOverlayItem;

import com.google.android.maps.GeoPoint;

public class HaltestellenOverlayItem extends PartyBolleOverlayItem {

	Stop stop;
	public Stop getStop() {
		return stop;
	}

	private InfoOverlayItem info	=	null;
	private PartyBolle app;
	
	public HaltestellenOverlayItem(PartyBolle app, Stop stop)
	{
		super(new GeoPoint(stop.getLat(), stop.getLon()), stop.getName(), String.valueOf(stop.getMastId()) );
		this.stop =	stop;
		this.app=app;
	}

	@Override
	public String getId() {
		return String.valueOf( stop.getStationId() );
	}

	@Override
	public String getType()
	{
		return "stop";
	}

	@Override
	public InfoOverlayItem getInfo(InfoOverlay overlay) {
		if(null==info) {
			info=new InfoOverlayItem(
					overlay, 
					getPoint(),
					new HaltestellenInfoShape(stop),
					new HaltestellenDetailDialog(app, this)
			);
		}
		return info;
	}

}
