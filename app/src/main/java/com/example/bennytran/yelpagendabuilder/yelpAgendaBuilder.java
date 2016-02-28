package com.example.bennytran.yelpagendabuilder;

// TODO:
// change to recommened singleton class design pattern

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;


public class yelpAgendaBuilder extends Application {

    public static final String LOG_TAG = "YELP_AGENDA_BUILDER";

    public static yelpAgendaBuilder instance;
    public ArrayList<String> restaurants;


    public yelpAgendaBuilder() {
        if (instance == null) {
            instance  = this;
            restaurants = new ArrayList<String>();
        } else {
            Log.e(LOG_TAG, "only one instance allowed");
        }
    }


    public static yelpAgendaBuilder getInstance() {
        return instance;
    }


}
