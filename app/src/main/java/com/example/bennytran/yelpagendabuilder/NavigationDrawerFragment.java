package com.example.bennytran.yelpagendabuilder;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NavigationDrawerFragment extends Fragment {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        setupListView(view);
        return view;
    }

    private void setupListView(View view) {
        ListView optionsList = (ListView) view.findViewById(R.id.lvNavigationPages);
        ArrayList<String> pages = new ArrayList<String>();
        pages.add("User Preferences");
        pages.add("My Groups");
        pages.add("My Agenda Plans");
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, pages);
        optionsList.setAdapter(adapter);
        // adapter.add("User Preferences");
        // optionsList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void setUpDrawer(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        // mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, 0,0);


    }





}
