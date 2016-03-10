package com.example.bennytran.yelpagendabuilder.SettingsScreen;


import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.bennytran.yelpagendabuilder.R;

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

        String childText = (String) getChild(groupPosition, childPosition);
        Log.i(LOG_TAG, childText);


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.preferences_list_item, null);
        }
        TextView txtListChild = (TextView) convertView.findViewById(R.id.tvPreferenceListItem);
        txtListChild.setText(childText);

        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
