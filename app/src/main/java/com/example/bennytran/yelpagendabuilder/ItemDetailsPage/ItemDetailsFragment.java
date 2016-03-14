package com.example.bennytran.yelpagendabuilder.ItemDetailsPage;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
// import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.SuggestionsFragment.SuggestionsFragment;
import com.example.bennytran.yelpagendabuilder.models.BusinessResult;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

import static com.example.bennytran.yelpagendabuilder.R.id.suggestionsContainer;


public class ItemDetailsFragment extends Fragment {


    public ItemDetailsFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);
        String name = getArguments().getString("name");
        String planDate = getArguments().getString("planDate");
        int position = getArguments().getInt("position");

        BusinessResult business = yelpAgendaBuilder.getInstance().userPlans.get(planDate)
                .planItems.get(position);


        TextView tvTitle = (TextView) view.findViewById(R.id.tvBusinessName);
        tvTitle.setText(name);
        TextView tvCategories = (TextView) view.findViewById(R.id.tvCategories);
        tvCategories.setText(business.formatCategories());
        TextView tvRating = (TextView) view.findViewById(R.id.tvRating);
        tvRating.setText("Average Rating: " + business.getRating());

        getFragmentManager().beginTransaction()
                .add(suggestionsContainer, new SuggestionsFragment())
                .addToBackStack(null).commit();

        return view;


    }

}
