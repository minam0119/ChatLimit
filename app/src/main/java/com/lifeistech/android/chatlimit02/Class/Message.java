package com.lifeistech.android.chatlimit02.Class;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

/**
 * Created by MINAMI on 2015/12/27.
 */
@ParseClassName("Message")
public class Message extends ParseObject {

    public String getUserId() { return getString("userId"); }

    public void setUserId(String userId){
        put("userId",userId);
    }

    public String getMessage() {
        return getString("message");
    }

    public void setMessage(String message) {
        put("message", message);
    }

    public ParseGeoPoint getLocation() {
        return getParseGeoPoint("location");
    }

    public void setLocation(ParseGeoPoint geoPoint) {
        put("location", geoPoint);
    }

}
