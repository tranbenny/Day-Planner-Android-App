package com.example.bennytran.yelpagendabuilder.Util;


import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

public class Constants {

    // firebase routes
    public static final String FIREBASE_BASE_URL = FirebaseURL.URL;
    public static final String USERS_URL = FIREBASE_BASE_URL + "/users";
    public static final String GROUPS_URL = FIREBASE_BASE_URL + "/groups";

    public static String getUserPlansURL() {
        return USERS_URL + "/" + yelpAgendaBuilder.getInstance().user + "/plans";
    }


}
