package com.example.bennytran.yelpagendabuilder.EditSettingsDialogFragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bennytran.yelpagendabuilder.AgendaScreen.CreateNewPlanActivity;
import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

public class EditLocationDialogFragment extends DialogFragment {

    private static final String LOG_TAG = EditLocationDialogFragment.class.getSimpleName();

    private CreateNewPlanActivity activity;
    private EditText mEditText;
    private LayoutInflater mInflater;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        activity = (CreateNewPlanActivity) getActivity();
        mInflater = activity.getLayoutInflater();
        View view = mInflater.inflate(R.layout.fragment_edit_location_dialog, null);
        mEditText = (EditText) view.findViewById(R.id.newLocationValue);


        return new AlertDialog.Builder(getActivity())
                .setIcon(android.R.drawable.stat_notify_error)
                .setTitle("Edit Location")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
                        String newLocation = mEditText.getText().toString();
                        Log.i(LOG_TAG, newLocation);
                        activity.updateLocation(newLocation);
                        yelpAgendaBuilder.getInstance().currentLocation = newLocation;
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Not Saved", Toast.LENGTH_SHORT).show();
                    }
                }).setView(view)
                .create();
    }
}
