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
import com.joelapenna.foursquare.types.Credentials;

/**
 * Auto-generated: 2009-11-13 21:59:24.709523
 *
 * @author Joe LaPenna (joe@joelapenna.com)
 * @param <T>
 */
public class CredentialsParser extends AbstractParser<Credentials> {
    private static final Logger LOG = Logger.getLogger(CredentialsParser.class.getCanonicalName());
    private static final boolean DEBUG = Foursquare.PARSER_DEBUG;

    @Override
    public Credentials parseInner(XmlPullParser parser) throws XmlPullParserException, IOException,
            FoursquareError, FoursquareParseException {
        parser.require(XmlPullParser.START_TAG, null, null);

        Credentials credentials = new Credentials();

        while (parser.nextTag() == XmlPullParser.START_TAG) {
            String name = parser.getName();
            if ("oauth_token".equals(name)) {
                credentials.setOauthToken(parser.nextText());

            } else if ("oauth_token_secret".equals(name)) {
                credentials.setOauthTokenSecret(parser.nextText());

            } else {
                // Consume something we don't understand.
                if (DEBUG) Log.d(PartyBolle.LOG_TAG, "Found tag that we don't recognize: " + name);
                skipSubTree(parser);
            }
        }
        return credentials;
    }
}
