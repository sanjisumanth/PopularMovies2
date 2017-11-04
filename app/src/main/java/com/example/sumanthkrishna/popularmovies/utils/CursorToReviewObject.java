package com.example.sumanthkrishna.popularmovies.utils;

import android.database.Cursor;

import com.example.sumanthkrishna.popularmovies.SingleReview;
import com.example.sumanthkrishna.popularmovies.data.MoviesContract;

import java.util.ArrayList;

/**
 * Created by sumanthkrishna on 19-Oct-17.
 */

public class CursorToReviewObject {

    public static ArrayList<SingleReview> extractReviewObjectFromCursor(Cursor cursor) {


        if (cursor == null) {

            return null;
        }

        ArrayList<SingleReview> reviewsArraylist = new ArrayList<>();
        int movieId;
        String reviewAuthor, reviewContent;



            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

                movieId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_MOVIE_ID)));

                reviewAuthor = cursor.getString(cursor.getColumnIndex(MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_AUTHOR));
//                reviewAuthorsList.add(reviewAuthor);

                reviewContent = cursor.getString(cursor.getColumnIndex(MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_CONTENT));
//                reviewContentList.add(reivewContent);
                SingleReview eachReivew = new SingleReview(reviewAuthor, movieId, reviewContent);
                reviewsArraylist.add(eachReivew);
            }



        return reviewsArraylist;

    }
}
