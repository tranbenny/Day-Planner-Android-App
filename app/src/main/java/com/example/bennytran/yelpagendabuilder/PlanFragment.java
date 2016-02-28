package com.example.bennytran.yelpagendabuilder;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class PlanFragment extends Fragment {

    public static final String LOG_TAG = PlanFragment.class.getSimpleName();

    private ArrayAdapter<String> mAdapter;
    private ListView mListView;
    private Context mContext;



    public PlanFragment() {
        // Required empty public constructor
        this.mContext = getActivity();
        Log.i(LOG_TAG, "fragment is created");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // get data from singleton class
    // attach data to a custom adapter and inflate layout onto a list view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ArrayList<String> restaurants = new ArrayList<String>();
        ArrayList<String> locations = new ArrayList<String>();
        for (int i = 0; i < 15; i++) {
            restaurants.add("item " + i);
            locations.add("city");
        }

        Log.i(LOG_TAG, "creating view");

        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        mListView = (ListView) view.findViewById(R.id.lvResults);
        mListView.setAdapter(new CustomAdapter(getActivity(), restaurants, locations));

        return view;
    }


    public class CustomAdapter extends BaseAdapter {

        // private Context context;
        private Activity activity;
        private ArrayList<String> restaurants;
        private ArrayList<String> locations;
        private LayoutInflater inflater;

        public CustomAdapter(Activity a, ArrayList<String> rest, ArrayList<String> locations) {
            Log.i(LOG_TAG, "custom adapter is being created");
            // this.context = context;
            this.activity = a;
            this.restaurants = rest;
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
            TextView tv2Location;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i(LOG_TAG, "creating views");
            Holder holder = new Holder();
            View row = inflater.inflate(R.layout.custom_list_item, null);
            holder.tvName = (TextView) row.findViewById(R.id.tvRestaurant);
            holder.tv2Location = (TextView) row.findViewById(R.id.tvLocation);
            holder.tvName.setText(restaurants.get(position));
            holder.tv2Location.setText(locations.get(position));
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
