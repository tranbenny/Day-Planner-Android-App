package com.example.bennytran.yelpagendabuilder.SettingsScreen;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
// import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.bennytran.yelpagendabuilder.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
User Preferences to add:
Time:
- Date choices, time interval for days

User:
- types of restaurants
- locations of restaurants
- activity category settings
- desserts
- look at yelp api for other possible preferences

 */



public class SettingsFragment extends Fragment {


    ExpandableListAdapter adapter;
    ExpandableListView mListView;

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    public SettingsFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        mListView = (ExpandableListView) view.findViewById(R.id.lvPreferencesGroups);
        prepareListData();
        adapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        mListView.setAdapter(adapter);

        return view;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


        listDataHeader.add("Restaurants");
        // american, brazillian, breakfast_brunch, buffet, burgers, chinese, french, greek
        // indian, irish, italian, japanese, korean, mexican, mediterranean, pakistani
        // pizza, thai,
        listDataHeader.add("Activities");
        // hiking, amusement parks, aquariums, beaches, escape games, hiking, parks
        // arcades, art gallers, casinos, cinema, festivals, museums, music venues
        // tours
        listDataHeader.add("Night Life");
        // bars, comedy clubs, dance clubs, music venues, karaoke, pool halls
        listDataHeader.add("Shopping");
        // book stores, video games, fashion, computers, department stores
        listDataHeader.add("Drinks and Desserts");
        // bakeries, coffee, tea, donuts, gelato, ice cream, juice bars, candy, chocolate
        // beer, wine

        ArrayList<String> restaurantCategories = new ArrayList<String>(
                Arrays.asList("American", "Brazilian", "Breakfast and Brunch", "Burgers",
                        "Chinese", "French", "Greek", "Indian", "Irish", "Italian", "Japanese"
                , "Korean", "Mexican", "Mediterranean", "Pakistani", "Pizza", "Thai"));
        ArrayList<String> activeCategories = new ArrayList<String>(
                Arrays.asList("Hiking", "Amusement Parks", "Aquariums", "Beaches", "Parks"
                , "Arcades", "Art Galleries", "Casinos", "Cinema", "Festivals", "Museums", "Tours"));
        ArrayList<String> nightLifeCategories = new ArrayList<String>(
                Arrays.asList("Bars", "Comedy Clubs", "Dance Clubs", "Music Venues",
                        "Karaoke", "Pool Halls"));
        ArrayList<String> shoppingCategories = new ArrayList<String>(
                Arrays.asList("Books", "Video Games", "Fashion", "Computers", "Department Stores"));
        ArrayList<String> drinkCategories = new ArrayList<String>(
                Arrays.asList("Bakeries", "Coffee", "Tea", "Donuts", "Gelato", "Ice Cream", "Juice Bars",
                        "Candy", "Chocolate"));

        listDataChild.put(listDataHeader.get(0), restaurantCategories);
        listDataChild.put(listDataHeader.get(1), activeCategories);
        listDataChild.put(listDataHeader.get(2), nightLifeCategories);
        listDataChild.put(listDataHeader.get(3), shoppingCategories);
        listDataChild.put(listDataHeader.get(4), drinkCategories);


    }




}
