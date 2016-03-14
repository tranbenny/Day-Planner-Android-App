package com.example.bennytran.yelpagendabuilder.SuggestionsFragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
// import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.models.BusinessResult;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class SuggestionsFragment extends Fragment {

    ExpandableListView mListView;
    List<String> groupLabels;
    HashMap<String, List<String>> groupItems;
    int currentPosition;

    public static SuggestionsFragment newInstance(String param1, String param2) {
        SuggestionsFragment fragment = new SuggestionsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // grabs the current position of the plan where item was clicked
        Bundle args = getArguments();
        currentPosition = args.getInt("position", 0);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_suggestions, container, false);
        mListView = (ExpandableListView) view.findViewById(R.id.lvSuggestions);
        // get items
        createSuggestions();
        SuggestionsListAdapter adapter = new SuggestionsListAdapter(getActivity(), groupLabels, groupItems, currentPosition);
        mListView.setAdapter(adapter);
        return view;
    }


    // sets up suggestion groups in groupLabels and groupItems
    private void createSuggestions() {
        groupLabels = new ArrayList<String>(Arrays.asList(
                "Breakfast/Brunch", "Lunch", "Dinner", "Activities", "Shopping",
                "Night Life", "Drinks/Desserts"));

        groupItems = new HashMap<String, List<String>>();
        groupItems.put("Breakfast/Brunch", setUpNames(yelpAgendaBuilder.getInstance().breakfast));
        groupItems.put("Lunch", setUpNames(yelpAgendaBuilder.getInstance().lunch));
        groupItems.put("Dinner", setUpNames(yelpAgendaBuilder.getInstance().dinner));
        groupItems.put("Activities", setUpNames(yelpAgendaBuilder.getInstance().activeActivities));
        groupItems.put("Shopping", setUpNames(yelpAgendaBuilder.getInstance().shopping));
        groupItems.put("Night Life", setUpNames(yelpAgendaBuilder.getInstance().nightLife));
        groupItems.put("Drinks/Desserts", setUpNames(yelpAgendaBuilder.getInstance().coffeeDessert));
    }

    private ArrayList<String> setUpNames(HashMap<String, BusinessResult> names) {
        ArrayList<String> result = new ArrayList<String>();
        result.addAll(names.keySet());
        return result;
    }



}
