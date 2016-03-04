package com.example.bennytran.yelpagendabuilder;

// TODO:
// change to recommened singleton class design pattern

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;


public class yelpAgendaBuilder extends Application {

    public static final String LOG_TAG = "YELP_AGENDA_BUILDER";

    public static yelpAgendaBuilder instance;
    public ArrayList<String> restaurants;

    public static final Integer[] backgroundImageID = new Integer[] {
            R.drawable.bar_1,
            R.drawable.bar_2,
            R.drawable.bar_3,
            R.drawable.breakfast_1,
            R.drawable.breakfast_2,
            R.drawable.coffee_1,
            R.drawable.food_1,
            R.drawable.food_2,
            R.drawable.food_3,
            R.drawable.hiking_1,
            R.drawable.ice_cream_1,
            R.drawable.night_life_1,
            R.drawable.night_life_2,
            R.drawable.park_1,
            R.drawable.shopping_1
    };


    public yelpAgendaBuilder() {
        if (instance == null) {
            instance  = this;
            restaurants = new ArrayList<String>();
            // load images
        } else {
            Log.e(LOG_TAG, "only one instance allowed");
        }
    }

    public Integer getRandomImage() {
        Random random = new Random();
        int randomIndex = random.nextInt(backgroundImageID.length);
        Log.i(LOG_TAG, "" + randomIndex);
        return backgroundImageID[randomIndex];
    }


    public static yelpAgendaBuilder getInstance() {
        return instance;
    }




}
