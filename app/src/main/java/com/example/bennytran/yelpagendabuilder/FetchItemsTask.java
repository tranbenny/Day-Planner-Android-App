package com.example.bennytran.yelpagendabuilder;

import android.os.AsyncTask;
import android.util.Log;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.SearchResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Response;

/**
 * Created by vivianso on 2/19/16.
 */
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
        searchParams.put("term", "food");
        searchParams.put("limit", "3");

        // locale params
        searchParams.put("lang", "fr");

        Call<SearchResponse> call = yelpAPI.search("San Francisco", searchParams);
        try {
            Response<SearchResponse> response = call.execute();
            Log.i("JSON Response", response.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("ERROR", "getting IO exception");
        }


        return null;
    }
}
