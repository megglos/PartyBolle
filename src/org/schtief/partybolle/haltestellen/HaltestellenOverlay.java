package org.schtief.partybolle.haltestellen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.schtief.partybolle.PartyBolle;
import org.schtief.partybolle.R;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.maps.ItemizedOverlay;

public class HaltestellenOverlay extends ItemizedOverlay<HaltestellenOverlayItem>{
	public static Drawable drawableStop;
	
	private Set<Integer> ids	=	new HashSet<Integer>();
	private ArrayList<HaltestellenOverlayItem> mOverlays = new ArrayList<HaltestellenOverlayItem>();
	private PartyBolle	app;

	public HaltestellenOverlay(PartyBolle app)
	{	
		super( boundCenterBottom(app.getResources().getDrawable(R.drawable.haltestelle_32)) );
		this.app	=	app;
		populate();
	}
	
	public void addStop(Stop stop) {
		if(ids.contains(stop.getStationId())) {
			return;
		}
		mOverlays.add(new HaltestellenOverlayItem(app, stop));
		setLastFocusedIndex(-1);
		populate();
	}

	@Override
	protected HaltestellenOverlayItem createItem(int id) {
		if(id>=mOverlays.size())
			return null;
	  return mOverlays.get(id);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
	
	public void cleanup(){
		mOverlays.clear();
		app.getMapView().removeAllViews();
		setLastFocusedIndex(-1);
		populate();	
	}
	
	@Override
	protected boolean onTap(int index)
	{
		if(index>=mOverlays.size())
			return false;
		
		HaltestellenOverlayItem item	=	mOverlays.get(index);
		if(null==item)
			return false;
		
		Log.i("HaltestellenOverlay", "tapped on "+item.getTitle());
		
		setFocus(item);
		return true;
	}


	@Override
	public void setFocus(HaltestellenOverlayItem item) {
		super.setFocus(item);
		Log.i("EventOverlay", "setFocus on "+item.getTitle());
		app.showHaltestelle(item);
	}


	public void prev() {
		if(mOverlays.size()==0)
			return;

		HaltestellenOverlayItem prev	=	nextFocus(false);
		if(null==prev){
			//try last
			prev=mOverlays.get(mOverlays.size()-1);
			if(null==prev)
				return;
		}
		Log.i("HaltestellenOverlay", "prev "+prev.getTitle());
		setFocus(prev);		
	}


	public void next() {
		if(mOverlays.size()==0)
			return;

		HaltestellenOverlayItem next	=	nextFocus(true);
		if(null==next){
			//try first
			next=mOverlays.get(0);
			if(null==next)
				return;
		}
		Log.i("HaltestellenOverlay", "next "+next.getTitle());
		setFocus(next);		
	}
	
}
