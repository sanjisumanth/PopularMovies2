package com.example.sumanthkrishna.popularmovies;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.sumanthkrishna.popularmovies.data.MoviesContract;
import com.example.sumanthkrishna.popularmovies.sync.ReviewSyncUtils;
import com.example.sumanthkrishna.popularmovies.sync.TrailerSyncUtils;
import com.example.sumanthkrishna.popularmovies.utils.CursorToReviewObject;
import com.example.sumanthkrishna.popularmovies.utils.CursorToTrailerObject;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.Arrays;


public class DetailMovieActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String[] REVIEW_PROJECTION = {

            MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_AUTHOR,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_CONTENT,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_MOVIE_ID

    };

    private static final String[] TRAILER_PROJECTION = {

            MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_TYPE,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_MOVIE_KEY,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_SITE

    };

    public static final int INDEX_MOVIE_TRAILER_TYPE = 0;
    public static final int INDEX_MOVIE_TRAILER_MOVIE_KEY = 1;



    private static final int LOADER_REVIEWS = 9;
    private static final int LOADER_TRAILER = 10;
    private final ArrayList<SingleMovie> mdetailMovies = new ArrayList<>();
    private ArrayList<SingleReview> mReviewsOfMovie = new ArrayList<>();
    private ArrayList<SingleTrailer> mTrailersOfMovie = new ArrayList<>();

    private String movieIdString;
    private RecyclerMovieAdapter recyclerMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*
           A Reference Variable to hold the reference to Arraylist of Movie objects which are passed by Intent.
         */

        SingleMovie movie;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);
        this.setTitle("Detail Movie ");

        /*
        Extract the details from the Intent
         */
        movie = getIntent().getExtras().getParcelable("MovieClicked");
        int movieId = movie.getMovieId();
        mdetailMovies.add(movie);
        movieIdString = String.valueOf(movieId);



        /*
          Initialise Stetho
         */
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
        Stetho.initializeWithDefaults(this);


       /*
       Start the background Sync of the reviews by passing in the Id of the movie
        */

        ReviewSyncUtils.startReviewSync(this, movieIdString);
        TrailerSyncUtils.startTrailerSync(this,movieIdString);


        /*
        Initiate the Loader to load the reviews from the Content provider
         */
        getSupportLoaderManager().initLoader(LOADER_TRAILER, null, this);
        getSupportLoaderManager().initLoader(LOADER_REVIEWS, null, this);



        /*
        Get the reference of the Recycler view and attach the adapter
         */

        RecyclerView movieDetailRecyclerVew = (RecyclerView) findViewById(R.id.rvMovieDetails);

        recyclerMovieAdapter = new RecyclerMovieAdapter(mdetailMovies, mReviewsOfMovie,mTrailersOfMovie,this);

        movieDetailRecyclerVew.setAdapter(recyclerMovieAdapter);
        movieDetailRecyclerVew.setLayoutManager(new LinearLayoutManager(this));

        movieDetailRecyclerVew.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));









    }





    /*                                            Implementation for Cursor Loader                                                              */

    /**
     * @param id   - Id of the loader which loads the reviews and trailers from the respective tables
     * @param args - Extras passed to the Loader
     * @return
     */

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        CursorLoader cursorLoader;

        switch (id){

            case LOADER_REVIEWS:{
                String selection = MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_MOVIE_ID + "  = ? ";

                String[] selctionArgs = new String[]{movieIdString};

                Log.d("InCursorLoader", "before cursor loader instantiation" + MoviesContract.Movie_Entry.CONTENT_URI_REVIEWS);

                cursorLoader = new CursorLoader(
                        this,
                        MoviesContract.Movie_Entry.CONTENT_URI_REVIEWS
                        , REVIEW_PROJECTION
                        , selection, selctionArgs, null);


                return cursorLoader;


            }

            case LOADER_TRAILER:{


                String selection = MoviesContract.Movie_Entry.COLUMN_MOVIE_TRAILER_MOVIE_id + "  = ? ";

                String[] selctionArgs = new String[]{movieIdString};


                cursorLoader = new CursorLoader(
                        this,
                        MoviesContract.Movie_Entry.CONTENT_URI_TRAILER
                        , TRAILER_PROJECTION
                        , selection, selctionArgs, null);


                return cursorLoader;
            }
        }

        return null;

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        switch (loader.getId()){

            case LOADER_REVIEWS:{

                Log.d("Cursor Object Deta", DatabaseUtils.dumpCursorToString(data));
                mReviewsOfMovie = CursorToReviewObject.extractReviewObjectFromCursor(data);
                Log.d("Cursor-Reviews of movie", "The array contents " + Arrays.toString(mReviewsOfMovie.toArray()));
                recyclerMovieAdapter.notifyDataSetChanged();
                recyclerMovieAdapter.updateReviews(mReviewsOfMovie);

                break;



            }
            case LOADER_TRAILER:{

                mTrailersOfMovie = CursorToTrailerObject.extractTrailerObjectFromCursor(data);
                recyclerMovieAdapter.notifyDataSetChanged();
                recyclerMovieAdapter.updateVideos(mTrailersOfMovie);
                break;



            }
        }



    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {




    }


}

