package com.example.sumanthkrishna.popularmovies.sync;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by sumanthkrishna on 15-Oct-17.
 */

public class ReviewSyncUtils {

    public static void startReviewSync(Context context,String movieID){
        Log.d("MenuClick", "starting of ReviewSync utils method" + movieID);

        Intent intentToSyncReview =  new Intent(context,ReviewsSyncIntentService.class);
        Bundle bundle = new Bundle();
        bundle.putString("MovieId",movieID);
        Log.d("MovieId","In startReviewSysnc" +
                ""+movieID);
        intentToSyncReview.putExtras(bundle);
        context.startService(intentToSyncReview);
        Log.d("MenuClick", "after of Reviewsync utils method");
    }


}

