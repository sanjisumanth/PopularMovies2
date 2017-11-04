package com.example.sumanthkrishna.popularmovies.utils;

import android.content.ContentValues;
import android.text.TextUtils;

import com.example.sumanthkrishna.popularmovies.data.MoviesContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sumanthkrishna on 15-Oct-17.
 */

class MoviesTrailerExtraction {

    public static ContentValues[] extractMovieRtrailers(String JSONResponse) throws JSONException {
        ContentValues[] movieTrailerValues;



        if (TextUtils.isEmpty(JSONResponse)) {
            return null;
        }

        JSONObject movieTrailerJASONObject = new JSONObject(JSONResponse);
        int movieIdofTrailer =  movieTrailerJASONObject.getInt("id");

//        movieTrailerObject = movieReviewsJASONObject.getJSONObject("videos");

        JSONArray movieTrailerResults = movieTrailerJASONObject.getJSONArray("results");

        movieTrailerValues = new ContentValues[movieTrailerResults.length()];

        for (int i = 0; i < movieTrailerResults.length(); i++) {

            JSONObject eachTrailerObject = movieTrailerResults.getJSONObject(i);
            String eachTrailerID = eachTrailerObject.getString("id");
            String eachTrailerLanguageCode = eachTrailerObject.getString("iso_639_1");
            String eachTrailerCountryCode = eachTrailerObject.getString("iso_3166_1");
            String eachTrailerKeyValue = eachTrailerObject.getString("key");
            String eachTrailerName=eachTrailerObject.getString("name");
            String eachTrailerSite= eachTrailerObject.getString("site");
            String eachTrailerSize= eachTrailerObject.getString("size");
            String eachTrailerType= eachTrailerObject.getString("type");


            ContentValues singleTrailerContenValue = new ContentValues();

            singleTrailerContenValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_ID, eachTrailerID);
            singleTrailerContenValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_MOVIE_NAME, eachTrailerName);
            singleTrailerContenValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_ISO639,eachTrailerLanguageCode);
            singleTrailerContenValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_ISO3166,eachTrailerCountryCode);
            singleTrailerContenValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_MOVIE_KEY,eachTrailerKeyValue);
            singleTrailerContenValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_SITE,eachTrailerSite);
            singleTrailerContenValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_SIZE,eachTrailerSize);
            singleTrailerContenValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_TYPE,eachTrailerType);
            singleTrailerContenValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_MOVIE_id,movieIdofTrailer);

            movieTrailerValues[i] = singleTrailerContenValue;
        }


        return movieTrailerValues;


    }
}
