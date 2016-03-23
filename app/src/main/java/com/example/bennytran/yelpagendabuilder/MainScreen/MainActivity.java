package com.example.bennytran.yelpagendabuilder.MainScreen;


import android.app.Application;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bennytran.yelpagendabuilder.AgendaScreen.CreateNewPlanActivity;
import com.example.bennytran.yelpagendabuilder.Util.Constants;
import com.example.bennytran.yelpagendabuilder.apiCalls.CategoryMapping;
import com.example.bennytran.yelpagendabuilder.apiCalls.FetchItemsTask;
import com.example.bennytran.yelpagendabuilder.AgendaScreen.PlanActivity;
import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // create singleton classes
        yelpAgendaBuilder app = new yelpAgendaBuilder();
        CategoryMapping categoryMapping = new CategoryMapping();


        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {

            // load new activity page
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateNewPlanActivity.class);
                startActivity(intent);
            }
        });

    }


}
