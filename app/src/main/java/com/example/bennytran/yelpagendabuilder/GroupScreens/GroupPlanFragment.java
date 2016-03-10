package com.example.bennytran.yelpagendabuilder.GroupScreens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bennytran.yelpagendabuilder.AgendaScreen.PlanFragment;
import com.example.bennytran.yelpagendabuilder.ItemDetailsPage.ItemDetails;
import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

import java.util.ArrayList;
import java.util.Set;


public class GroupPlanFragment extends Fragment {

    public static final String LOG_TAG = "GROUP PLAN FRAGMENT";

    private yelpAgendaBuilder app;
    private ListView mListView;


    public GroupPlanFragment() {
        // Required empty public constructor
    }

    public static GroupPlanFragment newInstance() {
        GroupPlanFragment fragment = new GroupPlanFragment();
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.app = yelpAgendaBuilder.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // View view = inflater.inflate(R.layout.fragment_group_plan, container, false);

        Set<String> places = app.restaurants.keySet();
        ArrayList<String> restaurants = new ArrayList<>();
        restaurants.addAll(places);
        ArrayList<String> startTimes = app.getStart();
        ArrayList<String> endTimes = app.getEnd();
        ArrayList<String> categories = new ArrayList<String>();
        ArrayList<String> locations = new ArrayList<String>();

        for (int i = 0; i < restaurants.size(); i++) {
            categories.add("food");
            locations.add("Seattle");
        }

        // Log.i(LOG_TAG, "creating view");

        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        mListView = (ListView) view.findViewById(R.id.lvResults);
        mListView.setAdapter(new CustomAdapter(getActivity(), restaurants, startTimes, endTimes, categories, locations));

        return view;
    }


    public class CustomAdapter extends BaseAdapter {

        // private Context context;
        private Activity activity;
        private ArrayList<String> restaurants;
        private ArrayList<String> start;
        private ArrayList<String> end;
        private ArrayList<String> categories;
        private ArrayList<String> locations;

        private LayoutInflater inflater;

        public CustomAdapter(Activity a, ArrayList<String> rest, ArrayList<String> start, ArrayList<String>
                end, ArrayList<String> categories, ArrayList<String> locations) {
            // Log.i(LOG_TAG, "custom adapter is being created");
            // this.context = context;
            this.activity = a;
            this.restaurants = rest;
            this.start = start;
            this.end = end;
            this.categories = categories;
            this.locations = locations;

            this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return this.restaurants.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // holder class for text view data
        public class Holder {
            TextView tvName;
            TextView tvCategory;
            TextView tvStart;
            TextView tvLocation;
            ImageView background;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // Log.i(LOG_TAG, "creating views");
            Holder holder = new Holder();
            View row = inflater.inflate(R.layout.custom_list_item, null);
            holder.tvName = (TextView) row.findViewById(R.id.tvRestaurant);
            holder.tvStart = (TextView) row.findViewById(R.id.tvStart);

            holder.background = (ImageView) row.findViewById(R.id.imageBackground);

            // changing values
            holder.tvName.setText(restaurants.get(position));
            holder.tvStart.setText(start.get(position));

            holder.background.setImageResource(app.getRandomImage());
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(LOG_TAG, "you clicked one of the items");
                }
            });

            return row;
        }
    }

}
