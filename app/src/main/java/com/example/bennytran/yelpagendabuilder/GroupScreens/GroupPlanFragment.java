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
import com.example.bennytran.yelpagendabuilder.SuggestionsFragment.SuggestionsActivity;
import com.example.bennytran.yelpagendabuilder.models.BlankResult;
import com.example.bennytran.yelpagendabuilder.models.BusinessResult;
import com.example.bennytran.yelpagendabuilder.models.Plan;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;
import com.yelp.clientlib.entities.Business;

import java.util.ArrayList;
import java.util.Set;

// TODO: button updates to update this individual plan

public class GroupPlanFragment extends Fragment {

    public static final String LOG_TAG = "GROUP PLAN FRAGMENT";

    private yelpAgendaBuilder app;
    private ListView mListView;
    public CustomAdapter mAdapter;


    public GroupPlanFragment() {}

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

        String groupPlan = "groupExample";
        //Plan newPlan = yelpAgendaBuilder.getInstance().userPlans.get("groupExample");
        Plan newPlan = new Plan(yelpAgendaBuilder.getInstance().currentStartTime, yelpAgendaBuilder.getInstance().currentEndTime, true);
        mAdapter = new CustomAdapter(getActivity(), newPlan, groupPlan);

        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        mListView = (ListView) view.findViewById(R.id.lvResults);
        mListView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }


    public class CustomAdapter extends BaseAdapter {

        // private Context context;
        private Activity activity;
        private Plan plan;
        private String date;
        private LayoutInflater inflater;

        public CustomAdapter(Activity a, Plan plan, String date) {
            // Log.i(LOG_TAG, "custom adapter is being created");
            // this.context = context;
            this.activity = a;
            this.plan = plan;
            this.date = date;

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
            ImageView deleteButton;
            ImageView blank;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // Log.i(LOG_TAG, "creating views");
            Holder holder = new Holder();
            BusinessResult business = plan.planItems.get(position);
            View row = inflater.inflate(R.layout.custom_list_item, null);


            holder.tvName = (TextView) row.findViewById(R.id.tvRestaurant);
            holder.tvStart = (TextView) row.findViewById(R.id.tvStart);
            holder.tvCategory = (TextView) row.findViewById(R.id.tvCategories);
            holder.background = (ImageView) row.findViewById(R.id.imageBackground);
            holder.blank = (ImageView) row.findViewById(R.id.blank);
            holder.deleteButton = (ImageView) row.findViewById(R.id.btnDelete);

            // changing values
            holder.tvName.setText(plan.planItems.get(position).getName());
            holder.tvStart.setText(plan.timeSlots.get(position).toString());
            holder.tvCategory.setText(plan.planItems.get(position).formatCategories());

            if (business.getName() != "") {
                holder.tvStart.setTextColor(getResources().getColor(R.color.white));
                holder.background.setImageResource(plan.planItems.get(position).getImageID());
                holder.deleteButton.setImageResource(R.mipmap.ic_add_circle_black);

                holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(LOG_TAG, "DELETE BUTTON WAS PRESSED");
                        // yelpAgendaBuilder.getInstance().userPlans.get("groupExample").planItems.set(position, new BlankResult());
                        mAdapter.notifyDataSetChanged();
                    }
                });

                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ItemDetails.class);
                        intent.putExtra("PlanDate", date);
                        intent.putExtra("position", position);
                        intent.putExtra("TITLE", plan.planItems.get(position).getName());
                        startActivity(intent);
                    }
                });
            } else {
                holder.tvStart.setTextColor(getResources().getColor(R.color.black));
                holder.blank.setImageResource(R.mipmap.ic_add_circle_black);

                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), SuggestionsActivity.class);
                        intent.putExtra("PlanDate", date);
                        intent.putExtra("position", position);
                        startActivity(intent);
                    }
                });
            }

            return row;
        }
    }

}
