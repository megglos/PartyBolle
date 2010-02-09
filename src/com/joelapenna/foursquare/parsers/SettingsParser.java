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
import com.joelapenna.foursquare.types.Settings;

/**
 * Auto-generated: 2009-11-22 20:21:11.006171
 *
 * @author Joe LaPenna (joe@joelapenna.com)
 * @param <T>
 */
public class SettingsParser extends AbstractParser<Settings> {
    private static final Logger LOG = Logger.getLogger(SettingsParser.class.getCanonicalName());
    private static final boolean DEBUG = Foursquare.PARSER_DEBUG;

    @Override
    public Settings parseInner(XmlPullParser parser) throws XmlPullParserException, IOException,
            FoursquareError, FoursquareParseException {
        parser.require(XmlPullParser.START_TAG, null, null);

        Settings settings = new Settings();

        while (parser.nextTag() == XmlPullParser.START_TAG) {
            String name = parser.getName();
            if ("feeds_key".equals(name)) {
                settings.setFeedsKey(parser.nextText());

            } else if ("pings".equals(name)) {
                settings.setPings(parser.nextText());

            } else if ("sendtotwitter".equals(name)) {
                settings.setSendtotwitter(Boolean.valueOf(parser.nextText()));

            } else {
                // Consume something we don't understand.
                if (DEBUG) Log.d(PartyBolle.LOG_TAG, "Found tag that we don't recognize: " + name);
                skipSubTree(parser);
            }
        }
        return settings;
    }
}
