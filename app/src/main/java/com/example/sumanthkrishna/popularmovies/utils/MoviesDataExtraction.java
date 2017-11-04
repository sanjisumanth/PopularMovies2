package com.example.sumanthkrishna.popularmovies.utils;

import android.content.ContentValues;
import android.text.TextUtils;
import android.util.Log;

import com.example.sumanthkrishna.popularmovies.data.MoviesContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Utility method to parse the JSCON results and return the Contentvalues Array to be used by Content
 * provider.
 */

class MoviesDataExtraction {


    /**
     * Reads the Json response from the HttpUrl connection and creates List of Single Movie objects.
     *
     * @param JSONResponse The resonponse from the url connection in the string form.
     */

        public static ContentValues[] extractJSONResponseData(String JSONResponse) throws JSONException {

            JSONArray movieArray;


            ContentValues[] movieContentValues;

            if (TextUtils.isEmpty(JSONResponse)) {
                return null;
            }


            JSONObject movieJASONObject = new JSONObject(JSONResponse);
            movieArray = movieJASONObject.getJSONArray("results");
            movieContentValues = new ContentValues[movieArray.length()];

            int moviesArraylength =  movieArray.length();
            String stringmoviesArray = String.valueOf(moviesArraylength);

            Log.d("MoviesExtraction " ," movies method" + stringmoviesArray);
            for (int i = 0; i < movieArray.length(); i++) {

                JSONObject eachMovie = movieArray.getJSONObject(i);
                int movieIdString = eachMovie.getInt("id");
                String movieUserRating = Double.toString(eachMovie.getDouble("vote_average"));
                String movieOriginalTitle = eachMovie.getString("original_title");
                String movieSynopsis = eachMovie.getString("overview");
                String moviePoster = eachMovie.getString("poster_path");

                String movieVoteCount = Integer.toString(eachMovie.getInt("vote_count"));
                String movieReleaseDate = eachMovie.getString("release_date");

                ContentValues singleMovieContentValue = new ContentValues();
                singleMovieContentValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_ID, String.valueOf(movieIdString));
                singleMovieContentValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_RATING, movieUserRating);
                singleMovieContentValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_ORIGINAL_TITLE, movieOriginalTitle);
                singleMovieContentValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_SYNOPSIS, movieSynopsis);
                singleMovieContentValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_POSTER_PATH, moviePoster);

                singleMovieContentValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_VOTE_COUNT, movieVoteCount);
                singleMovieContentValue.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_RELEASE_DATE, movieReleaseDate);


                movieContentValues[i] = singleMovieContentValue;

                Log.d("Movies value of i " , String.valueOf(i));
            }
//            Log.d("Movies " , Arrays.toString(movieContentValues));
            return movieContentValues;




        }

    }
