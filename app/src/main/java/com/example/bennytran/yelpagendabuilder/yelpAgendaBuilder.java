package com.example.bennytran.yelpagendabuilder;

import android.app.Application;
import android.util.Log;


public class yelpAgendaBuilder extends Application {

    public static final String LOG_TAG = "YELPAGENDABUILDER";

    public static yelpAgendaBuilder instance;

    public yelpAgendaBuilder() {
        if (instance == null) {
            instance  = this;
        } else {
            Log.e(LOG_TAG, "only one instance allowed");
        }
    }

    public static yelpAgendaBuilder getInstance() {
        return instance;
    }


}
