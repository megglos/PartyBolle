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
import com.joelapenna.foursquare.types.Rank;

/**
 * Auto-generated: 2009-11-13 21:59:23.532129
 *
 * @author Joe LaPenna (joe@joelapenna.com)
 * @param <T>
 */
public class RankParser extends AbstractParser<Rank> {
    private static final Logger LOG = Logger.getLogger(RankParser.class.getCanonicalName());
    private static final boolean DEBUG = Foursquare.PARSER_DEBUG;

    @Override
    public Rank parseInner(XmlPullParser parser) throws XmlPullParserException, IOException,
            FoursquareError, FoursquareParseException {
        parser.require(XmlPullParser.START_TAG, null, null);

        Rank rank = new Rank();

        while (parser.nextTag() == XmlPullParser.START_TAG) {
            String name = parser.getName();
            if ("city".equals(name)) {
                rank.setCity(parser.nextText());

            } else if ("message".equals(name)) {
                rank.setMessage(parser.nextText());

            } else if ("position".equals(name)) {
                rank.setPosition(parser.nextText());

            } else {
                // Consume something we don't understand.
                if (DEBUG) Log.d(PartyBolle.LOG_TAG, "Found tag that we don't recognize: " + name);
                skipSubTree(parser);
            }
        }
        return rank;
    }
}
