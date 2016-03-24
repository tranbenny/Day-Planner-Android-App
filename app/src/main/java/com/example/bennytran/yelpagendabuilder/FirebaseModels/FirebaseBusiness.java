package com.example.bennytran.yelpagendabuilder.FirebaseModels;


public class FirebaseBusiness {

    private String name;
    private String categories;
    private double avgRating;
    private String phoneNumber;
    private String url;
    private String location;


    public FirebaseBusiness() {}

    public FirebaseBusiness(String name, String categories, double avg_rating, String phoneNumber, String url, String location) {
        this.name = name;
        this.categories = categories;
        this.avgRating = avg_rating;
        this.phoneNumber = phoneNumber;
        this.url = url;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getCategories() {
        return categories;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public String getPhoneNumber() { return phoneNumber;}

    public String getUrl() { return url;}

    public String getLocation() { return location; }

}
