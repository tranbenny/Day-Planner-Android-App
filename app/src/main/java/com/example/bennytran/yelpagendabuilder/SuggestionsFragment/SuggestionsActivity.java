package com.example.bennytran.yelpagendabuilder.SuggestionsFragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bennytran.yelpagendabuilder.R;

public class SuggestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        int position = getIntent().getIntExtra("position", 0);
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);

        SuggestionsFragment fragment = new SuggestionsFragment();
        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction().add(R.id.suggestionsContainer, fragment)
                .addToBackStack(null).commit();

    }
}
