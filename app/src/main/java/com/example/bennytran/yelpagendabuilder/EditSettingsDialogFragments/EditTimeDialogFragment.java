package com.example.bennytran.yelpagendabuilder.EditSettingsDialogFragments;


import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.example.bennytran.yelpagendabuilder.AgendaScreen.CreateNewPlanActivity;
import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.models.Time;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

import java.util.Calendar;


public class EditTimeDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private static final String LOG_TAG = EditTimeDialogFragment.class.getSimpleName();

    private boolean start;
    private CreateNewPlanActivity activity;


    public EditTimeDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.start = getArguments().getBoolean("start");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        activity = (CreateNewPlanActivity) getActivity();
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }


    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_time_dialog, container, false);
    }
    */

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.i(LOG_TAG, "hour is " + hourOfDay + " and minute is " + minute);
        Time newTime = new Time(hourOfDay, minute);
        String displayTime = newTime.toString();
        if (this.start) {
            // start time
            yelpAgendaBuilder.getInstance().currentStartTime = newTime;
            activity.updateTime(displayTime, true);
        } else {
            // end time
            yelpAgendaBuilder.getInstance().currentEndTime = newTime;
            activity.updateTime(displayTime, false);
        }
    }
}
