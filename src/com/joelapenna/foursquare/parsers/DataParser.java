/**
 * Copyright 2009 Joe LaPenna
 */

package com.joelapenna.foursquare.parsers;

import java.io.IOException;
import java.util.logging.Logger;

import org.schtief.partybolle.PartyBolle;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

import com.joelapenna.foursquare.Foursquare;
import com.joelapenna.foursquare.error.FoursquareError;
import com.joelapenna.foursquare.error.FoursquareParseException;
import com.joelapenna.foursquare.types.Data;

/**
 * Auto-generated: 2009-11-13 21:59:25.219255
 * 
 * @author Joe LaPenna (joe@joelapenna.com)
 * @param <T>
 */
public class DataParser extends AbstractParser<Data> {
    private static final Logger LOG = Logger.getLogger(DataParser.class.getCanonicalName());
    private static final boolean DEBUG = Foursquare.PARSER_DEBUG;

    @Override
    public Data parseInner(XmlPullParser parser) throws XmlPullParserException, IOException,
            FoursquareError, FoursquareParseException {
        parser.require(XmlPullParser.START_TAG, null, null);

        Data data = new Data();

        while (parser.nextTag() == XmlPullParser.START_TAG) {
            String name = parser.getName();
            if ("cityid".equals(name)) {
                data.setCityid(parser.nextText());

            } else if ("message".equals(name)) {
                data.setMessage(parser.nextText());

            } else if ("status".equals(name)) {
                // Ugh, the one spot in the api where they return 1 instead of true.
                data.setStatus("1".equals(parser.nextText()));
            } else {
                // Consume something we don't understand.
                if (DEBUG) Log.d(PartyBolle.LOG_TAG, "Found tag that we don't recognize: " + name);
                skipSubTree(parser);
            }
        }
        return data;
    }
}
