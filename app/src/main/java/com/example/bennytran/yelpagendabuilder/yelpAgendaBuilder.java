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
    public ArrayList<String> timeStart;
    public ArrayList<String> timeEnd;
    // public ArrayList<String> nightlife;

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



    // constructor, allows only one instance to be created
    public yelpAgendaBuilder() {
        if (instance == null) {
            instance  = this;
            this.restaurants = new ArrayList<String>();
            this.timeStart = new ArrayList<String>();
            this.timeEnd = new ArrayList<String>();
        } else {
            Log.e(LOG_TAG, "only one instance allowed");
        }
    }

    // gets random image to display as background for list items
    public Integer getRandomImage() {
        Random random = new Random();
        int randomIndex = random.nextInt(backgroundImageID.length);
        // Log.i(LOG_TAG, "" + randomIndex);
        return backgroundImageID[randomIndex];
    }

    // print results added from api call
    public void logProperties() {
        Log.i(LOG_TAG, restaurants.toString());
    }

    public ArrayList<String> getStart() {
        int size = this.restaurants.size();
        for (int i = 0; i < size; i++) {
            String formattedStart = i + ":00 pm";
            String formattedEnd = (i + 1) + ":00";
            this.timeStart.add(formattedStart);
            this.timeEnd.add(formattedEnd);
        }
        return this.timeStart;
    }

    public ArrayList<String> getEnd() {
        return this.timeEnd;
    }


    public static yelpAgendaBuilder getInstance() {
        return instance;
    }




}
