package com.example.bennytran.yelpagendabuilder.EditSettingsDialogFragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bennytran.yelpagendabuilder.R;


public class EditTimeDialogFragment extends DialogFragment {


    public EditTimeDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_time_dialog, container, false);
    }

}
