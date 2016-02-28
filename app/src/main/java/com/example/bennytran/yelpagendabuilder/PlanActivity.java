package com.example.bennytran.yelpagendabuilder;

// this activity will hold the results for a user's plan
// plan will be based on user preferences, location, and time interval options

// TODO:
// create an easy interface
// need to create a custom nav bar at the top
// create a custom list view for the day results

// issue: navbar overlaps list view

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
        getSupportActionBar().setTitle("This is the toolbar!");
        getSupportActionBar().setSubtitle("subtitle");


        // Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().restaurants.toString());
        // add custom listvew fragment to activity
        getFragmentManager().beginTransaction().replace(R.id.activity_container, new PlanFragment()).commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
