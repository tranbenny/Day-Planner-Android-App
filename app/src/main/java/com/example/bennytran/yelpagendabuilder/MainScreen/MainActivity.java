package com.example.bennytran.yelpagendabuilder.MainScreen;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bennytran.yelpagendabuilder.apiCalls.FetchItemsTask;
import com.example.bennytran.yelpagendabuilder.AgendaScreen.PlanActivity;
import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.apiCalls.GetActiveItemsTask;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create singleton class
        yelpAgendaBuilder app = new yelpAgendaBuilder();
        // starts populating information into singleton class

        // execute all tasks
        fetchData();

        // show toast message for starting fetch items task
        Toast toastMessage = Toast.makeText(this, "started fetching data", Toast.LENGTH_LONG);
        toastMessage.show();

        // Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        // setSupportActionBar(myToolbar);

        // set the default values in the settings
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

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


    // executes multiple api calls to get all necessary information
    private void fetchData() {
        FetchItemsTask foodTask = new FetchItemsTask();
        foodTask.execute("food");
        FetchItemsTask activeTask = new FetchItemsTask();
        activeTask.execute("active things");
        FetchItemsTask nightLifeTask = new FetchItemsTask();
        nightLifeTask.execute("night life");
        FetchItemsTask shoppingTask = new FetchItemsTask();
        shoppingTask.execute("shopping");
        FetchItemsTask coffeeTask = new FetchItemsTask();
        coffeeTask.execute("coffee and desserts");
    }
}
