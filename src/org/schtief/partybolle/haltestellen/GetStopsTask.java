package org.schtief.partybolle.haltestellen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import org.schtief.partybolle.LocationArea;
import org.schtief.partybolle.PartyBolle;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * @author Andreas Schildbach
 */
public abstract class GetStopsTask extends AsyncTask<String, Void, StopsParser>
{
	public static final long LONG_MAX_DATA_REFRESH_INTERVAL = 240000000000L;
	private File cacheFile, tempCacheFile;
	private long masterDataRefreshInterval;
	private static final String STOPS_CACHE_FILENAME = "stops.cache";
	private static final String STOPS_URL = "http://oeffi.schildbach.de/stops.dat.gz?partybolle";
	private LocationArea area;
	final StopsParser parser = new StopsParser();

	public GetStopsTask(Context context, long masterDataRefreshInterval, LocationArea area)
	{	
		this.cacheFile = new File(context.getCacheDir(), STOPS_CACHE_FILENAME);
		this.tempCacheFile = new File(context.getCacheDir(), "." + STOPS_CACHE_FILENAME);
		this.masterDataRefreshInterval = masterDataRefreshInterval;
		this.area = area;
	}

	@Override
	protected StopsParser doInBackground(String... params)
	{
		Log.i(PartyBolle.LOG_TAG, "getting stops");
		
		try
		{
			final boolean cacheExpired = System.currentTimeMillis() - cacheFile.lastModified() > masterDataRefreshInterval;
			final boolean cacheExists = cacheFile.exists();
			if (!cacheExists || cacheExpired)
			{
				onLoadData();

				final URLConnection connection = new URL(STOPS_URL + "?" + (!cacheExists ? "first" : "expired")).openConnection();
				final InputStream is = new GZIPInputStream(connection.getInputStream());
				final FileOutputStream os = new FileOutputStream(tempCacheFile);
				final byte[] buf = new byte[4096];
				while (true)
				{
					final int read = is.read(buf);
					if (read == -1)
						break;

					os.write(buf, 0, read);
				}
				os.close();
				is.close();

				// rename
				cacheFile.delete();
				tempCacheFile.renameTo(cacheFile);
			}
		}
		catch (IOException x)
		{
			x.printStackTrace();
		}
		finally
		{
			tempCacheFile.delete();
		}

		Log.i(PartyBolle.LOG_TAG, "start parsing stops");
		if (cacheFile.exists())
		{
			try
			{
				onParseData();
				parser.parse(new FileInputStream(cacheFile), area);
				Log.i(PartyBolle.LOG_TAG, "finished parsing stops");
			}
			catch (IOException x)
			{
				cacheFile.delete();
				throw new RuntimeException(x);
			}
		}
		else
		{
			onError();
		}

		return parser;
	}

	@Override
	protected void onPostExecute(StopsParser result) {
		Log.i(PartyBolle.LOG_TAG, "got stops");	
	}
	
	protected abstract void onLoadData();

	protected abstract void onParseData();

	protected abstract void onError();

	public boolean removeCacheFile()
	{
		tempCacheFile.delete();
		final boolean success = cacheFile.delete();

		return success;
	}
}
