package com.example.sumanthkrishna.popularmovies.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import org.json.JSONException;

/**
 * Created by sumanthkrishna on 15-Oct-17.
 */

public class TailerSyncIntentService  extends IntentService{


    public TailerSyncIntentService() {
        super("TrailerSyncIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String movieID = intent.getStringExtra("MovieId");

        try {
            TrailerSyncTask.syncTrailer(this,movieID);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
