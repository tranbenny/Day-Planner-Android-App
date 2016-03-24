package com.example.bennytran.yelpagendabuilder.AgendaScreen;

// this activity will hold the results for a user's plan
// plan will be based on user preferences, location, and time interval options


import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

import com.example.bennytran.yelpagendabuilder.FirebaseModels.FirebaseBusiness;
import com.example.bennytran.yelpagendabuilder.GroupScreens.GroupPlanActivity;
import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.SettingsScreen.SettingsActivity;
import com.example.bennytran.yelpagendabuilder.Util.Constants;
import com.example.bennytran.yelpagendabuilder.models.BlankResult;
import com.example.bennytran.yelpagendabuilder.models.Plan;
import com.example.bennytran.yelpagendabuilder.models.Time;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;


public class PlanActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String LOG_TAG = PlanActivity.class.getSimpleName();

    private DrawerLayout mDrawerLayout;
    private boolean startsBlank;
    private boolean loadingOldPlan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        initializeScreen();
        startsBlank = getIntent().getBooleanExtra("blank", true);
        loadingOldPlan = getIntent().getBooleanExtra("old", false);
        Bundle bundle = new Bundle();
        bundle.putBoolean("blank", startsBlank);
        bundle.putBoolean("old", loadingOldPlan);
        PlanFragment fragment = new PlanFragment();
        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(R.id.activity_container, fragment, "FIRST_LIST")
                .addToBackStack(null).commit();
    }


    @Override
    protected void onResume() {
        Log.i(LOG_TAG, "ON RESUME WAS CALLED");
        // LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("finished-loading"));
        super.onResume();
    }


    // close navigation drawer if it is open, otherwise go back to the last activity
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // handles click events for toolbar menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save_plan) {
            // save plan to firebase backend
            Toast.makeText(this, "saving plan", Toast.LENGTH_LONG).show();
            Firebase planRef = new Firebase(Constants.getUserPlansURL());
            HashMap<String, FirebaseBusiness> newSavedPlan = Plan.createFirebaseForm();
            String key = yelpAgendaBuilder.getInstance().currentDate;
            key = key.replace("/", ":");

            planRef.child(key).setValue(newSavedPlan);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }



    // opens new activity on navigation button click
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch(id) {
            case R.id.nav_preferences:
                Log.i(LOG_TAG, "open settings screen");
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                break;

            case R.id.nav_personal_plans:
                // just close drawer, already on this page
                Intent userIntent = new Intent(this, GroupPlanActivity.class);
                startActivity(userIntent);
                break;

            case R.id.nav_create_new_plan:
                Intent createBlank = new Intent(this, CreateNewPlanActivity.class);
                startActivity(createBlank);
                break;

        }

        // close navigation drawer when button is clicked
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    // set up activity screen
    private void initializeScreen() {
        // set up tool bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(yelpAgendaBuilder.getInstance().currentDate);

        // set up navigation bar
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_drawer);
        navigationView.setNavigationItemSelectedListener(this);
    }







}
