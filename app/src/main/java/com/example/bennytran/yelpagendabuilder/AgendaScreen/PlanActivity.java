package com.example.bennytran.yelpagendabuilder.AgendaScreen;

// this activity will hold the results for a user's plan
// plan will be based on user preferences, location, and time interval options


import android.content.Intent;
import android.support.design.widget.NavigationView;
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


public class PlanActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String LOG_TAG = "PLAN ACTIVITY";

    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

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

        // sets up content from a fragment
        // Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().restaurants.toString());
        // add custom listvew fragment to activity
        // getFragmentManager().beginTransaction().replace(R.id.activity_container, new PlanFragment()).commit();
        getFragmentManager().beginTransaction().replace(R.id.activity_container, new PlanFragment()).commit();
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
}
