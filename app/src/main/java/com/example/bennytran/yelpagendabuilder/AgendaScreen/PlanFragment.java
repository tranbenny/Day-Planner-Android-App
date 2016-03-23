package com.example.bennytran.yelpagendabuilder.AgendaScreen;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bennytran.yelpagendabuilder.ItemDetailsPage.ItemDetails;
import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.SuggestionsFragment.SuggestionsActivity;
import com.example.bennytran.yelpagendabuilder.apiCalls.CategoryMapping;
import com.example.bennytran.yelpagendabuilder.apiCalls.FetchItemsTask;
import com.example.bennytran.yelpagendabuilder.models.BlankResult;
import com.example.bennytran.yelpagendabuilder.models.BusinessResult;
import com.example.bennytran.yelpagendabuilder.models.Plan;
import com.example.bennytran.yelpagendabuilder.models.Time;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

import java.util.ArrayList;
import java.util.Set;

// this fragment should handle both generated plans and blank plans


public class PlanFragment extends Fragment implements LoaderManager.LoaderCallbacks {

    public static final String LOG_TAG = PlanFragment.class.getSimpleName();

    public CustomAdapter mAdapter;
    private ListView mListView;


    public PlanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    // performs api calls for fetching business data
    private void getData() {
        FetchItemsTask breakfastTask = new FetchItemsTask(this);
        breakfastTask.execute("breakfast and brunch");
        FetchItemsTask lunchTask = new FetchItemsTask(this);
        lunchTask.execute("lunch");
        FetchItemsTask dinnerTask = new FetchItemsTask(this);
        dinnerTask.execute("dinner");

        FetchItemsTask activeTask = new FetchItemsTask(this);
        activeTask.execute("active things");
        FetchItemsTask nightLifeTask = new FetchItemsTask(this);
        nightLifeTask.execute("night life");
        FetchItemsTask shoppingTask = new FetchItemsTask(this);
        shoppingTask.execute("shopping");

        FetchItemsTask coffeeTask = new FetchItemsTask(this);
        coffeeTask.execute("coffee and desserts");
    }


    // get data from singleton class
    // attach data to a custom adapter and inflate layout onto a list view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Plan generatedPlan = new Plan(yelpAgendaBuilder.getInstance().currentStartTime, yelpAgendaBuilder.getInstance().currentEndTime, true);
        String date = yelpAgendaBuilder.getInstance().currentDate;

        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        mListView = (ListView) view.findViewById(R.id.lvResults);
        mAdapter = new CustomAdapter(getActivity(), generatedPlan, date);
        mListView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "ON RESUME WAS CALLED");
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new PlanLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        Plan plan = (Plan) data;
        mAdapter.setNewPlan(plan);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        Plan blankPlan = new Plan(yelpAgendaBuilder.getInstance().currentStartTime, yelpAgendaBuilder.getInstance().currentEndTime, true);
        mAdapter.setNewPlan(blankPlan);
    }

    // adapter class for listview of plan results
    public class CustomAdapter extends BaseAdapter {

        // private Context context;
        private Activity activity;
        private Plan plan;
        private String date;

        private LayoutInflater inflater;

        public CustomAdapter(Activity activity, Plan plan, String date) {
            this.activity = activity;
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
            View row;
            BusinessResult business = plan.planItems.get(position);

            Holder holder = new Holder();
            row = inflater.inflate(R.layout.custom_list_item, null);

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

            // holder.tvCategory.setText("Category");
            if (business.getName() != "") {
                holder.tvStart.setTextColor(getResources().getColor(R.color.white));
                holder.background.setImageResource(plan.planItems.get(position).getImageID());
                holder.deleteButton.setImageResource(R.mipmap.ic_add_circle_black);


                holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(LOG_TAG, "DELETE BUTTON PRESSED");
                        yelpAgendaBuilder.getInstance().currentPlan.planItems.set(position, new BlankResult());
                        mAdapter.notifyDataSetChanged();
                    }
                });


                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(LOG_TAG, "you clicked one of the items");
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
                        Log.i("LOG_TAG", "you clicked blank screen, move to suggestions list");
                        Intent intent = new Intent(getActivity(), SuggestionsActivity.class);
                        intent.putExtra("PlanDate", date);
                        intent.putExtra("position", position);
                        startActivity(intent);
                    }
                });
            }

            return row;
        }

        public void setNewPlan(Plan plan) {
            this.plan = plan;
            mAdapter.notifyDataSetChanged();
        }
    }


}
