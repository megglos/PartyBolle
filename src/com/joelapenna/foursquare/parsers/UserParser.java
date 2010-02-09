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
import com.joelapenna.foursquare.types.User;

/**
 * Auto-generated: 2010-01-14 11:02:45.160349
 *
 * @author Joe LaPenna (joe@joelapenna.com)
 * @param <T>
 */
public class UserParser extends AbstractParser<User> {
    private static final Logger LOG = Logger.getLogger(UserParser.class.getCanonicalName());
    private static final boolean DEBUG = Foursquare.PARSER_DEBUG;

    @Override
    public User parseInner(XmlPullParser parser) throws XmlPullParserException, IOException,
            FoursquareError, FoursquareParseException {
        parser.require(XmlPullParser.START_TAG, null, null);

        User user = new User();

        while (parser.nextTag() == XmlPullParser.START_TAG) {
            String name = parser.getName();
            if ("badges".equals(name)) {
                user.setBadges(new GroupParser(new BadgeParser()).parse(parser));

            } else if ("checkin".equals(name)) {
                user.setCheckin(new CheckinParser().parse(parser));

            } else if ("created".equals(name)) {
                user.setCreated(parser.nextText());

            } else if ("email".equals(name)) {
                user.setEmail(parser.nextText());

            } else if ("firstname".equals(name)) {
                user.setFirstname(parser.nextText());

            } else if ("friendstatus".equals(name)) {
                user.setFriendstatus(parser.nextText());

            } else if ("gender".equals(name)) {
                user.setGender(parser.nextText());

            } else if ("id".equals(name)) {
                user.setId(parser.nextText());

            } else if ("lastname".equals(name)) {
                user.setLastname(parser.nextText());

            } else if ("phone".equals(name)) {
                user.setPhone(parser.nextText());

            } else if ("photo".equals(name)) {
                user.setPhoto(parser.nextText());

            } else if ("settings".equals(name)) {
                user.setSettings(new SettingsParser().parse(parser));

            } else if ("twitter".equals(name)) {
                user.setTwitter(parser.nextText());

            } else {
                // Consume something we don't understand.
                if (DEBUG) Log.d(PartyBolle.LOG_TAG, "Found tag that we don't recognize: " + name);
                skipSubTree(parser);
            }
        }
        return user;
    }
}
