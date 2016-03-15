package com.example.bennytran.yelpagendabuilder.TimeEditActivity;


import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bennytran.yelpagendabuilder.R;


public class TimeFragment extends Fragment {


    private String type;

    public TimeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.type = getArguments().getString("type");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        if (this.type == "time") {
            view = inflater.inflate(R.layout.time_picker, container, false);
        } else {
            view = inflater.inflate(R.layout.date_picker, container, false);
        }

        return view;
    }

}
