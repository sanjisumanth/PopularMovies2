package com.example.sumanthkrishna.popularmovies.sync;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.sumanthkrishna.popularmovies.BuildConfig;
import com.example.sumanthkrishna.popularmovies.data.MoviesContract;
import com.example.sumanthkrishna.popularmovies.utils.NetworkUtils;

import org.json.JSONException;

import java.util.Arrays;

/**
 * Created by sumanthkrishna on 15-Oct-17.
 */

class TrailerSyncTask {

    private static String movieIDSelected;



    public static void syncTrailer(Context context, String movieID) throws JSONException {

        String QUERY_BASE_URL_REVIEW = "http://api.themoviedb.org/3/movie/ ";
        String QUERY_REVIEW_FOR_MOVIEID = "" + movieID;
        String QUERY_FINAL_URL_TRAILER = "/videos?api_key=" + BuildConfig.API_KEY;

        final String QUERY_URL_TRAILER = QUERY_BASE_URL_REVIEW + QUERY_REVIEW_FOR_MOVIEID + QUERY_FINAL_URL_TRAILER;



        Log.d("QueryStringForTrailer", QUERY_URL_TRAILER);


        try {

            ContentValues[] trailerContentValues = NetworkUtils.fetchTrailer(QUERY_URL_TRAILER);

            if (trailerContentValues != null && trailerContentValues.length != 0) {

                Log.d("Reviwcvalues output ", Arrays.toString(trailerContentValues));

                context.getContentResolver().bulkInsert(MoviesContract.Movie_Entry.CONTENT_URI_TRAILER, trailerContentValues);
                Log.d("Trailer ", Arrays.toString(trailerContentValues));
            }


        } catch (Exception e) {

            e.printStackTrace();
        }


    }
}
