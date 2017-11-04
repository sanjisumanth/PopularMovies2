package com.example.sumanthkrishna.popularmovies.sync;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by sumanthkrishna on 09-Oct-17.
 */

public class MoviesSyncUtils {

    public static void startMoviesSync(Context context) {
        Log.d("MenuClick", "starting of sync utils method");

        Intent intentToSyncMovies = new Intent(context, MovieSyncIntentService.class);

        context.startService(intentToSyncMovies);
        Log.d("MenuClick", "after of sync utils method");
    }
}
