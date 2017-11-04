package com.example.sumanthkrishna.popularmovies.utils;

import android.database.Cursor;

import com.example.sumanthkrishna.popularmovies.DetailMovieActivity;
import com.example.sumanthkrishna.popularmovies.SingleTrailer;

import java.util.ArrayList;

/**
 * Created by sumanthkrishna on 21-Oct-17.
 */

public class CursorToTrailerObject {

    public static ArrayList<SingleTrailer> extractTrailerObjectFromCursor(Cursor cursor) {


        if (cursor == null) {

            return null;
        }

        ArrayList<SingleTrailer> trailerArraylist = new ArrayList<>();

        String trailerKey, trailerName;

            if( cursor !=null ) {
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {


                    trailerKey = cursor.getString(DetailMovieActivity.INDEX_MOVIE_TRAILER_MOVIE_KEY);


                    trailerName = cursor.getString(DetailMovieActivity.INDEX_MOVIE_TRAILER_TYPE);

                    SingleTrailer eachTrailer = new SingleTrailer(trailerKey, trailerName);
                    trailerArraylist.add(eachTrailer);
                }


            }




        return trailerArraylist;

    }
}
