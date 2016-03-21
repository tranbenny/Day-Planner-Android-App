package com.example.bennytran.yelpagendabuilder.EditSettingsDialogFragments;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;


public class EditLocationDialogFragment extends DialogFragment {

    private static final String LOG_TAG = EditLocationDialogFragment.class.getSimpleName();

    private EditText mEditText;



    public interface EditNameDialogListener {
        void onFinishEditLocationDialog(String inputText);
    }

    public EditLocationDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_location_dialog, container, false);
        mEditText = (EditText) view.findViewById(R.id.newLocationValue);
        mEditText.requestFocus();

        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.i(LOG_TAG, "method here runs");
                if (EditorInfo.IME_ACTION_DONE == actionId) {
                    // EditNameDialogListener activity = (EditNameDialogListener) getActivity();
                    // activity.onFinishEditLocationDialog(mEditText.getText().toString());
                    yelpAgendaBuilder.getInstance().currentLocation = mEditText.getText().toString();
                    Intent intent = new Intent();
                    intent.putExtra("location", mEditText.getText().toString());

                    getTargetFragment().onActivityResult(getTargetRequestCode(), 200, intent);
                    Log.i(LOG_TAG, "should be updating");

                    // this.dismiss();
                    return true;
                }
                return false;
            }
        });


        // Button saveLocation = (Button) view.findViewById(R.id.bt);
        return view;
    }

    /*
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

    } */


}
