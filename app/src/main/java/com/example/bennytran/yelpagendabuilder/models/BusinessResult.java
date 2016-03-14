package com.example.bennytran.yelpagendabuilder.models;

// model for storing business information
// can't get boolean value to return

import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

import java.util.ArrayList;

public class BusinessResult {

    private String name;
    private String phone_number;
    private double rating;
    private String url;
    public int isBlank; // this field doesnt work?

    private ArrayList<String> categories;
    private int imageID;

    public BusinessResult(String name, String phone_number, double rating, String url,
    ArrayList<String> categories) {
        this.name = name;
        this.phone_number = phone_number;
        this.rating = rating;
        this.url = url;
        this.categories = categories;
        this.isBlank = 0; // doesn't work?

        this.imageID = yelpAgendaBuilder.getInstance().getRandomImage();

    }

    public String getName() { return this.name; }
    public String getNumber() { return this.phone_number; }
    public double getRating() { return this.rating; }
    public String getURL() { return this.url; }
    public ArrayList<String> getCategories() { return this.categories; }
    public int getImageID() { return this.imageID; }


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

    // indicator that result is not blank
    // method doesn't work ?
    public boolean isBlank() {
        return false;
    }

}
