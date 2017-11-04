package com.example.sumanthkrishna.popularmovies.utils;

import android.database.Cursor;
import android.util.Log;

import com.example.sumanthkrishna.popularmovies.MainActivity;
import com.example.sumanthkrishna.popularmovies.MovieAdapter;
import com.example.sumanthkrishna.popularmovies.SingleMovie;

/**
 * Created by sumanthkrishna on 08-Oct-17.
 */

public class CursorToMovieObject {


    public static SingleMovie extractMovieObjectFromCursor(Cursor cursor) {

        Log.d("extract values " ,"inside the cursor to array extract values");

        if(cursor.getColumnCount() == 7) {

            int movieID = Integer.parseInt(cursor.getString(MainActivity.INDEX_MOVIE_ID));
            String originalTitle = cursor.getString(MainActivity.INDEX_MOVIE_ORIGINAL_TITLE);
            String movieRating = cursor.getString(MainActivity.INDEX_MOVIE_RATING);
            String moviePosterPath = cursor.getString(MainActivity.INDEX_MOVIE_POSTER_PATH);
            String movieSynopsis = cursor.getString(MainActivity.INDEX_MOVIE_SYNOPSIS);
            String movieReleseDate = cursor.getString(MainActivity.INDEX_MOVIE_RELEASE_DATE);
            String movieVoteCount = cursor.getString(MainActivity.INDEX_MOVIE_VOTE_COUNT);
            String moviePosterURL = MovieAdapter.POSTER_BASE_URL + moviePosterPath;

            return new SingleMovie(movieID, movieRating, originalTitle, movieSynopsis, moviePosterPath, movieVoteCount, movieReleseDate, moviePosterURL, 0);


        }

        else {
            int movieID = Integer.parseInt(cursor.getString(MainActivity.INDEX_MOVIE_ID));
            String originalTitle = cursor.getString(MainActivity.INDEX_MOVIE_ORIGINAL_TITLE);
            String movieRating = cursor.getString(MainActivity.INDEX_MOVIE_RATING);
            String moviePosterPath = cursor.getString(MainActivity.INDEX_MOVIE_POSTER_PATH);
            String movieSynopsis = cursor.getString(MainActivity.INDEX_MOVIE_SYNOPSIS);
            String movieReleseDate = cursor.getString(MainActivity.INDEX_MOVIE_RELEASE_DATE);
            String movieVoteCount = cursor.getString(MainActivity.INDEX_MOVIE_VOTE_COUNT);
            String moviePosterURL = MovieAdapter.POSTER_BASE_URL + moviePosterPath;

            return new SingleMovie(movieID, movieRating, originalTitle, movieSynopsis, moviePosterPath, movieVoteCount, movieReleseDate, moviePosterURL, 1);

        }




    }

}
