package org.schtief.partybolle.haltestellen;

import org.schtief.partybolle.InfoShape;
import org.schtief.partybolle.PartyBolle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class HaltestellenInfoShape extends InfoShape{
	String name;
	String id;

	public HaltestellenInfoShape(Stop stop) {
		origIconHeight=(int)(32*PartyBolle.DISPLAY_SCALE);
		this.name = stop.getName();
		this.id = String.valueOf( stop.getStationId() );

		//check width
		Paint p = new Paint();
		{
			int infoWidth=0;
			p.setTextSize(12*PartyBolle.DISPLAY_SCALE);
			p.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
			float[] widths	=	new float[name.length()];
			p.getTextWidths(name, widths);
			for (int i = 0; i < widths.length; i++) {
				infoWidth+=widths[i];
			}

			int locationWidth=0;
			p.setTextSize(14*PartyBolle.DISPLAY_SCALE);
			p.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
			widths	=	new float[id.length()];
			p.getTextWidths(id, widths);
			for (int i = 0; i < widths.length; i++) {
				locationWidth+=widths[i];
			}
			width=Math.max(infoWidth, locationWidth);
		}
		width+=10*PartyBolle.DISPLAY_SCALE;
		height	=	(int)(52*PartyBolle.DISPLAY_SCALE);
	}

	@Override
	public void draw(Canvas c, Paint paint)
	{
		super.draw(c, paint);
		paint.setColor(Color.BLACK);
		paint.setTextSize(14*PartyBolle.DISPLAY_SCALE);
		paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
		//TODO wenn nich heute dann datum
		c.drawText(id, -(width/2)+5, -origIconHeight-(37*PartyBolle.DISPLAY_SCALE), paint);
		paint.setTextSize(12*PartyBolle.DISPLAY_SCALE);
		paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
		c.drawText(name, -(width/2)+5, -origIconHeight-(20*PartyBolle.DISPLAY_SCALE), paint);
	}
	
}