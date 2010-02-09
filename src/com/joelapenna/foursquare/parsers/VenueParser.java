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
import com.joelapenna.foursquare.types.Venue;

/**
 * Auto-generated: 2009-11-17 09:33:44.568010
 *
 * @author Joe LaPenna (joe@joelapenna.com)
 * @param <T>
 */
public class VenueParser extends AbstractParser<Venue> {
    private static final Logger LOG = Logger.getLogger(VenueParser.class.getCanonicalName());
    private static final boolean DEBUG = Foursquare.PARSER_DEBUG;

    @Override
    public Venue parseInner(XmlPullParser parser) throws XmlPullParserException, IOException,
            FoursquareError, FoursquareParseException {
        parser.require(XmlPullParser.START_TAG, null, null);

        Venue venue = new Venue();

        while (parser.nextTag() == XmlPullParser.START_TAG) {
            String name = parser.getName();
            if ("address".equals(name)) {
                venue.setAddress(parser.nextText());

            } else if ("checkins".equals(name)) {
                venue.setCheckins(new GroupParser(new CheckinParser()).parse(parser));

            } else if ("city".equals(name)) {
                venue.setCity(parser.nextText());

            } else if ("cityid".equals(name)) {
                venue.setCityid(parser.nextText());

            } else if ("crossstreet".equals(name)) {
                venue.setCrossstreet(parser.nextText());

            } else if ("distance".equals(name)) {
                venue.setDistance(parser.nextText());

            } else if ("geolat".equals(name)) {
                venue.setGeolat(parser.nextText());

            } else if ("geolong".equals(name)) {
                venue.setGeolong(parser.nextText());

            } else if ("id".equals(name)) {
                venue.setId(parser.nextText());

            } else if ("name".equals(name)) {
                venue.setName(parser.nextText());

            } else if ("phone".equals(name)) {
                venue.setPhone(parser.nextText());

            } else if ("state".equals(name)) {
                venue.setState(parser.nextText());

            } else if ("stats".equals(name)) {
                venue.setStats(new StatsParser().parse(parser));

            } else if ("tags".equals(name)) {
                venue.setTags(new TagsParser().parse(parser));

            } else if ("tips".equals(name)) {
                venue.setTips(new GroupParser(new TipParser()).parse(parser));

            } else if ("todos".equals(name)) {
                venue.setTodos(new GroupParser(new TipParser()).parse(parser));

            } else if ("twitter".equals(name)) {
                venue.setTwitter(parser.nextText());

            } else if ("zip".equals(name)) {
                venue.setZip(parser.nextText());

            } else {
                // Consume something we don't understand.
                if (DEBUG) Log.d(PartyBolle.LOG_TAG, "Found tag that we don't recognize: " + name);
                skipSubTree(parser);
            }
        }
        return venue;
    }
}
