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
import com.joelapenna.foursquare.types.Mayor;

/**
 * Auto-generated: 2009-11-13 21:59:24.322835
 *
 * @author Joe LaPenna (joe@joelapenna.com)
 * @param <T>
 */
public class MayorParser extends AbstractParser<Mayor> {
    private static final Logger LOG = Logger.getLogger(MayorParser.class.getCanonicalName());
    private static final boolean DEBUG = Foursquare.PARSER_DEBUG;

    @Override
    public Mayor parseInner(XmlPullParser parser) throws XmlPullParserException, IOException,
            FoursquareError, FoursquareParseException {
        parser.require(XmlPullParser.START_TAG, null, null);

        Mayor mayor = new Mayor();

        while (parser.nextTag() == XmlPullParser.START_TAG) {
            String name = parser.getName();
            if ("checkins".equals(name)) {
                mayor.setCheckins(parser.nextText());

            } else if ("count".equals(name)) {
                mayor.setCount(parser.nextText());

            } else if ("message".equals(name)) {
                mayor.setMessage(parser.nextText());

            } else if ("type".equals(name)) {
                mayor.setType(parser.nextText());

            } else if ("user".equals(name)) {
                mayor.setUser(new UserParser().parse(parser));

            } else {
                // Consume something we don't understand.
                if (DEBUG) Log.d(PartyBolle.LOG_TAG, "Found tag that we don't recognize: " + name);
                skipSubTree(parser);
            }
        }
        return mayor;
    }
}
