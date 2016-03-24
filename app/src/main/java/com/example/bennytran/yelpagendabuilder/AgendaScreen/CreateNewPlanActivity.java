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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bennytran.yelpagendabuilder.EditSettingsDialogFragments.EditDateDialogFragment;
import com.example.bennytran.yelpagendabuilder.EditSettingsDialogFragments.EditLocationDialogFragment;
import com.example.bennytran.yelpagendabuilder.EditSettingsDialogFragments.EditTimeDialogFragment;
import com.example.bennytran.yelpagendabuilder.GroupScreens.GroupPlanActivity;
import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.SettingsScreen.SettingsActivity;
import com.example.bennytran.yelpagendabuilder.UserInfoPage.PlanGroupListActivity;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

public class CreateNewPlanActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String LOG_TAG = CreateNewPlanActivity.class.getSimpleName();
    private CreateNewPlanActivity mInstance = this;
    // app instance
    private yelpAgendaBuilder app = yelpAgendaBuilder.getInstance();

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
                Intent planIntent = new Intent(this, PlanGroupListActivity.class);
                startActivity(planIntent);
                break;

            case R.id.nav_create_new_plan:
                Log.i(LOG_TAG, "on create page");
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

        // set up default display values for text fields
        mLocation = (TextView) findViewById(R.id.valueLocation);
        mLocation.setText(app.currentLocation);
        mStartTime = (TextView) findViewById(R.id.valueStartTime);
        mStartTime.setText(app.currentStartTime.toString());
        mEndTime = (TextView) findViewById(R.id.valueEndTime);
        mEndTime.setText(app.currentEndTime.toString());
        mDate = (TextView) findViewById(R.id.valueDate);
        mDate.setText(app.currentDate);
    }

    // adds click functions to all the buttons on the screen
    private void addButtonListeners() {
        mBtnNewPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mInstance, PlanActivity.class);
                intent.putExtra("blank", false);
                intent.putExtra("old", false);
                startActivity(intent);
            }
        });

        mBtnBlankPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mInstance, PlanActivity.class);
                intent.putExtra("blank", true);
                intent.putExtra("old", false);
                startActivity(intent);
            }
        });

        // buttons below load dialog fragments for editing the settings
        mBtnEditLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditLocationDialogFragment fragment = new EditLocationDialogFragment();
                fragment.show(getFragmentManager(), "Edit Location Fragment");
            }
        });

        mBtnEditStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // put in bundle start/end
                Bundle bundle = new Bundle();
                bundle.putBoolean("start", true);
                EditTimeDialogFragment fragment = new EditTimeDialogFragment();
                fragment.setArguments(bundle);
                fragment.show(getFragmentManager(), "Edit Time Fragment");
            }
        });

        mBtnEditEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("start", false);
                EditTimeDialogFragment fragment = new EditTimeDialogFragment();
                fragment.setArguments(bundle);
                fragment.show(getFragmentManager(), "Edit Time Fragment");
            }
        });

        mBtnEditDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditDateDialogFragment fragment = new EditDateDialogFragment();
                fragment.show(getFragmentManager(), "Edit Date Fragment");
            }
        });
    }

    // updates location text view with new text
    public void updateLocation(String newValue) {
        mLocation.setText(newValue);
    }
    // updates start/end time text views with new time
    public void updateTime(String newTime, boolean start) {
        if (start) {
            mStartTime.setText(newTime);
        } else {
            mEndTime.setText(newTime);
        }
    }
    // updates date text view with new date
    public void updateDate(String newDate) {
        mDate.setText(newDate);
    }


}
