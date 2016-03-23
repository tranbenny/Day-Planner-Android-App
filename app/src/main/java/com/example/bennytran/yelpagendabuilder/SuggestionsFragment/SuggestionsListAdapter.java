package com.example.bennytran.yelpagendabuilder.SuggestionsFragment;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.models.BusinessResult;
import com.example.bennytran.yelpagendabuilder.models.Plan;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;
import com.yelp.clientlib.entities.Business;

import java.util.HashMap;
import java.util.List;

public class SuggestionsListAdapter extends BaseExpandableListAdapter {

    public static final String LOG_TAG = "SUGGESTIONS ADAPTER";


    private Context mContext;
    private List<String> mListDataHeader;
    private HashMap<String, List<String>> mListDataChild;
    private int currentPosition;


    public SuggestionsListAdapter(Context context, List<String> listDataHeader,
                                  HashMap<String, List<String>> listChildData, int currentPosition) {
        this.mContext = context;
        this.mListDataHeader = listDataHeader;
        this.mListDataChild = listChildData;
        this.currentPosition = currentPosition;
    }


    @Override
    public int getGroupCount() {
        return this.mListDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mListDataChild.get(this.mListDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mListDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.mListDataChild.get(this.mListDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    // configure group layout
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.suggestions_list_group, null);
        }

        TextView tvListHeader = (TextView) convertView.findViewById(R.id.tvSuggestionsGroup);
        tvListHeader.setText(headerTitle);

        return convertView;
    }

    // configure item layout
    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        View row = convertView;
        Holder holder = null;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.suggestions_list_item, null);
            holder = new Holder();
            holder.name = (TextView) row.findViewById(R.id.tvSuggestionsItem);
            holder.replaceButton = (Button) row.findViewById(R.id.btnReplace);
            row.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.name.setText(childText);
        // add click listener here to replace plan item with clicked item
        holder.replaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // child Text = clicked text
                String groupTitle = (String) getGroup(groupPosition);
                HashMap<String, BusinessResult> group = null;
                switch(groupTitle) {
                    case "Breakfast/Brunch":
                        group = yelpAgendaBuilder.getInstance().breakfast;
                        break;
                    case "Lunch":
                        group = yelpAgendaBuilder.getInstance().lunch;
                        break;
                    case "Dinner":
                        group = yelpAgendaBuilder.getInstance().dinner;
                        break;
                    case "Activities":
                        group = yelpAgendaBuilder.getInstance().activeActivities;
                        break;
                    case "Shopping":
                        group = yelpAgendaBuilder.getInstance().shopping;
                        break;
                    case "Night Life":
                        group = yelpAgendaBuilder.getInstance().nightLife;
                        break;
                    case "Drinks/Desserts":
                        group = yelpAgendaBuilder.getInstance().coffeeDessert;
                        break;
                }
                BusinessResult newBusiness = group.get(childText);
                Plan currentPlan = yelpAgendaBuilder.getInstance().currentPlan;
                currentPlan.planItems.set(currentPosition, newBusiness);
                Log.i(LOG_TAG, "new item should have been replaced");
            }
        });

        return row;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class Holder {
        TextView name;
        Button replaceButton;
    }
}
