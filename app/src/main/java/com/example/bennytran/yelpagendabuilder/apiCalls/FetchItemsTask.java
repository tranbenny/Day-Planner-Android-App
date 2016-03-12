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
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class FetchItemsTask extends AsyncTask<String, Void, Void> {

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
    protected Void doInBackground(final String... params) {
        final String term = params[0];
        api_credentials credentials = new api_credentials();
        String consumerKey = credentials.getKey();
        String consumerSecret = credentials.getConsumerSecret();
        String token = credentials.getToken();
        String tokenSecret = credentials.getTokenSecret();

        YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
        YelpAPI yelpAPI = apiFactory.createAPI();

        HashMap<String, String> searchParams = new HashMap<>();
        searchParams.put("term", term);
        searchParams.put("lang", "fr");
        addSubCategories(searchParams, term);

        Call<SearchResponse> call = yelpAPI.search("Seattle", searchParams);
        Callback<SearchResponse> callback = new Callback<SearchResponse>() {

            @Override
            public void onResponse(Response<SearchResponse> response, Retrofit retrofit) {
                SearchResponse results = response.body();
                ArrayList<Business> businesses = results.businesses();

                for (Business business: businesses) {
                    String name = business.name();
                    String phoneNumber = business.phone();
                    double rating = business.rating();
                    String url = business.url();

                    ArrayList<String> categories = new ArrayList<String>();
                    ArrayList<Category> categoryNames = business.categories();
                    for (Category c: categoryNames) {
                        String type = c.name();
                        categories.add(type);
                    }

                    BusinessResult result = new BusinessResult(name, phoneNumber, rating, url, categories);
                    yelpAgendaBuilder.getInstance().addCategoryMap(term, name, result);


                }
                Log.i(LOG_TAG, term + " task has finished");

                if (term == "breakfast and brunch") {
                    Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().breakfast.keySet().toString());
                } else if (term == "lunch") {
                    Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().lunch.keySet().toString());
                } else if (term == "dinner") {
                    Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().dinner.keySet().toString());
                } else if (term == "active things") {
                    Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().activeActivities.keySet().toString());
                } else if (term == "night life") {
                    Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().nightLife.keySet().toString());
                } else if (term == "shopping") {
                    Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().shopping.keySet().toString());
                } else if (term == "coffee and desserts") {
                    Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().coffeeDessert.keySet().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i(LOG_TAG, "response error occured");
            }
        };
        call.enqueue(callback);

        /*

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
        }*/


        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // Toast finishedToast = Toast.makeText(this.mContext, "finished api call", Toast.LENGTH_LONG);
        // finishedToast.show();
    }


    private void addSubCategories(HashMap<String, String> params, String term) {
        ArrayList<String> subCategories = null;
        switch(term) {
            case "breakfast and brunch":
                subCategories = CategoryMapping.getInstance().RESTAURANT_CATEGORIES;
                break;
            case "lunch":
                subCategories = CategoryMapping.getInstance().RESTAURANT_CATEGORIES;
                break;
            case "dinner":
                subCategories = CategoryMapping.getInstance().RESTAURANT_CATEGORIES;
                break;
            case "active things":
                subCategories = CategoryMapping.getInstance().ACTIVITY_CATEGORIES;
                break;
            case "night life":
                subCategories = CategoryMapping.getInstance().NIGHTLIFE_CATEGORIES;
                break;
            case "shopping":
                subCategories = CategoryMapping.getInstance().SHOPPING_CATEGORIES;
                break;
            case "coffee and desserts":
                Log.i(LOG_TAG, "got coffee categories");
                subCategories = CategoryMapping.getInstance().DRINK_CATEGORIES;
                break;
        }
        for (String category: subCategories) {
            if (CategoryMapping.getInstance().ALL_PREFERENCES.get(category)) {
                params.put("category_filter", category);
            }
        }
    }

}
