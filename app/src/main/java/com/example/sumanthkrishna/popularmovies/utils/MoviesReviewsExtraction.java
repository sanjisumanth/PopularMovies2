package com.example.sumanthkrishna.popularmovies.utils;

import android.content.ContentValues;
import android.text.TextUtils;
import android.util.Log;

import com.example.sumanthkrishna.popularmovies.data.MoviesContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sumanthkrishna on 15-Oct-17.
 */

class MoviesReviewsExtraction {


    public static ContentValues[] extractMovieReviews(String JSONResponse) throws JSONException {
        ContentValues[] movieReviewsValues;


        Log.d("JSONResponse",JSONResponse);


        if (TextUtils.isEmpty(JSONResponse)) {
            return null;
        }

        JSONObject movieReviewsJASONObject = new JSONObject(JSONResponse);

        int movieIdofReview =  movieReviewsJASONObject.getInt("id");

//        movieReviewsObject = movieReviewsJASONObject.getJSONObject("reviews");

        JSONArray movieReviewsResults = movieReviewsJASONObject.getJSONArray("results");

        movieReviewsValues = new ContentValues[movieReviewsResults.length()];

        for (int i = 0; i < movieReviewsResults.length(); i++) {

            JSONObject eachReviewObject = movieReviewsResults.getJSONObject(i);
            String eachReviewAuthorName = eachReviewObject.getString("author");
            String eachReviewContent = eachReviewObject.getString("content");
            String filteredReviewContentString = eachReviewContent.replaceAll("\"(\\b[^\"]+|\\s+)?\"(\\b[^\"]+\\b)?\"([^\"]+\\b|\\s+)?\"","\"$1$2$3\"");
            String eachReviewId =  eachReviewObject.getString("id");

            ContentValues singleReviewContenValue = new ContentValues();

            singleReviewContenValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_AUTHOR, eachReviewAuthorName);
            singleReviewContenValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_CONTENT, filteredReviewContentString);
            singleReviewContenValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_MOVIE_ID,movieIdofReview);
            singleReviewContenValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_ID,eachReviewId);



            Log.d("Review values",eachReviewAuthorName+eachReviewContent);
            movieReviewsValues[i] = singleReviewContenValue;
        }


        return movieReviewsValues;


    }






}
