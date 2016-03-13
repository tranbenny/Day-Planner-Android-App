package com.example.bennytran.yelpagendabuilder.AgendaScreen;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bennytran.yelpagendabuilder.ItemDetailsPage.ItemDetails;
import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.apiCalls.CategoryMapping;
import com.example.bennytran.yelpagendabuilder.apiCalls.FetchItemsTask;
import com.example.bennytran.yelpagendabuilder.models.BusinessResult;
import com.example.bennytran.yelpagendabuilder.models.Plan;
import com.example.bennytran.yelpagendabuilder.models.Time;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

import java.util.ArrayList;
import java.util.Set;


public class PlanFragment extends Fragment {

    public static final String LOG_TAG = PlanFragment.class.getSimpleName();

    private ArrayAdapter<String> mAdapter;
    private ListView mListView;
    private Context mContext;
    private yelpAgendaBuilder app;



    public PlanFragment() {
        // Required empty public constructor
        this.mContext = getActivity();
        app = yelpAgendaBuilder.getInstance();
        // Log.i(LOG_TAG, "fragment is created");
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
        // ArrayList<String> restaurants = new ArrayList<String>();

        // ArrayList<String> restaurants = new ArrayList<String>();
        // ArrayList<String> startTimes = new ArrayList<String>();
        // ArrayList<String> categories = new ArrayList<String>();
        Plan generatedPlan = new Plan(new Time(9,0), new Time(23, 0));
        // for(BusinessResult business: generatedPlan.planItems) {
            //restaurants.add(business.getName());
            //categories.add(business.formatCategories());
        //}

        //ArrayList<Time> timeSlots = generatedPlan.timeSlots;
        //for (Time time: timeSlots) {
            //startTimes.add(time.toString());
        //}


        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        mListView = (ListView) view.findViewById(R.id.lvResults);
        mListView.setAdapter(new CustomAdapter(getActivity(), generatedPlan));

        /* swipe layout refresh button
        final SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            // reload results
            @Override
            public void onRefresh() {
                Log.i(LOG_TAG, "view should be refreshing");
                Log.i(LOG_TAG, CategoryMapping.getInstance().getExampleValue());
                // ((CustomAdapter) mListView.getAdapter()).notifyDataSetChanged();
                swipeLayout.setRefreshing(false);
            }
        });
        */

        return view;
    }


    public class CustomAdapter extends BaseAdapter {

        // private Context context;
        private Activity activity;
        Plan plan;
        /*
        private ArrayList<String> restaurants;
        private ArrayList<String> start;
        private ArrayList<String> categories;
        */

        private LayoutInflater inflater;

        public CustomAdapter(Activity activity, Plan plan) {
            this.activity = activity;
            this.plan = plan;
            this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return this.plan.timeSlots.size();
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
            ImageView background;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // Log.i(LOG_TAG, "creating views");
            Holder holder = new Holder();
            View row = inflater.inflate(R.layout.custom_list_item, null);

            holder.tvName = (TextView) row.findViewById(R.id.tvRestaurant);
            holder.tvStart = (TextView) row.findViewById(R.id.tvStart);
            holder.tvCategory = (TextView) row.findViewById(R.id.tvCategories);
            holder.background = (ImageView) row.findViewById(R.id.imageBackground);

            // changing values
            holder.tvName.setText(plan.planItems.get(position).getName());
            holder.tvStart.setText(plan.timeSlots.get(position).toString());
            holder.tvCategory.setText(plan.planItems.get(position).formatCategories());

            holder.background.setImageResource(plan.planItems.get(position).getImageID());
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(LOG_TAG, "you clicked one of the items");
                    Intent intent = new Intent(getActivity(), ItemDetails.class);
                    intent.putExtra("TITLE", "restaurant title");
                    startActivity(intent);
                }
            });

            return row;
        }
    }


}
