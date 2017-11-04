package com.example.sumanthkrishna.popularmovies.sync;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by sumanthkrishna on 16-Oct-17.
 */

public class TrailerSyncUtils {



        public static void startTrailerSync(Context context, String movieID){
            Log.d("MenuClick", "starting of sync utils method");

            Intent intentToSyncTailer =  new Intent(context,TailerSyncIntentService.class);
            Bundle bundle = new Bundle();
            bundle.putString("MovieId",movieID);
            intentToSyncTailer.putExtras(bundle);
            context.startService(intentToSyncTailer);
            Log.d("MenuClick", "after of sync utils method");
        }



    }
