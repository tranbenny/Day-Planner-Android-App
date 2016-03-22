package com.example.bennytran.yelpagendabuilder.AgendaScreen;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bennytran.yelpagendabuilder.EditSettingsDialogFragments.EditLocationDialogFragment;
import com.example.bennytran.yelpagendabuilder.GroupScreens.GroupPlanActivity;
import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.SettingsScreen.SettingsActivity;

public class CreateNewPlanActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String LOG_TAG = CreateNewPlanActivity.class.getSimpleName();
    private CreateNewPlanActivity mInstance = this;

    // navigation components
    private DrawerLayout mDrawerLayout;

    // Plan configuration buttons
    private Button mBtnNewPlan;
    private Button mBtnBlankPlan;
    private Button mBtnEditLocation;
    private Button mBtnEditStartTime;
    private Button mBtnEditEndTime;
    private Button mBtnEditDate;

    // plan configuration displayed options
    private TextView mLocation;
    private TextView mStartTime;
    private TextView mEndTime;
    private TextView mDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_plan);

        initializeScreen();
        locateButtonsAndLabels();
        addButtonListeners();

        // add fragment to screen
        // need to take this fragment out and load everything on the activity

        //getFragmentManager().beginTransaction().add(R.id.createNewActivity_container, new BlankPlanFragment())
        //        .addToBackStack(null).commit();

    }



    // handle navigation drawer clicks
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
                Intent planIntent = new Intent(this, PlanActivity.class);
                startActivity(planIntent);
                break;

            case R.id.nav_create_new_plan:
                Log.i(LOG_TAG, "on create page");
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

    // creates toolbar and navigation screen
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

    // attach button/text views to layout file
    private void locateButtonsAndLabels() {
        mBtnNewPlan = (Button) findViewById(R.id.btnGeneratePlan);
        mBtnBlankPlan = (Button) findViewById(R.id.btnBlankPlan);
        mBtnEditLocation = (Button) findViewById(R.id.btnLocationEdit);
        mBtnEditStartTime = (Button) findViewById(R.id.btnLoadEditTime);
        mBtnEditEndTime = (Button) findViewById(R.id.btnLoadEditEndTime);
        mBtnEditDate = (Button) findViewById(R.id.btnLoadEditDate);


        mLocation = (TextView) findViewById(R.id.valueLocation);
        mStartTime = (TextView) findViewById(R.id.valueStartTime);
        mEndTime = (TextView) findViewById(R.id.valueEndTime);
        mDate = (TextView) findViewById(R.id.valueDate);
    }

    // adds click functions to all the buttons on the screen
    private void addButtonListeners() {
        mBtnNewPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mInstance, PlanActivity.class);
                intent.putExtra("blank", false);
                startActivity(intent);
            }
        });

        mBtnBlankPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mInstance, PlanActivity.class);
                intent.putExtra("blank", true);
                startActivity(intent);
            }
        });

        // buttons below load dialog fragments
        mBtnEditLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditLocationDialogFragment fragment = new EditLocationDialogFragment();
                fragment.show(getFragmentManager(), "Edit Location Fragmen");
            }
        });

        mBtnEditStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBtnEditEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBtnEditDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void updateLocation(String newValue) {
        mLocation.setText(newValue);
    }

}
