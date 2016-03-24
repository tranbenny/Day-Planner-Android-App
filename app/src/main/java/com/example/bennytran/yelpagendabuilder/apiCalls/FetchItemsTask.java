package com.example.bennytran.yelpagendabuilder.apiCalls;
// async task for fetching yelp business information
// types of api calls: breakfast, lunch, happy hour, nightlife, activities


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.example.bennytran.yelpagendabuilder.AgendaScreen.PlanActivity;
import com.example.bennytran.yelpagendabuilder.AgendaScreen.PlanFragment;
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

    public static final String LOG_TAG = FetchItemsTask.class.getSimpleName();

    private PlanFragment mInstance;

    public FetchItemsTask(PlanFragment fragment) {
        Log.i(LOG_TAG, "task was created");
        this.mInstance = fragment;
    }


    @Override
    protected Void doInBackground(final String... params) {
        final String term = params[0];
        api2 credentials = new api2();
        String consumerKey = credentials.getKey();
        String consumerSecret = credentials.getConsumerSecret();
        String token = credentials.getToken();
        String tokenSecret = credentials.getTokenSecret();

        YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
        YelpAPI yelpAPI = apiFactory.createAPI();

        HashMap<String, String> searchParams = new HashMap<>();
        searchParams.put("term", term);
        searchParams.put("lang", "en");
        addSubCategories(searchParams, term);

        Call<SearchResponse> call = yelpAPI.search("Seattle", searchParams);
        Callback<SearchResponse> callback = new Callback<SearchResponse>() {

            @Override
            public void onResponse(Response<SearchResponse> response, Retrofit retrofit) {
                SearchResponse results = response.body();
                ArrayList<Business> businesses = results.businesses();
                String location = yelpAgendaBuilder.getInstance().currentLocation;
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

                    BusinessResult result = new BusinessResult(name, phoneNumber, rating, url, categories, location);
                    yelpAgendaBuilder.getInstance().addCategoryMap(term, name, result);


                }
                Log.i(LOG_TAG, term + " task has finished");

                if (term == "breakfast and brunch") {
                    Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().breakfast.keySet().toString());
                    yelpAgendaBuilder.getInstance().breakfastFinished = true;
                } else if (term == "lunch") {
                    Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().lunch.keySet().toString());
                    yelpAgendaBuilder.getInstance().lunchFinished = true;
                } else if (term == "dinner") {
                    Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().dinner.keySet().toString());
                    yelpAgendaBuilder.getInstance().dinnerFinished = true;
                } else if (term == "active things") {
                    Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().activeActivities.keySet().toString());
                    yelpAgendaBuilder.getInstance().activeFinished = true;
                } else if (term == "night life") {
                    Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().nightLife.keySet().toString());
                    yelpAgendaBuilder.getInstance().nightLifeFinished = true;
                } else if (term == "shopping") {
                    Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().shopping.keySet().toString());
                    yelpAgendaBuilder.getInstance().shoppingFinished = true;
                } else if (term == "coffee and desserts") {
                    Log.i(LOG_TAG, yelpAgendaBuilder.getInstance().coffeeDessert.keySet().toString());
                    yelpAgendaBuilder.getInstance().coffeeDessertFinished = true;
                }

                // check if all calls are finished, then send service
                if (yelpAgendaBuilder.getInstance().isFinished()) {
                    // Toast finishedToast = Toast.makeText(activity, "finished api call", Toast.LENGTH_LONG);
                    // finishedToast.show();
                    // initiate loader here
                    mInstance.getLoaderManager().initLoader(1, null, mInstance).forceLoad();

                }

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
