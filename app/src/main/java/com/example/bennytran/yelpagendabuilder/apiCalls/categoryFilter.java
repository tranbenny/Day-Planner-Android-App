package com.example.bennytran.yelpagendabuilder.apiCalls;

// uses yelp category json file to choose all the possible categories
// creates different json files for categories:
// - category_filter: list of categories: f.e. bar, french
//        - food
//        - active life
//        - Arts and Entertainment
//        - Nightlife
//        - Restaurants

// USAGE: ran in eclipse java project
// download org.json and org.json.simple jar packages and add to build path
// required import of external library of org.json and org.json.simple jar projects to java build


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class categoryFilter {

    public static void main(String[] args) {

        JSONParser parser = new JSONParser();
        // list to store all sub categories
        JSONArray restaurantCategories = new JSONArray();
        JSONArray foodCategories = new JSONArray();
        JSONArray shoppingCategories = new JSONArray();
        JSONArray artCategories = new JSONArray();
        JSONArray nightlifeCategories = new JSONArray();




        try {
            Object object = parser.parse(new FileReader("categories.json"));
            JSONArray categories = (JSONArray) object;

            for (int i = 0; i < categories.length(); i++) {
                try {
                    JSONObject category = categories.getJSONObject(i);
                    // check if category has any country restrictions
                    // only evaluate the categories without country restrictions
                    if (checkCountryRestrictions(category) == true) {
                        String title = category.getString("title");
                        JSONArray parentCategories = category.getJSONArray("parents");
                        if (parentCategories.length() > 0) {
                            for (int j = 0; j < parentCategories.length(); j++) {
                                String parent = parentCategories.getString(j);
                                switch (parent) {
                                    case "restaurants":
                                        restaurantCategories.put(title);
                                        break;
                                    case "food":
                                        foodCategories.put(title);
                                        break;
                                    case "shopping":
                                        shoppingCategories.put(title);
                                        break;
                                    case "art":
                                        artCategories.put(title);
                                        break;
                                    case "nightlife":
                                        nightlifeCategories.put(title);
                                }
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject categoryMap = new JSONObject();
        try {
            categoryMap.put("restaurant", restaurantCategories);
            categoryMap.put("food", foodCategories);
            categoryMap.put("shopping", shoppingCategories);
            categoryMap.put("art", artCategories);
            categoryMap.put("nightlife", nightlifeCategories);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            FileWriter file = new FileWriter("filteredCategories.json");
            file.write(categoryMap.toString());
            // file.flush(); writes content to destination and makes buffer empty for further data to store
            file.close(); // closes stream permanently, have to re-open to write more
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // takes in an object
    // returns boolean value whether object contains country properties
    public static boolean checkCountryRestrictions(JSONObject object) {
        Iterator iterator = object.keys();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            if (key == "country_blacklist" || key == "country_whitelist") {
                return false;
            }
        }
        return true;
    }

}
