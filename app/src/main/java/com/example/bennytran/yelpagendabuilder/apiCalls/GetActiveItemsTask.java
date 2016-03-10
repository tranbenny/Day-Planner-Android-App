package com.example.bennytran.yelpagendabuilder.apiCalls;


import android.os.AsyncTask;
import android.util.Log;

import com.example.bennytran.yelpagendabuilder.models.BusinessResult;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;
import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.Category;
import com.yelp.clientlib.entities.SearchResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class GetActiveItemsTask extends AsyncTask<Void, Void, Void> {

    private static final String LOG_TAG = "GET ACTIVE ITEMS TASK";

    public GetActiveItemsTask() {}

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
        searchParams.put("term", "hiking");
        searchParams.put("lang", "fr");

        Call<SearchResponse> call = yelpAPI.search("Seattle", searchParams);
        Callback<SearchResponse> callback = new Callback<SearchResponse>() {
            @Override
            public void onResponse(Response<SearchResponse> response, Retrofit retrofit) {
                SearchResponse results = response.body();
                ArrayList<Business> businesses = results.businesses();

            }

            @Override
            public void onFailure(Throwable t) {
                Log.i(LOG_TAG, "response error occured");
            }
        };
        call.enqueue(callback);


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
    }
}
