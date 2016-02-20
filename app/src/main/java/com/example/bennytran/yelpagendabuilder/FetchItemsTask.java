package com.example.bennytran.yelpagendabuilder;
// async task for fetching yelp business information
// types of api calls: breakfast, lunch, happy hour, nightlife, activities


import android.os.AsyncTask;
import android.util.Log;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Response;

public class FetchItemsTask extends AsyncTask<Void, Void, Void> {


    @Override
    protected Void doInBackground(Void... params) {
        api_credentials credentials = new api_credentials();
        String consumerKey = credentials.getKey();
        String consumerSecret = credentials.getConsumerSecret();
        String token = credentials.getToken();
        String tokenSecret = credentials.getTokenSecret();

        YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
        YelpAPI yelpAPI = apiFactory.createAPI();


        Map<String, String> searchParams = new HashMap<>();

        // general params
        searchParams.put("term", "attractions");
        

        // locale params
        searchParams.put("lang", "fr");

        Call<SearchResponse> call = yelpAPI.search("Seattle", searchParams);
        try {
            Response<SearchResponse> response = call.execute();
            // Log.i("JSON Response", response.body().toString());
            SearchResponse results = response.body();
            ArrayList<Business> businesses = results.businesses();
            Log.i("SIZE", "" + businesses.size());
            for (Business bus: businesses) {
                Log.i("NAME", bus.name());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("ERROR", "getting IO exception");
        }


        return null;
    }
}
