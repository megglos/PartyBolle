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
import com.joelapenna.foursquare.types.Special;

/**
 * Auto-generated: 2010-01-09 17:54:58.032420
 *
 * @author Joe LaPenna (joe@joelapenna.com)
 * @param <T>
 */
public class SpecialParser extends AbstractParser<Special> {
    private static final Logger LOG = Logger.getLogger(SpecialParser.class.getCanonicalName());
    private static final boolean DEBUG = Foursquare.PARSER_DEBUG;

    @Override
    public Special parseInner(XmlPullParser parser) throws XmlPullParserException, IOException,
            FoursquareError, FoursquareParseException {
        parser.require(XmlPullParser.START_TAG, null, null);

        Special special = new Special();

        while (parser.nextTag() == XmlPullParser.START_TAG) {
            String name = parser.getName();
            if ("id".equals(name)) {
                special.setId(parser.nextText());

            } else if ("message".equals(name)) {
                special.setMessage(parser.nextText());

            } else if ("type".equals(name)) {
                special.setType(parser.nextText());

            } else if ("venue".equals(name)) {
                special.setVenue(new VenueParser().parse(parser));

            } else {
                // Consume something we don't understand.
                if (DEBUG) Log.d(PartyBolle.LOG_TAG, "Found tag that we don't recognize: " + name);
                skipSubTree(parser);
            }
        }
        return special;
    }
}
