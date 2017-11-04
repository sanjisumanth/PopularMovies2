package com.example.sumanthkrishna.popularmovies;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sumanthkrishna.popularmovies.data.MoviesContract;

/**
 * Created by sumanthkrishna on 16-Oct-17.
 */

class ReviewAdapter extends BaseAdapter {

    private Cursor cursor;
    private final Context mcontext;


    public ReviewAdapter(Context context,Cursor cursor){

        mcontext = context;
        this.cursor = cursor;


    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(mcontext).inflate(R.layout.single_review, parent, false);
        }
        cursor.moveToPosition(i);

        String reviewAuthor =  cursor.getString(cursor.getColumnIndex(MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_AUTHOR));
        String reviewContent = cursor.getString(cursor.getColumnIndex(MoviesContract.Movie_Entry.COLUMN_MOVIE_REVIEW_CONTENT));

        TextView reviewAuthorView = (TextView) convertView.findViewById(R.id.reivewAuthorTextView);
        reviewAuthorView.setText(reviewAuthor);

        TextView reviewContentView = (TextView) convertView.findViewById(R.id.reviewContentTextView);
        reviewContentView.setText(reviewContent);

        return null;
    }

    void
    swapCursor(Cursor newCursor) {
        cursor = newCursor;
        notifyDataSetChanged
                ();
    }
}
