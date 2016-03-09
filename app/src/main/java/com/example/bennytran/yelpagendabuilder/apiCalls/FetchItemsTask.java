package com.example.bennytran.yelpagendabuilder.apiCalls;
// async task for fetching yelp business information
// types of api calls: breakfast, lunch, happy hour, nightlife, activities


import android.os.AsyncTask;
import android.util.Log;

import com.example.bennytran.yelpagendabuilder.models.BusinessResult;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;
import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.Category;
import com.yelp.clientlib.entities.SearchResponse;

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

        Map<String, String> searchParams = new HashMap<>();
        searchParams.put("term", "food");
        searchParams.put("lang", "fr");

        Call<SearchResponse> call = yelpAPI.search("Seattle", searchParams);


        try {
            Response<SearchResponse> response = call.execute();
            SearchResponse results = response.body();
            ArrayList<Business> businesses = results.businesses();
            for (Business business: businesses) {

                String name = business.name();
                String phoneNumber = business.phone();
                double rating = business.rating();
                String url = business.url();
                String address = business.location().address().get(0);

                ArrayList<String> categories = new ArrayList<String>();
                ArrayList<Category> categoryNames = business.categories();
                for (Category c: categoryNames) {
                    String type = c.name();
                    categories.add(type);
                }

                BusinessResult result = new BusinessResult(name, phoneNumber, rating, url, address, categories);
                yelpAgendaBuilder.getInstance().restaurants.add(business.name());
                yelpAgendaBuilder.getInstance().results.add(result);
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
        Log.i(LOG_TAG, "finished task");
        Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().restaurants.toString());

        // Toast finishedToast = Toast.makeText(this.mContext, "finished api call", Toast.LENGTH_LONG);
        // finishedToast.show();
    }

}
