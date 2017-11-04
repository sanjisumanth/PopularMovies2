package com.example.sumanthkrishna.popularmovies.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONException;

/**
 * Created by sumanthkrishna on 15-Oct-17.
 */

public class ReviewsSyncIntentService extends IntentService{


    public ReviewsSyncIntentService() {
        super("ReviewsSyncIntentService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        String movieID = intent.getStringExtra("MovieId");

        Log.d("MovieId","In Reviws sync intent service " + movieID);

        try {
            ReviewSyncTask.syncReview(this,movieID);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
