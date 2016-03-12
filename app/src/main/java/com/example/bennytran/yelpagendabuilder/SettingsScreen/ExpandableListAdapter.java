package com.example.bennytran.yelpagendabuilder.SettingsScreen;


import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.apiCalls.CategoryMapping;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private static final String LOG_TAG = "SETTINGS LIST ADAPTER";

    private Context mContext;
    private List<String> mListDataHeader;
    private HashMap<String, List<String>> mListDataChild;


    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listChildData) {
        this.mContext = context;
        this.mListDataHeader = listDataHeader;
        this.mListDataChild = listChildData;
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

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.preferences_list_group, null);
        }

        TextView tvListHeader = (TextView) convertView.findViewById(R.id.tvListHeader);
        tvListHeader.setText(headerTitle);
        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        View row = convertView;
        Holder holder = null;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.preferences_list_item, null);
            holder = new Holder();
            holder.category = (TextView) row.findViewById(R.id.tvPreferenceListItem);
            holder.checkBox = (CheckBox) row.findViewById(R.id.checkboxCategory);
            row.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
            holder.checkBox.setOnCheckedChangeListener(null);
        }

        holder.checkBox.setFocusable(false);
        holder.category.setText(childText);
        holder.checkBox.setChecked(CategoryMapping.getInstance().ALL_PREFERENCES.get(childText));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    CategoryMapping.getInstance().ALL_PREFERENCES.put(childText, true);
                    Log.i(LOG_TAG, "set to true");
                } else {
                    CategoryMapping.getInstance().ALL_PREFERENCES.put(childText, false);
                    Log.i(LOG_TAG, "set to false");
                }
            }
        });

        return row;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



    static class Holder {
        TextView category;
        CheckBox checkBox;
    }

}
