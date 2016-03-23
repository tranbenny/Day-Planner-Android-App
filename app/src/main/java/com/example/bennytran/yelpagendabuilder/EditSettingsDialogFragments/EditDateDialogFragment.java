package com.example.bennytran.yelpagendabuilder.EditSettingsDialogFragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import com.example.bennytran.yelpagendabuilder.AgendaScreen.CreateNewPlanActivity;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

import java.util.Calendar;

public class EditDateDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final String LOG_TAG = EditDateDialogFragment.class.getSimpleName();

    private CreateNewPlanActivity activity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        activity = (CreateNewPlanActivity) getActivity();
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, day, month);

    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String dateText = monthOfYear + "/" + dayOfMonth + "/" + year;
        yelpAgendaBuilder.getInstance().currentDate = dateText;
        activity.updateDate(dateText);
    }
}
