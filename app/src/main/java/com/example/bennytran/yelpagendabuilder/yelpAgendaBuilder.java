package com.example.bennytran.yelpagendabuilder;


import android.app.Application;
import android.util.Log;

import com.example.bennytran.yelpagendabuilder.models.BusinessResult;
import com.example.bennytran.yelpagendabuilder.models.Plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;


public class yelpAgendaBuilder extends Application {

    public static final String LOG_TAG = "YELP_AGENDA_BUILDER";

    public static yelpAgendaBuilder instance;


    // public HashMap<String, BusinessResult> restaurants = new HashMap<>();
    public HashMap<String, BusinessResult> breakfast = new HashMap<>();
    public HashMap<String, BusinessResult> lunch = new HashMap<>();
    public HashMap<String, BusinessResult> dinner = new HashMap<>();


    public HashMap<String, BusinessResult> activeActivities = new HashMap<>();
    public HashMap<String, BusinessResult> nightLife = new HashMap<>();
    public HashMap<String, BusinessResult> shopping = new HashMap<>();
    public HashMap<String, BusinessResult> coffeeDessert = new HashMap<>();

    public HashMap<String, Plan> userPlans = new HashMap<>();



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
        Log.i(LOG_TAG, breakfast.toString());
    }


    public void addCategoryMap(String term, String name, BusinessResult business) {
        switch(term) {
            case "breakfast and brunch":
                this.breakfast.put(name, business);
                break;
            case "lunch":
                this.lunch.put(name, business);
                break;
            case "dinner":
                this.dinner.put(name, business);
                break;
            case "active things":
                this.activeActivities.put(name, business);
                break;
            case "night life":
                this.nightLife.put(name, business);
                break;
            case "shopping":
                this.shopping.put(name, business);
                break;
            case "coffee and desserts":
                this.coffeeDessert.put(name, business);
                break;
        }
    }


    // takes in a category term and returns the highest rated result
    public BusinessResult getMaxResult(String term) {
        HashMap<String, BusinessResult> results = null;
        BusinessResult max = null;
        double maxRating = 0;
        switch(term) {
            case "breakfast and brunch":
                results = this.breakfast;
                break;
            case "lunch":
                results = this.lunch;
                break;
            case "dinner":
                results = this.dinner;
                break;
            case "active things":
                results = this.activeActivities;
                break;
            case "night life":
                results = this.nightLife;
                break;
            case "shopping":
                results = this.shopping;
                break;
            case "coffee and desserts":
                results = this.coffeeDessert;
                break;
        }
        for (String result: results.keySet()) {
            BusinessResult business = results.get(result);
            if (max == null || business.getRating() > maxRating) {
                max = business;
            }
        }
        return max;
    }

    // returns a random result from activities, drink/desserts, and night life
    // need to fix, this is causing app to crash app cannot get results
    public BusinessResult getRandomResult(String term) {
        Random random = new Random();
        HashMap<String, BusinessResult> results = null;
        switch(term) {
            case "active things":
                results = this.activeActivities;
                break;
            case "coffee and desserts":
                results = this.coffeeDessert;
                break;
            case "shopping":
                results = this.shopping;
                break;
        }
        Set<String> resultKeys = results.keySet();
        BusinessResult randomResult = null;
        int size = resultKeys.size();
        int randomIndex = random.nextInt(size);
        int startingIndex = 0;
        // choose a random result
        for (String key: resultKeys) {
            if (randomResult == null) {
                randomResult = results.get(key);
            }

            if (startingIndex == randomIndex) {
                randomResult = results.get(key);
                break;
            }
        }
        return randomResult;
    }

    public void addUserPlans(String date, Plan plan) {
        this.userPlans.put(date, plan);
    }



    public static yelpAgendaBuilder getInstance() {
        return instance;
    }




}
