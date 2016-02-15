package com.example.bennytran.yelpagendabuilder;

// this activity will hold the results for a user's plan
// plan will be based on user preferences, location, and time interval options

// TODO:
// create an easy interface
// need to create a custom nav bar at the top
// create a custom list view for the day results

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;


public class PlanActivity extends AppCompatActivity {

    public static final String LOG_TAG = "PLAN ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // create a fragment here to hold the information
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
