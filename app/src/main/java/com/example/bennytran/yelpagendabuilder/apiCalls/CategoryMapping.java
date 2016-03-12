package com.example.bennytran.yelpagendabuilder.apiCalls;


// Data Structure: HashMap for each group: categories + boolean value


import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CategoryMapping {

    public static final String LOG_TAG = "CATEGORY MAPPING";
    public static CategoryMapping instance;

    // list of all sub-categories in category groups
    public static final ArrayList<String> RESTAURANT_CATEGORIES = new ArrayList<String>(
            Arrays.asList("American", "Brazilian", "Breakfast and Brunch", "Burgers",
                    "Chinese", "French", "Greek", "Indian", "Irish", "Italian", "Japanese"
                    , "Korean", "Mexican", "Mediterranean", "Pakistani", "Pizza", "Thai"));
    // public HashMap<String, Boolean> RESTAURANT_PREFERENCES;

    public static final ArrayList<String> ACTIVITY_CATEGORIES = new ArrayList<String>(
            Arrays.asList("Hiking", "Amusement Parks", "Aquariums", "Beaches", "Parks"
                    , "Arcades", "Art Galleries", "Casinos", "Cinema", "Festivals", "Museums", "Tours"));
    // public HashMap<String, Boolean> ACTIVITY_PREFERENCES;

    public static final ArrayList<String> NIGHTLIFE_CATEGORIES = new ArrayList<String>(
            Arrays.asList("Bars", "Comedy Clubs", "Dance Clubs", "Music Venues",
                    "Karaoke", "Pool Halls"));
    // public HashMap<String, Boolean> NIGHTLIFE_PREFERENCES;

    public static final ArrayList<String> SHOPPING_CATEGORIES = new ArrayList<String>(
            Arrays.asList("Books", "Video Games", "Fashion", "Computers", "Department Stores"));
    // public HashMap<String, Boolean> SHOPPING_PREFERENCES;

    public static final ArrayList<String> DRINK_CATEGORIES = new ArrayList<String>(
            Arrays.asList("Bakeries", "Coffee", "Tea", "Donuts", "Gelato", "Ice Cream", "Juice Bars",
                    "Candy", "Chocolate"));
    // public HashMap<String, Boolean> DRINK_PREFERENCES;
    public HashMap<String, Boolean> ALL_PREFERENCES;


    public CategoryMapping() {
        if (instance == null) {
            instance = this;
            initializeMap();
        } else {
            Log.i(LOG_TAG, "only one instance allowed");
        }
    }

    // creates a mapping for boolean values for search categories
    private void initializeMap() {
        this.ALL_PREFERENCES = new HashMap<String, Boolean>();
        addCategoriesPreferences(ALL_PREFERENCES, RESTAURANT_CATEGORIES);
        addCategoriesPreferences(ALL_PREFERENCES, ACTIVITY_CATEGORIES);
        addCategoriesPreferences(ALL_PREFERENCES, NIGHTLIFE_CATEGORIES);
        addCategoriesPreferences(ALL_PREFERENCES, SHOPPING_CATEGORIES);
        addCategoriesPreferences(ALL_PREFERENCES, DRINK_CATEGORIES);
    }

    // add all category trackers to map
    private void addCategoriesPreferences(HashMap<String, Boolean> preferences, ArrayList<String> categories) {
        for (String category: categories) {
            preferences.put(category, false);
        }
    }

    public String getExampleValue() {
        return "Bars: " + ALL_PREFERENCES.get("Bars");
    }

    // allows access to category mappings variables
    public static CategoryMapping getInstance() {
        return instance;
    }



}
