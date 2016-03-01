package com.example.bennytran.yelpagendabuilder;

/*

 */


import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create singleton class
        yelpAgendaBuilder app = new yelpAgendaBuilder();
        // starts populating information into singleton class
        FetchItemsTask task = new FetchItemsTask();
        task.execute();
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

        Button settingsBtn = (Button) findViewById(R.id.btnSettings);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }
}
