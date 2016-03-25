package com.example.bennytran.yelpagendabuilder.Util;


import com.example.bennytran.yelpagendabuilder.R;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

public class Constants {

    public static final String[] SEARCH_TERMS = {"breakfast and brunch", "lunch", "dinner",
    "active things", "night life", "shopping", "coffee and desserts"};


    // firebase routes
    public static final String FIREBASE_BASE_URL = FirebaseURL.URL;
    public static final String USERS_URL = FIREBASE_BASE_URL + "/users";
    public static final String GROUPS_URL = FIREBASE_BASE_URL + "/groups";


    public static String getUserInfoURL() {
        return USERS_URL + "/" + yelpAgendaBuilder.getInstance().user;
    }

    public static String getUserPlansURL() {
        return USERS_URL + "/" + yelpAgendaBuilder.getInstance().user + "/plans";
    }

    public static String getGroupInfoURL(String name) {
        return GROUPS_URL + "/" + name;
    }

    public static String getMessagesURL(String name) {
        return GROUPS_URL + "/" + name + "/messages";
    }

    // image resources
    // groups: breakfast/brunch, lunch, dinner, active categories, shopping, night life, coffee and desesrts
    public static int[] breakfastImages = {R.drawable.breakfast_1, R.drawable.breakfast_2,
            R.drawable.breakfast_3, R.drawable.breakfast_4};
    public static int[] foodImages = {R.drawable.food_1, R.drawable.food_2, R.drawable.food_3,
            R.drawable.food_4, R.drawable.food_5, R.drawable.food_6, R.drawable.food_7, R.drawable.food_8};
    public static int[] activeImages = {R.drawable.hiking_1, R.drawable.park_1, R.drawable.park_2,
    R.drawable.park_3};
    public static int[] shoppingImages = {R.drawable.shopping_1, R.drawable.shopping_2, R.drawable.shopping_3,
    R.drawable.shopping_4};
    public static int[] nightlifeImages = {R.drawable.bar_1, R.drawable.bar_2, R.drawable.bar_3, R.drawable.night_life_1,
    R.drawable.night_life_2, R.drawable.bar_4, R.drawable.bar_5};
    public static int[] coffeeDessertImages = {R.drawable.coffee_1, R.drawable.ice_cream_1,
            R.drawable.dessert_1, R.drawable.dessert_2, R.drawable.dessert_3};


}
