package com.example.sumanthkrishna.popularmovies.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by sumanthkrishna on 06-Oct-17.
 */

public class MoviesContract {

    public static final String CONTENT_AUTHORITY = "com.example.sumanthkrishna.popularmovies";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MOVIES_TOP_RATED = "moviesTopRated";
    public static final String PATH_MOVIES_FAVORITE = "moviesFavorite";
    public static final String PATH_MOVIES_MOST_POPULAR = "moviesMostPopular";
    public static final String PATH_MOVIES_NOW_PLAYING = "moviesNowPlaying";
    public static final String PATH_MOVIES_REVIEWS = "moviesReviews";
    public static final String PATH_MOVIES_TRAILER = "moviesTrailer";



    public static final class Movie_Entry implements BaseColumns {

        public static final Uri CONTENT_URI_TOP_RATED = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIES_TOP_RATED)
                .build();

        public static final Uri CONTENT_URI_MOST_POPULAR = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIES_MOST_POPULAR)
                .build();

        public static final Uri CONTENT_URI_FAVORITE = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIES_FAVORITE)
                .build();

        public static final Uri CONTENT_URI_NOW_PLAYING = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIES_NOW_PLAYING)
                .build();

        public static final Uri CONTENT_URI_REVIEWS = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIES_REVIEWS)
                .build();

        public static final Uri CONTENT_URI_TRAILER = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIES_TRAILER)
                .build();




        public static final String TABLE_TOP_RATED = "moviesTopRated";



        public static final String TABLE_MOST_POPULAR = "moviesMostPopular";

        public static final String TABLE_NOW_PLAYING = "moviesNowPlaying";

        public static final String TABLE_REVIEWS = "moviesReviews";

        public static final String TABLE_TRAILER = "moviesTrailer";

        public static final String TABLE_FAVORITE = "moviesFavorite";


        public static final String COLUMN_MOVIE_ID = "movieId";
        public static final String COLUMN_MOVIE_RATING = "voteAverage";
        public static final String COLUMN_MOVIE_ORIGINAL_TITLE = "originalTitle";
        public static final String COLUMN_MOVIE_SYNOPSIS = "overview";
        public static final String COLUMN_MOVIE_POSTER_PATH = "posterPath";
        public static final String COLUMN_MOVIE_VOTE_COUNT = "voteCount";
        public static final String COLUMN_MOVIE_RELEASE_DATE = "releaseDate";
        public static final String COLUMN_MOVIE_REVIEW_AUTHOR = "reviewAuthor";
        public static final String COLUMN_MOVIE_REVIEW_CONTENT = "reivewContent";
        public static final String COLUMN_MOVIE_REVIEW_ID = "reviewID";
        public static final String COLUMN_MOVIE_REVIEW_MOVIE_ID = "reviewMovieID";
        public static final String COLUMN_MOVIE_TRAILER_MOVIE_KEY = "trailerKey";
        public static final String COLUMN_MOVIE_TRAILER_MOVIE_NAME = "trailerName";
        public static final String COLUMN_MOVIE_TRAILER_ID = "trailerId";
        public static final String COLUMN_MOVIE_TRAILER_ISO639 = "trailerIso639";
        public static final String COLUMN_MOVIE_TRAILER_ISO3166 = "trailerIso3166";
        public static final String COLUMN_MOVIE_TRAILER_SITE = "trailerSite";
        public static final String COLUMN_MOVIE_TRAILER_SIZE = "trailerSize";
        public static final String COLUMN_MOVIE_TRAILER_TYPE = "trailerType";
        public static final String COLUMN_MOVIE_TRAILER_MOVIE_id = "trailerMovieId";
        public static final String COLUMN_MOVIE_FAVORITE_MARK = "favoriteMark";



    }


}





