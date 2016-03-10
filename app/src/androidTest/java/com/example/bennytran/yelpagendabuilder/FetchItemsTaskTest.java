package com.example.bennytran.yelpagendabuilder;


import android.test.ApplicationTestCase;

import com.example.bennytran.yelpagendabuilder.apiCalls.FetchItemsTask;
import com.example.bennytran.yelpagendabuilder.models.BusinessResult;

import java.util.ArrayList;

public class FetchItemsTaskTest extends ApplicationTestCase {

    private yelpAgendaBuilder app = new yelpAgendaBuilder();
    private FetchItemsTask task;


    public FetchItemsTaskTest(Class applicationClass) {
        super(applicationClass);
    }

    protected void setUp() {
        this.task = new FetchItemsTask();
    }

    public void testFoodResults() {
        task.execute();
        assertFalse(app.restaurants.isEmpty());
    }
}
