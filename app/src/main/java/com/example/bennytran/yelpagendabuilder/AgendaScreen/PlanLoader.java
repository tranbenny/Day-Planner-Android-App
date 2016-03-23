package com.example.bennytran.yelpagendabuilder.AgendaScreen;


import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.bennytran.yelpagendabuilder.models.Plan;
import com.example.bennytran.yelpagendabuilder.models.Time;
import com.example.bennytran.yelpagendabuilder.yelpAgendaBuilder;

public class PlanLoader extends AsyncTaskLoader {

    public PlanLoader(Context context) {
        super(context);
    }

    @Override
    public Object loadInBackground() {

        Time startTime = yelpAgendaBuilder.getInstance().currentStartTime;
        Time endTime = yelpAgendaBuilder.getInstance().currentEndTime;
        Plan newPlan = new Plan(startTime, endTime, false);
        return newPlan;
    }
}
