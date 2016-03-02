package com.example.bennytran.yelpagendabuilder.AgendaScreen;

// this activity will hold the results for a user's plan
// plan will be based on user preferences, location, and time interval options


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

import com.example.bennytran.yelpagendabuilder.R;


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

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.nav_camera) {
            Log.i(LOG_TAG, "you pressed the camera button");
        } else {
            Log.i(LOG_TAG, "you pressed a navigation bar button");
        }
        // close navigation drawer when button is clicked
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
