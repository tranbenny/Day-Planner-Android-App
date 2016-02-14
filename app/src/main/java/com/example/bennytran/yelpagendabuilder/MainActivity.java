package com.example.bennytran.yelpagendabuilder;

/*
TODO:
- connect all screens with a nav bar
- configure a settings activity to take user preferences
- use user preferences to get all possible results from yelp
- load results of all possible events into app and onto a schedule
- allow option to send schedule to people in a group, allow option to add people into group
- allow option to like certain events and replace events


 */


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        // setSupportActionBar(myToolbar);


        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {

            // load new activity page
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlanActivity.class);
                startActivity(intent);
            }
        });

    }
}
