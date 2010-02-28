package org.schtief.partybolle.haltestellen;

import org.schtief.partybolle.PartyBolle;
import org.schtief.partybolle.R;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class HaltestellenDetailDialog extends Dialog {
	// private static DateFormat df = new
	// SimpleDateFormat("EEE d.MM.yyyy HH:mm");

	private Stop stop;
	private PartyBolle app;
	private HaltestellenOverlayItem haltestellenOverlayItem;

	public HaltestellenDetailDialog(PartyBolle app, HaltestellenOverlayItem haltestellenOverlayItem) {
		super(app);
		this.app = app;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.stop = haltestellenOverlayItem.stop;
		this.haltestellenOverlayItem = haltestellenOverlayItem;
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.haltestellendialog);
		
		((TextView) findViewById(R.id.HaltestellenName)).setText(stop.getName());
		((TextView) findViewById(R.id.HalstestellenId)).setText(stop.getStationId());

		findViewById(R.id.HalstestellenWebAction).setOnClickListener(
				new View.OnClickListener() {

					public void onClick(View arg0) {
						//TODO: 2010-02-28 sb invoke Oeffi intent
					}
				});
	}
}