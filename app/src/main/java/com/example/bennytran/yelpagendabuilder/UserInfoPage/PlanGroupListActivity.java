package com.example.bennytran.yelpagendabuilder.UserInfoPage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bennytran.yelpagendabuilder.AgendaScreen.CreateNewPlanActivity;
import com.example.bennytran.yelpagendabuilder.AgendaScreen.PlanActivity;
import com.example.bennytran.yelpagendabuilder.AgendaScreen.PlanFragment;
import com.example.bennytran.yelpagendabuilder.GroupScreens.GroupPlanActivity;
import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.SettingsScreen.SettingsActivity;
import com.example.bennytran.yelpagendabuilder.Util.Constants;
import com.example.bennytran.yelpagendabuilder.models.Plan;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.zip.Inflater;

// activity for displaying user's plans and groups

public class PlanGroupListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = PlanGroupListActivity.class.getSimpleName();

    private Context mContext;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;

    private ListView mListViewGroups;
    private ListView mListViewPlans;
    private ArrayList<String> groups;
    private ArrayList<String> plans;
    private GroupPlanAdapter mGroupAdapter;
    private GroupPlanAdapter mPlanAdapter;


    private Firebase mUserRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_group_list);

        initializeScreen();
        groups = new ArrayList<String>();
        plans = new ArrayList<String>();
        mGroupAdapter = new GroupPlanAdapter(this, groups, "group");
        mPlanAdapter = new GroupPlanAdapter(this, plans, "plan");
        mListViewGroups.setAdapter(mGroupAdapter);
        mListViewPlans.setAdapter(mPlanAdapter);

        mUserRef = new Firebase(Constants.getUserInfoURL());
        getUserInfo();
        Log.i(LOG_TAG, Constants.getUserInfoURL());

    }

    // handles navigation icon clicks 
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
                Log.i(LOG_TAG, "already on page");
                break;

            case R.id.nav_create_new_plan:
                Log.i(LOG_TAG, "on create page");
                Intent createNewPlanIntent = new Intent(this, CreateNewPlanActivity.class);
                startActivity(createNewPlanIntent);
                break;
        }

        // close navigation drawer when button is clicked
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // sets up screen toolbar and drawer layout
    private void initializeScreen() {

        mContext = this;
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("My Group & Plans ");

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        mListViewGroups = (ListView) findViewById(R.id.lvGroups);
        mListViewPlans = (ListView) findViewById(R.id.lvPlans);
    }

    // get group and plan info
    private void getUserInfo() {
        // read in all the groups for the user
        mUserRef.child("groups").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String groupName = dataSnapshot.getKey();
                groups.add(groupName);
                yelpAgendaBuilder.getInstance().userGroups.add(groupName);
                mGroupAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(LOG_TAG, firebaseError.toString());
            }
        });

        // read in all the plans for the user
        mUserRef.child("plans").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                Plan.loadFromFirebase(key); // saves plan to app's instance
                key = key.replace(":", "/");
                plans.add(key);
                mPlanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    // custom adapter for creating list views
    private class GroupPlanAdapter extends BaseAdapter {

        private ArrayList<String> items;
        private LayoutInflater mInflater;
        private String type;


        public GroupPlanAdapter(Activity activity, ArrayList<String> items, String type) {
            this.items = items;
            this.mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.type = type;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        // on click load the correct plan in a plan activity
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final String text = items.get(position);
            View row = mInflater.inflate(R.layout.user_info_list_item, null);
            TextView name = (TextView) row.findViewById(R.id.tvPlanGroupName);
            name.setText(text);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // when plan is clicked, should load up the plan activity
                    if (type == "plan") {
                        Log.i(LOG_TAG, "you clicked " + text);
                        Plan clickedPlan = yelpAgendaBuilder.getInstance().userPlans.get(text);
                        yelpAgendaBuilder.getInstance().currentPlan = clickedPlan;
                        yelpAgendaBuilder.getInstance().currentDate = text;
                        Intent intent = new Intent(mContext, PlanActivity.class);
                        intent.putExtra("blank", false);
                        intent.putExtra("old", true);
                        startActivity(intent);
                    } else {
                        // type = group
                        Log.i(LOG_TAG, "you clicked group " + text);
                        yelpAgendaBuilder.getInstance().currentGroup = text;
                        Intent intent = new Intent(mContext, GroupPlanActivity.class);
                        intent.putExtra("group", text);
                        startActivity(intent);
                    }
                }
            });
            return row;
        }
    }

}
