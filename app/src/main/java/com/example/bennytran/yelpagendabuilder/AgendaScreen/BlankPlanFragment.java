package com.example.bennytran.yelpagendabuilder.AgendaScreen;

// blank screen for entering start/end times, location search, 2 buttons: generated plan + blank plan

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bennytran.yelpagendabuilder.R;


public class BlankPlanFragment extends Fragment {


    public BlankPlanFragment() {
        // Required empty public constructor
    }

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


        return view;
    }




}
