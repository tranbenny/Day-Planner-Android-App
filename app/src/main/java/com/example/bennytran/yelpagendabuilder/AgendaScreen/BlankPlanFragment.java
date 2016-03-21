package com.example.bennytran.yelpagendabuilder.AgendaScreen;

// blank screen for entering start/end times, location search, 2 buttons: generated plan + blank plan

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bennytran.yelpagendabuilder.EditSettingsDialogFragments.EditLocationDialogFragment;
import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.TimeEditActivity.TimeLocationActivity;


public class BlankPlanFragment extends Fragment {

    private static final String LOG_TAG = BlankPlanFragment.class.getSimpleName();
    private TextView mLocation;
    private BlankPlanFragment mInstance = this;


    public BlankPlanFragment() { }

    public static BlankPlanFragment newInstance(String param1, String param2) {
        BlankPlanFragment fragment = new BlankPlanFragment();
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
        View view = inflater.inflate(R.layout.fragment_blank_plan, container, false);
        mLocation = (TextView) view.findViewById(R.id.valueLocation);
        Button newPlanBtn = (Button) view.findViewById(R.id.btnGeneratePlan);
        Button blankBtn = (Button) view.findViewById(R.id.btnBlankPlan);

        newPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open plan page:
                Intent intent = new Intent(getActivity(), PlanActivity.class);
                intent.putExtra("blank", false);
                startActivity(intent);
            }
        });

        blankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open a blank template for creating a plan
                Intent intent = new Intent(getActivity(), PlanActivity.class);
                intent.putExtra("blank", true);
                startActivity(intent);
            }
        });

        // add buttons to load new activity for editing times/dates
        Button locationEdit = (Button) view.findViewById(R.id.btnLocationEdit);
        Button startTimeEdit = (Button) view.findViewById(R.id.btnLoadEditDate);
        Button endTimeEdit = (Button) view.findViewById(R.id.btnLoadEditEndTime);
        Button dateEdit = (Button) view.findViewById(R.id.btnLoadEditDate);

        // change these to use a dialog fragment
        locationEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditLocationDialogFragment fragment = new EditLocationDialogFragment();
                fragment.setTargetFragment(mInstance, 200);
                fragment.show(getFragmentManager(), "Location Edit Fragment");
            }
        });



        startTimeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startTime = new Intent(getActivity(), TimeLocationActivity.class);
                startTime.putExtra("type", "start");
                startActivity(startTime);
            }
        });

        endTimeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent endTime = new Intent(getActivity(), TimeLocationActivity.class);
                endTime.putExtra("type", "end");
                startActivity(endTime);
            }
        });

        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent location = new Intent(getActivity(), TimeLocationActivity.class);
                location.putExtra("type", "location");
                startActivity(location);
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(LOG_TAG, "onActivityResult method was called");
        if (requestCode == 200) {
            if (resultCode == 200) {
                String newLocation = data.getStringExtra("location");
                mLocation.setText(newLocation);
            }
        }
    }
}
