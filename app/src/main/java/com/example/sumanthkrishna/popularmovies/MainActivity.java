package com.example.sumanthkrishna.popularmovies;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.sumanthkrishna.popularmovies.data.MoviesContract;
import com.example.sumanthkrishna.popularmovies.sync.MoviesSyncUtils;
import com.example.sumanthkrishna.popularmovies.utils.CursorToMovieObject;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    private static final Uri topRatedUri = MoviesContract.Movie_Entry.CONTENT_URI_TOP_RATED;
    private static final Uri favoriteUri = MoviesContract.Movie_Entry.CONTENT_URI_FAVORITE;
    private static final Uri mostPopularUri = MoviesContract.Movie_Entry.CONTENT_URI_MOST_POPULAR;
    private static final Uri nowPlayingUri = MoviesContract.Movie_Entry.CONTENT_URI_NOW_PLAYING;

    private ArrayList<SingleMovie> movieList = new ArrayList<>();

    private String sortOrder = "nowPlaying";
    String lastSortedChoice;
    private GridView movieView;
    private int mpos;



    private MovieAdapter ma;
    private static final int LOADER_MOVIES = 8;


    private static final String[] MOVIE_PROJECTION = {

            MoviesContract.Movie_Entry.COLUMN_MOVIE_ID,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_ORIGINAL_TITLE,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_RELEASE_DATE,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_RATING,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_SYNOPSIS,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_POSTER_PATH,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_VOTE_COUNT,


    };
    private static final String[] MOVIE_PROJECTION_FAVORITE = {

            MoviesContract.Movie_Entry.COLUMN_MOVIE_ID,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_ORIGINAL_TITLE,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_RELEASE_DATE,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_RATING,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_SYNOPSIS,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_POSTER_PATH,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_VOTE_COUNT,
            MoviesContract.Movie_Entry.COLUMN_MOVIE_FAVORITE_MARK


    };


    public static final int INDEX_MOVIE_ID = 0;
    public static final int INDEX_MOVIE_ORIGINAL_TITLE = 1;
    public static final int INDEX_MOVIE_RELEASE_DATE = 2;
    public static final int INDEX_MOVIE_RATING = 3;
    public static final int INDEX_MOVIE_SYNOPSIS = 4;
    public static final int INDEX_MOVIE_POSTER_PATH = 5;
    public static final int INDEX_MOVIE_VOTE_COUNT = 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        movieView = (GridView) findViewById(R.id.grid_layout_view);


        MoviesSyncUtils.startMoviesSync(this);

        if (savedInstanceState != null) {

            lastSortedChoice = savedInstanceState.getString("sortSelected");
             mpos = savedInstanceState.getInt("position");
            switchToPreviousState(lastSortedChoice,mpos);

        } else {
            loadSelectedMovies(nowPlayingUri);
            ma = new MovieAdapter(this);
            movieView.setAdapter(ma);
           Log.d("Mainactivity", "preference value " + sortOrder);
        }


        movieView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DetailMovieActivity.class);
                SingleMovie movieClicked = movieList.get(i);
                intent.putExtra("MovieClicked", movieClicked);
//                bundle.putParcelableArrayList("singleMovie", movieList);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("movieInstanceList", movieList);
        outState.putString("sortSelected", sortOrder);
        int index = movieView.getFirstVisiblePosition();
        outState.putInt("position",index);
    }

    /***************************************Menu Implementations******************************************************************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater minflater = getMenuInflater();
        minflater.inflate(R.menu.settings, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.topRatedMovie: {
                this.sortOrder = "topRated";
                Log.d("Mainactivity item", "preference value " + sortOrder);
                loadSelectedMovies(topRatedUri);

                break;
            }


            case R.id.popularMovie: {
                this.sortOrder = "mostPopular";
                loadSelectedMovies(mostPopularUri);

                break;

            }

            case R.id.favoriteMovie: {
                this.sortOrder = "favorite";
                loadSelectedMovies(favoriteUri);

                break;

            }

        }
        return super.onOptionsItemSelected(item);
    }

    private void loadSelectedMovies(Uri selectedChoice) {


        Bundle selctedBundle = new Bundle();
        selctedBundle.putString("Query String", selectedChoice.toString());

        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<Cursor> cursorLoader = loaderManager.getLoader(LOADER_MOVIES);

        if (cursorLoader == null) {

            loaderManager.initLoader(LOADER_MOVIES, selctedBundle, MainActivity.this);


        } else {

            loaderManager.restartLoader(LOADER_MOVIES, selctedBundle, MainActivity.this);
        }


    }


    /*********************************************End of Menu Implementataion************************************************************************/


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String selectedChoice = args.getString("Query String");


        String[] PROJECTION = MOVIE_PROJECTION;

        if (selectedChoice == null || TextUtils.isEmpty(selectedChoice)) {

            return null;
        }

        if (selectedChoice.equals(favoriteUri.toString())) {

            PROJECTION = MOVIE_PROJECTION_FAVORITE;
        }


        Uri uri = Uri.parse(selectedChoice);


        return new CursorLoader(
                this,
                uri
                , PROJECTION
                , null, null, null);


    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        Log.d("Cursor Object", DatabaseUtils.dumpCursorToString(data));

        if (data.getColumnCount() == 8 && data == null) {

            Toast.makeText(this, "Favorites List is Empty", Toast.LENGTH_LONG);
        }


        ma.swapCursor(data);


        movieList = cursorToArrayList(data);


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        ma.swapCursor(null);
    }

    private ArrayList<SingleMovie> cursorToArrayList(Cursor cursor) {

        ArrayList<SingleMovie> listOfMovies = new ArrayList<>();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            listOfMovies.add(CursorToMovieObject.extractMovieObjectFromCursor(cursor));
        }
        return listOfMovies;
    }

    public void switchToPreviousState(String selectedChoice,int pos) {

        switch (selectedChoice){

            case "topRated":{
                reloadMoviesOnChangeState(topRatedUri,pos);

               break;
            }

            case "mostPopular": {
                reloadMoviesOnChangeState(mostPopularUri,pos);
                break;
            }

            case "favorite":{
                reloadMoviesOnChangeState(favoriteUri,pos);
                break;
            }

           default:{
                reloadMoviesOnChangeState(nowPlayingUri,pos);
                break;
            }
        }
//        if (selectedChoice.equals("topRated")) {
//            reloadMoviesOnChangeState(topRatedUri);
//            Log.d("Mainactivity top", "preference value " + sortOrder);
//        }
//        if (selectedChoice.equals("mostPopular")) {
//            reloadMoviesOnChangeState(mostPopularUri);
//        }
//
//        if (selectedChoice.equals("favorite")) {
//            reloadMoviesOnChangeState(favoriteUri);
//        }
//
//        if (selectedChoice.equals("nowPlaying")) {
//            reloadMoviesOnChangeState(nowPlayingUri);
//
//        }

    }

    public void reloadMoviesOnChangeState(Uri uri,int pos) {
        Bundle selctedBundle = new Bundle();
        selctedBundle.putString("Query String", uri.toString());
        getSupportLoaderManager().restartLoader(LOADER_MOVIES, selctedBundle, MainActivity.this);
        this.sortOrder = lastSortedChoice;
        ma = new MovieAdapter(this);
        movieView.setAdapter(ma);
        movieView.smoothScrollToPosition(pos);

    }


}


