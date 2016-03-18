package com.example.bennytran.yelpagendabuilder.TimeEditActivity;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.bennytran.yelpagendabuilder.AgendaScreen.CreateNewPlanActivity;
import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;


public class TimeFragment extends Fragment {

    public static final String LOG_TAG = "TIME_FRAGMENT";

    private String type;
    private String time = "";

    public TimeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.type = getArguments().getString("type");
        if (this.type == "time") {
            this.time = getArguments().getString("time");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        final String time = this.time;
        if (this.type == "time") {
            view = inflater.inflate(R.layout.time_picker, container, false);
            final TimePicker timePicker = (TimePicker) view.findViewById(R.id.timePicker);

            Button saveTimeButton = (Button) view.findViewById(R.id.btnSaveTime);
            saveTimeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int hour = timePicker.getCurrentHour();
                    int minute = timePicker.getCurrentMinute();

                    if (time == "start") {
                        // update start time field
                        yelpAgendaBuilder.getInstance().currentStartTime.setNewTime(hour, minute);
                    } else if (time == "end") {
                        // update end time field
                        yelpAgendaBuilder.getInstance().currentEndTime.setNewTime(hour, minute);
                    }
                    Intent blankIntent = new Intent(getActivity(), CreateNewPlanActivity.class);
                    startActivity(blankIntent);
                }
            });

        } else {
            view = inflater.inflate(R.layout.date_picker, container, false);
            final DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);

            Button saveDateButton = (Button) view.findViewById(R.id.btnSaveDate);
            saveDateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // update date field
                    int month = datePicker.getMonth();
                    int day = datePicker.getDayOfMonth();
                    int year = datePicker.getYear();
                    String date = month + "/" + day + "/" + year;
                    yelpAgendaBuilder.getInstance().currentDate = date;
                    Intent blankIntent = new Intent(getActivity(), CreateNewPlanActivity.class);
                    startActivity(blankIntent);
                }
            });

        }

        return view;
    }

}
