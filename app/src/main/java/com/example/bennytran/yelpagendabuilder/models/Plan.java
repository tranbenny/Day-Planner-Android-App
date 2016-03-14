package com.example.bennytran.yelpagendabuilder.models;


// data structure for storing generated plans
// need to store time + business
// create a constructor to generate a blank plan


import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

import java.util.ArrayList;
import java.util.Random;

public class Plan {

    public ArrayList<Time> timeSlots;
    public ArrayList<BusinessResult> planItems;

    private Time startTime;
    private Time endTime;

    private int numberSlots;

    private boolean containsBreakfast;
    private boolean containsLunch;
    private boolean containsDinner;


    public Plan(Time start, Time end) {
        this.startTime = start;
        this.endTime = end;

        this.containsBreakfast = false;
        this.containsLunch = false;
        this.containsDinner = false;

        this.numberSlots = computeRange(this.startTime, this.endTime);
        timeSlots = new ArrayList<Time>();
        createTimeSlots(timeSlots);
        planItems = new ArrayList<BusinessResult>();
        createPlan(planItems);
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



}
