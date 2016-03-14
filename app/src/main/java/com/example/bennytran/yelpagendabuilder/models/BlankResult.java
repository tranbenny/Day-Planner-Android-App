package com.example.bennytran.yelpagendabuilder.models;


import java.util.ArrayList;

public class BlankResult extends BusinessResult {

    // calls BusinessResult constructor, creates a blank result filler for empty tempaltes
    public BlankResult() {
        super("", "", 0, "", new ArrayList<String>());
    }

    // check if this works
    @Override
    public boolean isBlank() { return true; }
}
