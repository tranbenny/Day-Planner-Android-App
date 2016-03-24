package com.example.bennytran.yelpagendabuilder.MainScreen;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;


        // create singleton classes
        yelpAgendaBuilder app = new yelpAgendaBuilder();
        CategoryMapping categoryMapping = new CategoryMapping();
        Firebase.setAndroidContext(this);


        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {

            // load new activity page
            @Override
            public void onClick(View v) {
                // Log in as "exampleUsername"
                logInSuccessful("exampleUsername");
            }
        });

    }

    private void logInSuccessful(String username) {
        final String userName = username;

        Firebase usersRef = new Firebase(Constants.USERS_URL);
        // Log.i(LOG_TAG, Constants.USERS_URL);
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(LOG_TAG, dataSnapshot.child(userName).toString());
                if (dataSnapshot.child(userName) != null) {
                    Log.i(LOG_TAG, "answer is true");
                    yelpAgendaBuilder.getInstance().user = userName;
                    Intent intent = new Intent(mContext, CreateNewPlanActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(mContext, "User not found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(LOG_TAG, firebaseError.getMessage());
            }
        });
    }



}
