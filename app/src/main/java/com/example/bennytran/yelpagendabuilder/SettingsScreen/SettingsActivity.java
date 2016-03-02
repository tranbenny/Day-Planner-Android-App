package com.example.bennytran.yelpagendabuilder.SettingsScreen;

/*
yelp Search parameters:
optional:
- search term: f.e. restaurants, things to do, etc.
- sort: sort mode: distance, etc.
- category_filter: list of categories: f.e. bar, french
    - food
    - active life
    - Arts and Entertainment
    - Hotels and Travel
    - Nightlife
    - Restaurants
- radius_filter: search radius in meters, max: 25 miles
- deals_filter: search exclusively for businesses with deals

required:
- location

*/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment())
                .commit();


    }

}
