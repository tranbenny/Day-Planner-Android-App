package com.example.bennytran.yelpagendabuilder.AgendaScreen;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bennytran.yelpagendabuilder.R;


public class NotDownLoadingFragment extends Fragment {


    public NotDownLoadingFragment() {
        // Required empty public constructor
    }

    public static NotDownLoadingFragment newInstance(String param1, String param2) {
        NotDownLoadingFragment fragment = new NotDownLoadingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_not_down_loading, container, false);


        return view;
    }



}
