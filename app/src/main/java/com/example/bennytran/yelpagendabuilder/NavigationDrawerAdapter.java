package com.example.bennytran.yelpagendabuilder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

// custom adapter for linking pages to navigation drawer

public class NavigationDrawerAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<String> pages;
    private LayoutInflater inflater;

    public NavigationDrawerAdapter(Activity activity, ArrayList<String> pages) {
        this.activity = activity;
        this.pages = pages;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.pages.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
