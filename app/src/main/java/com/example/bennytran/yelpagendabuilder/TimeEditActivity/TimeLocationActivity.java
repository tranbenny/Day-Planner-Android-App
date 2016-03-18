package com.example.bennytran.yelpagendabuilder.TimeEditActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bennytran.yelpagendabuilder.R;

public class TimeLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_location);


        String type = getIntent().getStringExtra("type");


        switch(type) {
            case "start":
                TimeFragment fragment = new TimeFragment();
                Bundle bundle = new Bundle();
                bundle.putString("type", "time");
                bundle.putString("time", "start");
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.timeEditContainer, fragment).commit();
                break;

            case "end":
                TimeFragment fragment2 = new TimeFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("type", "time");
                bundle2.putString("time", "end");
                fragment2.setArguments(bundle2);

                getFragmentManager().beginTransaction().add(R.id.timeEditContainer, fragment2).commit();
                break;

            case "location":
                TimeFragment fragment3 = new TimeFragment();
                Bundle bundle3 = new Bundle();
                bundle3.putString("type", "location");
                fragment3.setArguments(bundle3);
                getFragmentManager().beginTransaction().add(R.id.timeEditContainer, fragment3).commit();
                break;
        }



    }
}
