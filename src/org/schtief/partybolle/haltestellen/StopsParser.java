package org.schtief.partybolle.haltestellen;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.schtief.partybolle.LocationArea;

/**
 * @author Andreas Schildbach
 */
public class StopsParser
{
	private static final String[] EMPTY_LINES = new String[0];

	private List<Stop> stops;

	public List<Stop> getStops()
	{
		return stops;
	}

	public void parse(InputStream is, LocationArea area) throws IOException
	{
		final DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
		/* final int version = */dis.readInt();

		final int size = dis.readInt();
		stops = new ArrayList<Stop>();

		for (int i = 0; i < size; i++)
		{
			final Double lat = dis.readDouble() * 1E6;
			final Double lon = dis.readDouble() * 1E6;
			final String name = dis.readUTF();
			final int mastId = dis.readInt();
			final int stationId = dis.readInt();
			final String linesStr = dis.readUTF();
			//final String[] lines = linesStr.length() > 0 ? linesStr.split(",") : EMPTY_LINES;
			
			final int latInt = lat.intValue();
			final int lonInt = lon.intValue();
			
			if(latInt < area.getLatNorth() && latInt > area.getLatSouth() && lonInt < area.getLongEast() && lonInt > area.getLongWest()) {
				stops.add( new Stop(lat.intValue(), lon.intValue(), name, stationId, mastId) );	
			}
			
		}
		dis.close();
	}
}
