package com.example.bennytran.yelpagendabuilder.models;


// write a method to check for the max 24 hours


public class Time implements Comparable {

    private int hour;
    private int minute;

    private String amPm;


    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;

        if (this.hour > 12) {
            this.amPm = "PM";
        } else {
            this.amPm = "AM";
        }
    }

    public Time(Time time, int hours) {
        this.hour = time.getHour() + hours;
        this.minute = time.getMinute();
        if (this.hour > 12) {
            this.amPm = "PM";
        } else {
            this.amPm = "AM";
        }
    }

    @Override
    public int compareTo(Object another) {
        return 0;
    }

    public String toString() {
        return hour + ":" + minute + " " + this.amPm;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public boolean inBreakfastTime() {
        return this.hour >= 6 && this.hour <= 11;
    }

    public boolean inLunchTime() {
        return this.hour >= 12 && this.hour <= 16;
    }

    public boolean inDinnerTime() {
        return this.hour >= 17 || this.hour <= 4;
    }

    public boolean inNightLife() {
        return this.hour >= 22 || this.hour <= 4;
    }

}