package com.example.bennytran.yelpagendabuilder.models;


// data structure for storing generated plans
// need to store time + business
// create a constructor to generate a blank plan


import android.util.Log;

import com.example.bennytran.yelpagendabuilder.FirebaseModels.FirebaseBusiness;
import com.example.bennytran.yelpagendabuilder.Util.Constants;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Plan {

    private static final String LOG_TAG = Plan.class.getSimpleName();

    public ArrayList<Time> timeSlots;
    public ArrayList<BusinessResult> planItems;

    private Time startTime;
    private Time endTime;

    private int numberSlots;

    private boolean containsBreakfast;
    private boolean containsLunch;
    private boolean containsDinner;


    public Plan(Time start, Time end, boolean blank) {
        this.startTime = start;
        this.endTime = end;

        this.containsBreakfast = false;
        this.containsLunch = false;
        this.containsDinner = false;

        this.numberSlots = computeRange(this.startTime, this.endTime);
        timeSlots = new ArrayList<Time>();
        createTimeSlots(timeSlots);
        planItems = new ArrayList<BusinessResult>();
        if (!blank) {
            createPlan(planItems);
        } else {
            createBlankPlan(planItems);
        }
    }

    public Plan() {
        timeSlots = new ArrayList<Time>();
        planItems = new ArrayList<BusinessResult>();
    }


    private int computeRange(Time start, Time end) {
        return end.getHour() - start.getHour();
    }

    private void createTimeSlots(ArrayList<Time> timeSlots) {
        timeSlots.add(startTime);
        for (int i = 1; i < this.numberSlots - 1; i++) {
            Time newTime = new Time(startTime, i);
            timeSlots.add(newTime);
        }
        timeSlots.add(endTime);
    }

    private void createPlan(ArrayList<BusinessResult> plan) {
        Random random = new Random();
        for (int i = 0; i < timeSlots.size(); i++) {
            Time time = this.timeSlots.get(i);

            // add in meals
            if (time.inBreakfastTime() && !containsBreakfast) {
                BusinessResult breakfast = yelpAgendaBuilder.getInstance().getMaxResult("breakfast and brunch");
                plan.add(breakfast);
                this.containsBreakfast = true;
            } else if (time.inLunchTime() && !containsLunch) {
                BusinessResult lunch = yelpAgendaBuilder.getInstance().getMaxResult("lunch");
                plan.add(lunch);
                this.containsLunch = true;
            } else if (time.inDinnerTime() && !containsDinner) {
                BusinessResult dinner = yelpAgendaBuilder.getInstance().getMaxResult("dinner");
                plan.add(dinner);
                this.containsDinner = true;
            } else if (time.inNightLife()) {
                BusinessResult party = yelpAgendaBuilder.getInstance().getMaxResult("night life");
                plan.add(party);
            } else {
                // choose randomly from other categories
                // active, shopping, coffee and desserts
                int randomNumber = random.nextInt(3);
                if (randomNumber == 0) {
                    // activity
                    BusinessResult activeThing = yelpAgendaBuilder.getInstance().getRandomResult("active things");
                    plan.add(activeThing);
                } else if (randomNumber == 1) {
                    // shopping
                    BusinessResult shoppingTrip = yelpAgendaBuilder.getInstance().getRandomResult("shopping");
                    plan.add(shoppingTrip);
                } else {
                    // drink + dessert
                    BusinessResult drinkDessert = yelpAgendaBuilder.getInstance().getRandomResult("coffee and desserts");
                    plan.add(drinkDessert);
                }
            }
        }
    }

    private void createBlankPlan(ArrayList<BusinessResult> plan) {
        for (int i = 0; i < timeSlots.size(); i++) {
            plan.add(new BlankResult());
        }
    }

    // create a class to load current plan into firebase backend
    public static HashMap<String, FirebaseBusiness> createFirebaseForm() {
        HashMap<String, FirebaseBusiness> result = new HashMap<String, FirebaseBusiness>();
        Plan currentPlan = yelpAgendaBuilder.getInstance().currentPlan;
        String currentLocation = yelpAgendaBuilder.getInstance().currentLocation;
        ArrayList<Time> times = currentPlan.timeSlots;

        for (int i = 0; i < times.size(); i++) {
            String key = times.get(i).toString();
            BusinessResult business = currentPlan.planItems.get(i);
            FirebaseBusiness formattedBusiness = new FirebaseBusiness(business.getName(), business.formatCategories(),
                    business.getRating(), business.getNumber(), business.getURL(), business.getLocation(), business.getType());
            result.put(key, formattedBusiness);
        }
        return result;
    }

    // construct plan object from firebase backend
    // add plan to user's saved plans
    public static void loadFromFirebase(String planKey) {
        final Plan loadedPlan = new Plan();
        final String key = planKey.replace(":", "/");
        Firebase ref = new Firebase(Constants.getUserPlansURL() + "/" + planKey);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    String time = child.getKey();
                    loadedPlan.timeSlots.add(new Time(time));
                    FirebaseBusiness info = child.getValue(FirebaseBusiness.class);
                    loadedPlan.planItems.add(new BusinessResult(info));
                    // loadedPlan.planItems.add(new BlankResult());
                }
                yelpAgendaBuilder.getInstance().userPlans.put(key, loadedPlan);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(LOG_TAG, firebaseError.getMessage());
            }
        });
    }



}
