package com.example.sumanthkrishna.popularmovies.sync;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.sumanthkrishna.popularmovies.BuildConfig;
import com.example.sumanthkrishna.popularmovies.data.MoviesContract;
import com.example.sumanthkrishna.popularmovies.utils.NetworkUtils;

import org.json.JSONException;

import java.util.Arrays;

/**
 * Created by sumanthkrishna on 07-Oct-17.
 */

class MoviesSyncTask {
    /**
     * Performs network queires,parses the JSON and inserts the movies into our content provider tables.
     */


    private static final String QUERY_URL_POPULAR = "http://api.themoviedb.org/3/movie/popular?api_key=" + BuildConfig.API_KEY ;
    private static final String QUERY_URL_TOP_RATED = "http://api.themoviedb.org/3/movie/top_rated?api_key=" + BuildConfig.API_KEY;
    private static final String QUERY_URL_NOW_PLAYING = "http://api.themoviedb.org/3/movie/now_playing?api_key=" + BuildConfig.API_KEY;



    public static void syncMovies(Context context)  throws JSONException {
        Log.d("MenuClick", "starting of sync utils method");



            ContentValues[] playingContentValues = NetworkUtils.fetchMovieData(QUERY_URL_NOW_PLAYING);
            if( playingContentValues == null ){

                Toast.makeText(context,"CHECK INTERNET CONNECTION",Toast.LENGTH_LONG).show();
                return;
            }

            context.getContentResolver().bulkInsert(MoviesContract.Movie_Entry.CONTENT_URI_NOW_PLAYING, playingContentValues);


            Log.d("content_nowPlaying", Arrays.toString(playingContentValues));

            ContentValues[] popularContentValues = NetworkUtils.fetchMovieData(QUERY_URL_POPULAR);
            context.getContentResolver().bulkInsert(MoviesContract.Movie_Entry.CONTENT_URI_MOST_POPULAR, popularContentValues);
            Log.d(" content_popular", Arrays.toString(popularContentValues));

            ContentValues[] ratedContentValues = NetworkUtils.fetchMovieData(QUERY_URL_TOP_RATED);
            context.getContentResolver().bulkInsert(MoviesContract.Movie_Entry.CONTENT_URI_TOP_RATED, ratedContentValues);
            Log.d(" content_rated", Arrays.toString(playingContentValues));






    }



}



