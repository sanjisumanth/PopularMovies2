package com.example.sumanthkrishna.popularmovies.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by sumanthkrishna on 06-Oct-17.
 */

public class MoviesProvider extends ContentProvider {

    private static MoviesDbHelper mHelper;

    private static final UriMatcher sUriMatcher = buildUriMatcher();


    // Matcher code for the Popular Movies Table
    private static final int CODE_MOVIES_POPULAR = 100;

    //Matcher code for the single movies in the Popular Movies table
    private static final int CODE_MOVIES_POPULAR_WITH_MOVIE_ID = 101;

    // Matcher code for the Popular Movies Table
    private static final int CODE_MOVIES_TOP_RATED = 200;

    //Matcher code for the single movies in the Popular Movies table
    private static final int CODE_MOVIES_TOP_RATED_WITH_MOVIE_ID = 201;

    // Matcher code for the Popular Movies Table
    private static final int CODE_MOVIES_FAVORITE = 300;

    //Matcher code for the single movies in the Popular Movies table
    private static final int CODE_MOVIES_FAVORITE_WITH_MOVIE_ID = 301;

    private static final int CODE_MOVIES_NOW_PLAYING = 400;
    private static final int CODE_MOVIES_NOW_PLAYING_WITH_MOVIE_ID = 401;

    private static final int CODE_MOVIES_REVIEW= 500;
    private static final int CODE_MOVIES_REVIEW_WITH_MOVIE_ID= 501;

    private static final int CODE_MOVIES_TRAILER = 600;
    private static final int CODE_MOVIES_TRAILER_WITH_MOVIE_ID = 601;



    //Uri matcher Class definition

    private static UriMatcher buildUriMatcher() {


        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // Matcher definition for the URI Movies table
        uriMatcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.PATH_MOVIES_MOST_POPULAR, CODE_MOVIES_POPULAR);

