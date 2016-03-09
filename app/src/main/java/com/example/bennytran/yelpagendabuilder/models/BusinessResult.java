package com.example.bennytran.yelpagendabuilder.models;

// model for storing business information

import java.util.ArrayList;

public class BusinessResult {

    private String name;
    private String phone_number;
    private double rating;
    private String url;

    private ArrayList<String> categories;

    private String address;

    public BusinessResult(String name, String phone_number, double rating, String url, String address,
    ArrayList<String> categories) {
        this.name = name;
        this.phone_number = phone_number;
        this.rating = rating;
        this.url = url;
        this.address = address;

        this.categories = categories;
    }

    public String getName() { return this.name; }
    public String getNumber() { return this.phone_number; }
    public double getRating() { return this.rating; }
    public String getURL() { return this.url; }
    public String getAddress() { return this.address; }
    public ArrayList<String> getCategories() { return this.categories; }



}
