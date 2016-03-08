package com.example.bennytran.yelpagendabuilder.GroupScreens;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bennytran.yelpagendabuilder.R;


public class GroupPreferencesFragment extends Fragment {

    private String title;
    private int page;


    public GroupPreferencesFragment() {
        // Required empty public constructor
    }

    public static GroupPreferencesFragment newInstance(int page, String title) {
        GroupPreferencesFragment fragment = new GroupPreferencesFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("someTitle");
        page = getArguments().getInt("someInt", 0);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group_preferences, container, false);
    }


}
