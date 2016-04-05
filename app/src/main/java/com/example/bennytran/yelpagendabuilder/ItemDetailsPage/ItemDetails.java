package com.example.bennytran.yelpagendabuilder.ItemDetailsPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.models.BusinessResult;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

// toolbar should be back button, not navigation drawer icon


public class ItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Intent intent = getIntent();
        String name = intent.getStringExtra("TITLE");
        String planDate = intent.getStringExtra("PlanDate");
        int planPosition = intent.getIntExtra("position", 0);

        // load fragment of details
        Bundle bundle = new Bundle();
        bundle.putString("planDate", planDate);
        bundle.putString("name", name);
        bundle.putInt("position", planPosition);

        ItemDetailsFragment detailsFragment = new ItemDetailsFragment();
        detailsFragment.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(R.id.detailContainer, detailsFragment).commit();

    }
}
