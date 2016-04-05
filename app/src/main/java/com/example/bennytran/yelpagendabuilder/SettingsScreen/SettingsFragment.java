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
import com.example.bennytran.yelpagendabuilder.apiCalls.CategoryMapping;

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

        // category groups
        listDataHeader.add("Restaurants");
        listDataHeader.add("Activities");
        listDataHeader.add("Night Life");
        listDataHeader.add("Shopping");
        listDataHeader.add("Drinks and Desserts");

        // add sub-categories to category groups
        listDataChild.put(listDataHeader.get(0), CategoryMapping.getInstance().RESTAURANT_CATEGORIES);
        listDataChild.put(listDataHeader.get(1), CategoryMapping.getInstance().ACTIVITY_CATEGORIES);
        listDataChild.put(listDataHeader.get(2), CategoryMapping.getInstance().NIGHTLIFE_CATEGORIES);
        listDataChild.put(listDataHeader.get(3), CategoryMapping.getInstance().SHOPPING_CATEGORIES);
        listDataChild.put(listDataHeader.get(4), CategoryMapping.getInstance().DRINK_CATEGORIES);

    }






}
