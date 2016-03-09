package com.example.bennytran.yelpagendabuilder.ItemDetailsPage;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
// import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bennytran.yelpagendabuilder.R;


/* details to include:

name
category
location

time
favorites option

 */



public class ItemDetailsFragment extends Fragment {


    public ItemDetailsFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);
        String title = getArguments().getString("title");

        TextView tvTitle = (TextView) view.findViewById(R.id.tvBusinessName);
        tvTitle.setText(title);
        TextView tvLocation = (TextView) view.findViewById(R.id.tvBusinessLocation);


        return view;


    }

}
