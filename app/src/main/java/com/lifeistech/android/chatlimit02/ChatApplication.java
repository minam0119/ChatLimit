package com.lifeistech.android.chatlimit02;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.xml.sax.helpers.ParserAdapter;

/**
 * Created by MINAMI on 2015/12/27.
 */
public class ChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "4SgziLTz6cD91IJsknbYMhWlR3ZV3eg50AT8FfBQ", "3YsC0tgvbUFZn9yngv4mX7sNrOYIL7Asey6VhBJW");
        ParseUser.registerSubclass(User.class);
        ParseObject.registerSubclass(Message.class);
    }
}
