/**
 * Copyright 2009 Joe LaPenna
 */

package com.joelapenna.foursquare.types;

/**
 * Auto-generated: 2009-11-12 21:45:34.803921
 *
 * @author Joe LaPenna (joe@joelapenna.com)
 */
public class Stats implements FoursquareType {

    private Beenhere mBeenhere;
    private String mCheckins;
    private Mayor mMayor;

    public Stats() {
    }

    public Beenhere getBeenhere() {
        return mBeenhere;
    }

    public void setBeenhere(Beenhere beenhere) {
        mBeenhere = beenhere;
    }

    public String getCheckins() {
        return mCheckins;
    }

    public void setCheckins(String checkins) {
        mCheckins = checkins;
    }

    public Mayor getMayor() {
        return mMayor;
    }

    public void setMayor(Mayor mayor) {
        mMayor = mayor;
    }

}
