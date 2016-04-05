package com.example.bennytran.yelpagendabuilder.models;


import java.util.ArrayList;

public class BlankResult extends BusinessResult {

    // calls BusinessResult constructor, creates a blank result filler for empty templates
    public BlankResult() {
        super("", "", 0, "", new ArrayList<String>(), "", "");
    }


}
