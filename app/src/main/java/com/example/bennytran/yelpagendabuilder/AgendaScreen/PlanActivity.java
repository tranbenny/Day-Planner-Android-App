package com.example.bennytran.yelpagendabuilder.AgendaScreen;

// this activity will hold the results for a user's plan
// plan will be based on user preferences, location, and time interval options
// implement event listener to wait for async calls to finish before fragment is loaded


import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

import com.example.bennytran.yelpagendabuilder.GroupScreens.GroupPlanActivity;
import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.SettingsScreen.SettingsActivity;
import com.example.bennytran.yelpagendabuilder.models.BlankResult;
import com.example.bennytran.yelpagendabuilder.models.Plan;
import com.example.bennytran.yelpagendabuilder.models.Time;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;


public class PlanActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String LOG_TAG = PlanActivity.class.getSimpleName();

    private DrawerLayout mDrawerLayout;
    private boolean startsBlank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        initializeScreen();
        startsBlank = getIntent().getBooleanExtra("blank", true);
        Bundle bundle = new Bundle();
        bundle.putBoolean("blank", startsBlank);
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
                Log.i(LOG_TAG, "on plans page");
                break;

            case R.id.nav_create_new_plan:
                Intent createBlank = new Intent(this, CreateNewPlanActivity.class);
                startActivity(createBlank);
                break;

            case R.id.nav_group_plans:
                Log.i(LOG_TAG, "on group plans page");
                Intent intent = new Intent(this, GroupPlanActivity.class);
                intent.putExtra("TITLE", "Plans");
                startActivity(intent);
                break;

            case R.id.nav_group_chats:
                Log.i(LOG_TAG, "on group chats");
                Intent chatIntent = new Intent(this, GroupPlanActivity.class);
                chatIntent.putExtra("TITLE", "group chat");
                startActivity(chatIntent);
                break;

            case R.id.nav_group_preferences:
                Log.i(LOG_TAG, "on group preferences page");
                Intent groupSettingsIntent = new Intent(this, GroupPlanActivity.class);
                groupSettingsIntent.putExtra("TITLE", "group settings");
                startActivity(groupSettingsIntent);
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
