package com.example.bennytran.yelpagendabuilder;
// async task for fetching yelp business information
// types of api calls: breakfast, lunch, happy hour, nightlife, activities


import android.content.Context;
import android.content.SharedPreferences;
import android.media.audiofx.BassBoost;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

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

    public static final String LOG_TAG = "FETCH_ITEMS_TASK";
    // Context mContext;


    public FetchItemsTask() {
        // mContext = context;
        // SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
        // boolean syncConnPref = sharedPref.getBoolean("bars", false);

        // Log.i(LOG_TAG, "" + syncConnPref);
        Log.i(LOG_TAG, "task was created");

    }

    /*
    protected void onPreExecute() {
        super.onPreExecute();
        // SharedPreferences sharedPref = getSharedPreferences(<SharedP)
    }*/

    @Override
    protected Void doInBackground(Void... params) {
        api_credentials credentials = new api_credentials();
        String consumerKey = credentials.getKey();
        String consumerSecret = credentials.getConsumerSecret();
        String token = credentials.getToken();
        String tokenSecret = credentials.getTokenSecret();

        YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
        YelpAPI yelpAPI = apiFactory.createAPI();


        Map<String, String> searchParams = new HashMap<String, String>();

        // general params
        searchParams.put("term", "food");
        

        // locale params
        searchParams.put("lang", "fr");

        Call<SearchResponse> call = yelpAPI.search("Seattle", searchParams);
        try {
            Response<SearchResponse> response = call.execute();
            // Log.i("JSON Response", response.body().toString());
            SearchResponse results = response.body();
            ArrayList<Business> businesses = results.businesses();
            // Log.i("SIZE", "" + businesses.size());
            for (Business bus: businesses) {
                // Log.i("NAME", bus.name());
                yelpAgendaBuilder.getInstance().restaurants.add(bus.name());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(LOG_TAG, "getting IO exception");
            Log.i(LOG_TAG, "check if phone has network connection");
        }


        return null;
    }

    // TODO: create a notification that the result is done loading
    // makes toast for now
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // Toast finishedToast = Toast.makeText(this.mContext, "finished api call", Toast.LENGTH_LONG);
        // finishedToast.show();
    }

}