        //Matcher definition for the URI with movie date
        uriMatcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.PATH_MOVIES_MOST_POPULAR + "/#", CODE_MOVIES_POPULAR_WITH_MOVIE_ID);

        // Matcher definition for the URI Movies table
        uriMatcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.PATH_MOVIES_TOP_RATED, CODE_MOVIES_TOP_RATED);

        //Matcher definition for the URI with movie date
        uriMatcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.PATH_MOVIES_TOP_RATED + "/#", CODE_MOVIES_TOP_RATED_WITH_MOVIE_ID);

        // Matcher definition for the URI Movies table
        uriMatcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.PATH_MOVIES_FAVORITE, CODE_MOVIES_FAVORITE);

        //Matcher definition for the URI with movie date
        uriMatcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.PATH_MOVIES_FAVORITE + "/#", CODE_MOVIES_FAVORITE_WITH_MOVIE_ID);

        uriMatcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.PATH_MOVIES_NOW_PLAYING, CODE_MOVIES_NOW_PLAYING);

        uriMatcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.PATH_MOVIES_NOW_PLAYING + "/#", CODE_MOVIES_NOW_PLAYING_WITH_MOVIE_ID);

        uriMatcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.PATH_MOVIES_REVIEWS ,CODE_MOVIES_REVIEW);

        uriMatcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.PATH_MOVIES_REVIEWS + "#" ,CODE_MOVIES_REVIEW_WITH_MOVIE_ID);

        uriMatcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.PATH_MOVIES_TRAILER ,CODE_MOVIES_TRAILER);

        uriMatcher.addURI(MoviesContract.CONTENT_AUTHORITY,MoviesContract.PATH_MOVIES_TRAILER + "/#" , CODE_MOVIES_TRAILER_WITH_MOVIE_ID);

        uriMatcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.PATH_MOVIES_FAVORITE , CODE_MOVIES_FAVORITE );

        uriMatcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.Movie_Entry.TABLE_FAVORITE + "/#" , CODE_MOVIES_FAVORITE_WITH_MOVIE_ID);

        return uriMatcher;

    }


    //Content Provider Implementations------------------------------------------------------------------------//

    @Override
    public boolean onCreate() {

        mHelper = new MoviesDbHelper(getContext());

        return false;
    }


    /**
     * Query Method Implementation.This method will return the Cursor object with details about each movie,based on the
     * selection criterion.     *
     *
     * @param uri           : Uri for the Movies Table
     * @param projection    :The value should be null because we need all the data stored for each movie
     * @param selection     : The selection is based on the movie ID
     * @param selectionArgs : Defines the specific columns requested to be returned.
     * @param sortOrder : The order in which the results to be sorted
     * @return : Returns the cursor
     */


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor;
        SQLiteDatabase db = mHelper.getReadableDatabase();

        switch (sUriMatcher.match(uri)) {

            //Query to retrieve the most popular movies
            case CODE_MOVIES_POPULAR: {

                cursor = db.query(MoviesContract.Movie_Entry.TABLE_MOST_POPULAR,
                        projection, selection, selectionArgs, null, null, sortOrder);
                break;

            }

            case CODE_MOVIES_POPULAR_WITH_MOVIE_ID: {

                String popularSelection = MoviesContract.Movie_Entry.COLUMN_MOVIE_ID + " = ?";

                cursor = db.query(MoviesContract.Movie_Entry.TABLE_MOST_POPULAR,
                        projection,
                        popularSelection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                break;
            }

            //Query to retrieve the Top rated movies


            case CODE_MOVIES_TOP_RATED: {

                cursor = db.query(MoviesContract.Movie_Entry.TABLE_TOP_RATED, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            }
            case CODE_MOVIES_TOP_RATED_WITH_MOVIE_ID: {

                String topRatedSelection = MoviesContract.Movie_Entry.COLUMN_MOVIE_ID + " = ? ";

                cursor = db.query(MoviesContract.Movie_Entry.TABLE_TOP_RATED
                        , projection
                        , topRatedSelection
                        , selectionArgs
                        , null
                        , null
                        , sortOrder);
                break;

            }

            //Query to retrieve the favorite movies

            case CODE_MOVIES_FAVORITE: {

                cursor = db.query(MoviesContract.Movie_Entry.TABLE_FAVORITE, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            }


            case CODE_MOVIES_FAVORITE_WITH_MOVIE_ID: {


                String favoriteSelection = MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_ID + " = ? ";

                cursor = db.query(MoviesContract.Movie_Entry.TABLE_FAVORITE
                        , projection
                        , favoriteSelection
                        , selectionArgs
                        , null
                        , null
                        , sortOrder);
                break;

            }

            case CODE_MOVIES_NOW_PLAYING: {

                cursor = db.query(MoviesContract.Movie_Entry.TABLE_NOW_PLAYING, projection, selection, selectionArgs, null, null, sortOrder);
                break;


            }

            case CODE_MOVIES_REVIEW : {



                cursor = db.query(MoviesContract.Movie_Entry.TABLE_REVIEWS
                        ,projection
                        ,selection
                        ,selectionArgs
                        ,null
                        ,null
                        ,sortOrder);
                break;



            }

            case CODE_MOVIES_TRAILER : {

                String moviesTrailerSelection = MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_MOVIE_id + " = ? " ;

                cursor = db.query(MoviesContract.Movie_Entry.TABLE_TRAILER
                        ,projection
                        ,moviesTrailerSelection
                        ,selectionArgs
                        ,null
                        ,null
                        ,sortOrder);
                break;



            }







            default: {

                throw new UnsupportedOperationException("Wrong uri " + uri);

            }

        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;



    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {

        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.beginTransaction();

        try {

            switch (sUriMatcher.match(uri)) {

                case CODE_MOVIES_POPULAR: {

                    int rowInserted = 0;

                    for (ContentValues value : values) {

                        long _id = db.insert(MoviesContract.Movie_Entry.TABLE_MOST_POPULAR, null, value);
                        if (_id > 0) {
                            rowInserted++;
                        }
                                          }


                    if (rowInserted > 0) {

                        getContext().getContentResolver().notifyChange(uri, null);
                    }
                    return rowInserted;

                }


                case CODE_MOVIES_TOP_RATED: {
                    int rowInserted = 0;

                    for (ContentValues value : values) {
                        long _IDFav = db.insert(MoviesContract.Movie_Entry.TABLE_TOP_RATED, null, value);
                        if (_IDFav > 0) {

                            rowInserted++;
                        }
                    }


                    if (rowInserted > 0) {

                        getContext().getContentResolver().notifyChange(uri, null);
                    }
                    return rowInserted;

                }

                case CODE_MOVIES_NOW_PLAYING: {
                    int rowInserted = 0;
                    for (ContentValues value : values) {
                        long _IDFav = db.insert(MoviesContract.Movie_Entry.TABLE_NOW_PLAYING, null, value);
                        if (_IDFav > 0) {

                            rowInserted++;
                        }
                    }

                    if (rowInserted > 0) {

                        getContext().getContentResolver().notifyChange(uri, null);
                    }
                    return rowInserted;

                }

                case CODE_MOVIES_REVIEW: {

                    int rowInserted = 0;
                    for(ContentValues value : values ){
                        long _IDFav = db.insert(MoviesContract.Movie_Entry.TABLE_REVIEWS, null, value);
                        if (_IDFav > 0) {

                            rowInserted++;
                        }


                    }
                    if (rowInserted > 0) {

                        getContext().getContentResolver().notifyChange(uri, null);
                    }
                    return rowInserted;

                }

                case CODE_MOVIES_TRAILER: {

                    int rowInserted = 0;
                    for(ContentValues value : values ){
                        long _IDFav = db.insert(MoviesContract.Movie_Entry.TABLE_TRAILER, null, value);
                        if (_IDFav > 0) {

                            rowInserted++;
                        }


                    }
                    if (rowInserted > 0) {

                        getContext().getContentResolver().notifyChange(uri, null);
                    }
                    return rowInserted;

                }



                default: {
                    return super.bulkInsert(uri, values);

                }
            }
        } finally {

            db.setTransactionSuccessful();

            db.endTransaction();

        }


    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        SQLiteDatabase db = mHelper.getWritableDatabase();
        int rowsInserted = 0;

        switch (sUriMatcher.match(uri)) {

            case CODE_MOVIES_FAVORITE: {

                long _ID =  db.insert(MoviesContract.Movie_Entry.TABLE_FAVORITE,null,contentValues);

                if(_ID > 0 ){

                    rowsInserted++;
                }

                if(rowsInserted > 0 ){

                    getContext().getContentResolver().notifyChange(uri,null);
                }

                break;

            }


        }

        return null;
    }

    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {

        /* Users of the delete method will expect the number of rows deleted to be returned. */
        int numRowsDeleted=0;

        /*
         * If we pass null as the selection to SQLiteDatabase#delete, our entire table will be
         * deleted. However, if we do pass null and delete all of the rows in the table, we won't
         * know how many rows were deleted. According to the documentation for SQLiteDatabase,
         * passing "1" for the selection will delete all rows and return the number of rows
         * deleted, which is what the caller of this method expects.
         */

        SQLiteDatabase db = mHelper.getWritableDatabase();

        String movieSelection = MoviesContract.Movie_Entry.COLUMN_MOVIE_ID + " = ? " ;


         switch (sUriMatcher.match(uri)) {

            case CODE_MOVIES_FAVORITE: {
                long _ID = db.delete(MoviesContract.Movie_Entry.TABLE_FAVORITE,
                        movieSelection,
                        selectionArgs);

                if (_ID > 0) {
                   numRowsDeleted++;
                }

                if(numRowsDeleted >0){
                    getContext().getContentResolver().notifyChange(uri,null);
                }

                return numRowsDeleted;

            }



             default: {
                 throw new UnsupportedOperationException("Unknown uri: " + uri);

             }


        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
