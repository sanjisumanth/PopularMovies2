package com.example.sumanthkrishna.popularmovies.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONException;

/**
 * Created by sumanthkrishna on 09-Oct-17.
 */

public class MovieSyncIntentService extends IntentService {


    public MovieSyncIntentService() {
        super("MovieSyncIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d("MenuClick", "before sync movies method");

        try {
            MoviesSyncTask.syncMovies(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("MenuClick", "after sync movies method");

    }
}
