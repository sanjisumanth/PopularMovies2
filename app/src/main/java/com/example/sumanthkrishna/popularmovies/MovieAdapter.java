package com.example.sumanthkrishna.popularmovies;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sumanthkrishna.popularmovies.data.MoviesContract;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Adapter object which attaches the data to the each views.
 */



public class MovieAdapter extends BaseAdapter{

    public static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w500/";


    private Cursor cursor;
    private final Context mcontext;


    private final ArrayList<SingleMovie> movieList = new ArrayList<>();
    public MovieAdapter(Context context){

        mcontext = context;




    }

    void
    swapCursor(Cursor newCursor) {
        cursor = newCursor;
        notifyDataSetChanged();
    }




    @Override
    public int getCount() {
        return (cursor == null) ? 0:cursor.getCount();
    }

    @Override
    public Object getItem(int i) {
        return  movieList.get(i);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }



    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(mcontext).inflate(R.layout.single_image, parent, false);
        }

        cursor.moveToPosition(i);


        int movieId =  cursor.getInt(cursor.getColumnIndex(MoviesContract.Movie_Entry.COLUMN_MOVIE_ID));
        String movieTitle = cursor.getString(cursor.getColumnIndex(MoviesContract.Movie_Entry.COLUMN_MOVIE_ORIGINAL_TITLE));
        String movieSynopsis = cursor.getString(cursor.getColumnIndex(MoviesContract.Movie_Entry.COLUMN_MOVIE_SYNOPSIS));
        String movieUserRating = cursor.getString(cursor.getColumnIndex(MoviesContract.Movie_Entry.COLUMN_MOVIE_RATING));
        String movieVote = cursor.getString(cursor.getColumnIndex(MoviesContract.Movie_Entry.COLUMN_MOVIE_VOTE_COUNT));
        String movieReleaseDate = cursor.getString(cursor.getColumnIndex(MoviesContract.Movie_Entry.COLUMN_MOVIE_RELEASE_DATE));

        String moviePosterPath =  cursor.getString(cursor.getColumnIndex(MoviesContract.Movie_Entry.COLUMN_MOVIE_POSTER_PATH));


        String moviePosterURL = POSTER_BASE_URL + moviePosterPath;
        ImageView moviePoster = (ImageView) view.findViewById(R.id.singleImageView);
        Picasso.with(mcontext).load(moviePosterURL).error(R.mipmap.ic_launcher_round).into(moviePoster);


        SingleMovie movie =  new SingleMovie(movieId,movieUserRating,movieTitle,movieSynopsis,moviePosterPath,movieVote,movieReleaseDate,moviePosterURL,0);
        movieList.add(movie);













        return view;
    }


}