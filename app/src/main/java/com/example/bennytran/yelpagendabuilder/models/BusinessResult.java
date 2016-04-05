package com.example.bennytran.yelpagendabuilder.models;

// model for storing business information
// can't get boolean value to return

import com.example.bennytran.yelpagendabuilder.FirebaseModels.FirebaseBusiness;
import com.example.bennytran.yelpagendabuilder.Util.Constants;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BusinessResult {

    private String name;
    private String phone_number;
    private double rating;
    private String url;
    private String location;
    private String term;

    private ArrayList<String> categories;
    private int imageID;

    public BusinessResult(String name, String phone_number, double rating, String url,
    ArrayList<String> categories, String location, String type) {
        this.name = name;
        this.phone_number = phone_number;
        this.rating = rating;
        this.url = url;
        this.categories = categories;
        this.location = location;
        this.term = type;
        chooseImage();

    }

    public BusinessResult(FirebaseBusiness result) {
        this.name = result.getName();
        this.rating = result.getAvgRating();
        this.categories = new ArrayList<String>(Arrays.asList(result.getCategories().split(",")));
        this.url = result.getUrl();
        this.phone_number = result.getPhoneNumber();
        this.term = result.getType();
        chooseImage();

    }

    public String getName() { return this.name; }
    public String getNumber() { return this.phone_number; }
    public double getRating() { return this.rating; }
    public String getURL() { return this.url; }
    public String getLocation() { return this.location; }
    public ArrayList<String> getCategories() { return this.categories; }
    public int getImageID() { return this.imageID; }
    public String getType() { return this.term; }


    // returns a formatted string for displaying categories on view
    public String formatCategories() {
        String result = "";
        if (this.categories.size() > 0) {
            for (int i = 0; i < this.categories.size() - 1; i++) {
                result = result + this.categories.get(i) + ", ";
            }
            result += this.categories.get(this.categories.size() - 1);
        }
        return result;
    }

    // chooses a random image from the correct category depending on the search term
    private void chooseImage() {
        switch(term) {
            case "breakfast and brunch":
                this.imageID = getRandom(Constants.breakfastImages);
                break;
            case "lunch":
                this.imageID = getRandom(Constants.foodImages);
                break;
            case "dinner":
                this.imageID = getRandom(Constants.foodImages);
                break;
            case "active things":
                this.imageID = getRandom(Constants.activeImages);
                break;
            case "night life":
                this.imageID = getRandom(Constants.nightlifeImages);
                break;
            case "shopping":
                this.imageID = getRandom(Constants.shoppingImages);
                break;
            case "coffee and desserts":
                this.imageID = getRandom(Constants.coffeeDessertImages);
                break;
        }
    }

    private int getRandom(int[] array) {
        Random random = new Random();
        int randomIndex = random.nextInt(array.length);
        return array[randomIndex];
    }


}
