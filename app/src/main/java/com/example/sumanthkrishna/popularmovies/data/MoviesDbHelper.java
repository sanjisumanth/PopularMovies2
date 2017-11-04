package com.example.sumanthkrishna.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sumanthkrishna on 06-Oct-17.
 */

class MoviesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";

    private static final int DATABASE_VERSION = 1;



    public MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_MOVIE_TABLE_MOST_POPULAR =

                " CREATE TABLE " +  MoviesContract.Movie_Entry.TABLE_MOST_POPULAR + " ("+
                        MoviesContract.Movie_Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_POSTER_PATH + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_ORIGINAL_TITLE + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_RATING + " REAL NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_SYNOPSIS + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_VOTE_COUNT + " INTEGER NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_RELEASE_DATE + " TEXT NOT NULL ) ";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE_MOST_POPULAR);


        final String SQL_CREATE_MOVIE_TABLE_TOP_RATED =

                " CREATE TABLE " +  MoviesContract.Movie_Entry.TABLE_TOP_RATED + " ("+
                        MoviesContract.Movie_Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_POSTER_PATH + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_ORIGINAL_TITLE + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_RATING + " REAL NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_SYNOPSIS + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_VOTE_COUNT + " INTEGER NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_RELEASE_DATE + " TEXT NOT NULL ) ";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE_TOP_RATED);

        final String SQL_CREATE_MOVIE_TABLE_FAVORITE =
                " CREATE TABLE " +  MoviesContract.Movie_Entry.TABLE_FAVORITE+ " (" +
                        MoviesContract.Movie_Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_ID + " INTEGER UNIQUE ON CONFLICT REPLACE, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_POSTER_PATH + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_ORIGINAL_TITLE + " TEXT NOT NULL, "+
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_RATING + " REAL NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_FAVORITE_MARK + " INTEGER NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_RELEASE_DATE + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_VOTE_COUNT + " INTEGER NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_SYNOPSIS + " TEXT NOT NULL ) " ;

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE_FAVORITE);




        final String SQL_CREATE_MOVIE_TABLE_NOW_PLAYING =

                " CREATE TABLE " +  MoviesContract.Movie_Entry.TABLE_NOW_PLAYING + " (" +
                        MoviesContract.Movie_Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_POSTER_PATH + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_ORIGINAL_TITLE + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_RATING + " REAL NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_SYNOPSIS + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_VOTE_COUNT + " INTEGER NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_RELEASE_DATE + " TEXT NOT NULL ) " ;

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE_NOW_PLAYING);




        final String SQL_CREATE_MOVIE_TABLE_REVIEWS =

                " CREATE TABLE " +  MoviesContract.Movie_Entry.TABLE_REVIEWS + " (" +
                        MoviesContract.Movie_Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_ID + " TEXT UNIQUE ON CONFLICT REPLACE, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_AUTHOR + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_CONTENT + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_MOVIE_ID + " INTEGER NOT NULL ) ";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE_REVIEWS);

        final String SQL_CREATE_MOVIE_TABLE_TRAILER =
                " CREATE TABLE " +  MoviesContract.Movie_Entry.TABLE_TRAILER + " (" +
                        MoviesContract.Movie_Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_ID + " INTEGER UNIQUE ON CONFLICT REPLACE, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_MOVIE_NAME + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_ISO639 + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_ISO3166 + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_MOVIE_KEY + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_SITE + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_TYPE + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_SIZE + " TEXT NOT NULL, " +
                        MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_MOVIE_id + " INTEGER NOT NULL )" ;

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE_TRAILER);





    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il) {

       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.Movie_Entry.TABLE_MOST_POPULAR);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.Movie_Entry.TABLE_TOP_RATED);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.Movie_Entry.TABLE_FAVORITE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.Movie_Entry.TABLE_NOW_PLAYING);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.Movie_Entry.TABLE_REVIEWS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.Movie_Entry.TABLE_TRAILER);




       onCreate(sqLiteDatabase);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys = ON;");
        super.onOpen(db);
    }
}
