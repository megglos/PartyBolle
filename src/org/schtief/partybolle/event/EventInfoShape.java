package org.schtief.partybolle.event;


import org.schtief.partybolle.InfoShape;
import org.schtief.partybolle.PartyBolle;
import org.schtief.util.json.JSONObject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
Copyright by Stefan Lischke a.k.a Mister Schtief 
started in 2010 in Berlin Germany

This file is part of PartyBolle.

PartyBolle is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PartyBolle is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with PartyBolle.  If not, see <http://www.gnu.org/licenses/>.
*/
public class EventInfoShape extends InfoShape{

	String location,info;

	public EventInfoShape(JSONObject loc){
		origIconHeight=(int)(42*PartyBolle.DISPLAY_SCALE);
		this.info=loc.optJSONArray("e").optJSONObject(0).optString("tit");
		this.location=loc.optString("n");

		//check width
		{
			int infoWidth=0;
			Paint p = new Paint();
			p.setTextSize(12*PartyBolle.DISPLAY_SCALE);
			p.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
			float[] widths	=	new float[info.length()];
			p.getTextWidths(info, widths);
			for (int i = 0; i < widths.length; i++) {
				infoWidth+=widths[i];
			}
			
			int locationWidth=0;
			p.setTextSize(14*PartyBolle.DISPLAY_SCALE);
			p.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
			widths	=	new float[location.length()];
			p.getTextWidths(location, widths);
			for (int i = 0; i < widths.length; i++) {
				locationWidth+=widths[i];
			}
			width=Math.max(infoWidth, locationWidth);
		}
		width+=10;
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
		c.drawText(location, -(width/2)+5, -origIconHeight-(37*PartyBolle.DISPLAY_SCALE), paint);
		paint.setTextSize(12*PartyBolle.DISPLAY_SCALE);
		paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
		c.drawText(info, -(width/2)+5, -origIconHeight-(20*PartyBolle.DISPLAY_SCALE), paint);
	}
	
}
