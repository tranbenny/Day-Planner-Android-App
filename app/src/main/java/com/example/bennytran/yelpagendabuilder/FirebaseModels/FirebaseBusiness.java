package com.example.bennytran.yelpagendabuilder.FirebaseModels;


public class FirebaseBusiness {

    private String name;
    private String categories;
    private double avgRating;
    private String phoneNumber;
    private String url;
    private String location;
    private String type;


    public FirebaseBusiness() {}

    public FirebaseBusiness(String name, String categories, double avg_rating, String phoneNumber, String url, String location,
    String type) {
        this.name = name;
        this.categories = categories;
        this.avgRating = avg_rating;
        this.phoneNumber = phoneNumber;
        this.url = url;
        this.location = location;
        this.type = type;
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

    public String getType() { return type; }

}
