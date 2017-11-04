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


class ReviewSyncTask {

    private static String movieIDSelected ;




//    now_playing?api_key="
//
//    https://api.themoviedb.org/3/movie/500/videos?api_key=8ad02d26ab804ff15eb1d58b4c88caf9


    public static void syncReview(Context context, String movieID ) throws JSONException{

         final String QUERY_BASE_URL_REVIEW = "http://api.themoviedb.org/3/movie/ " ;
        final String QUERY_REVIEW_FOR_MOVIEID = "" + movieID;
        final String QUERY_FINAL_URL_REVIEW = "/reviews?api_key=" + BuildConfig.API_KEY;

        Log.d("MovieId","In Review sync Task " + movieID);

        final String  QUERY_URL_REVIEW = QUERY_BASE_URL_REVIEW + QUERY_REVIEW_FOR_MOVIEID + QUERY_FINAL_URL_REVIEW ;

        Log.d("QueryStringForReview",QUERY_URL_REVIEW);

        try {

            ContentValues[] reviewsContentValues = NetworkUtils.fetchReview(QUERY_URL_REVIEW);

            if(reviewsContentValues !=null && reviewsContentValues.length != 0 ){

                Log.d("Reviwcvalues output ", Arrays.toString(reviewsContentValues));

                context.getContentResolver().bulkInsert(MoviesContract.Movie_Entry.CONTENT_URI_REVIEWS, reviewsContentValues);
            }



        } catch (Exception e ){

            e.printStackTrace();
        }








    }
}
