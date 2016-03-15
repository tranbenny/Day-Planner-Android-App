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


        // show toast message for starting fetch items task
        Toast toastMessage = Toast.makeText(this, "started fetching data", Toast.LENGTH_LONG);
        toastMessage.show();
        // execute all tasks
        fetchData();

        // Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        // setSupportActionBar(myToolbar);

        // set the default values in the settings
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

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


    // executes multiple api calls to get all necessary information
    private void fetchData() {

        FetchItemsTask breakfastTask = new FetchItemsTask(this);
        breakfastTask.execute("breakfast and brunch");
        FetchItemsTask lunchTask = new FetchItemsTask(this);
        lunchTask.execute("lunch");
        FetchItemsTask dinnerTask = new FetchItemsTask(this);
        dinnerTask.execute("dinner");


        FetchItemsTask activeTask = new FetchItemsTask(this);
        activeTask.execute("active things");
        FetchItemsTask nightLifeTask = new FetchItemsTask(this);
        nightLifeTask.execute("night life");
        FetchItemsTask shoppingTask = new FetchItemsTask(this);
        shoppingTask.execute("shopping");

        FetchItemsTask coffeeTask = new FetchItemsTask(this);
        coffeeTask.execute("coffee and desserts");
    }
}
