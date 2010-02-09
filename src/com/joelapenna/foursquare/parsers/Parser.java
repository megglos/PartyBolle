/**
 * Copyright 2009 Joe LaPenna
 */

package com.joelapenna.foursquare.parsers;

import com.joelapenna.foursquare.error.FoursquareError;
import com.joelapenna.foursquare.error.FoursquareParseException;
import com.joelapenna.foursquare.types.FoursquareType;

import org.xmlpull.v1.XmlPullParser;

/**
 * @author Joe LaPenna (joe@joelapenna.com)
 * @param <T>
 */
public interface Parser<T extends FoursquareType> {

    public abstract T parse(XmlPullParser parser) throws FoursquareError, FoursquareParseException;

}
