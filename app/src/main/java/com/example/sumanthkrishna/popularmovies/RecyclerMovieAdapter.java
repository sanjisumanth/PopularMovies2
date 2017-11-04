package com.example.sumanthkrishna.popularmovies;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sumanthkrishna.popularmovies.data.MoviesContract;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sumanthkrishna on 19-Oct-17.
 */

class RecyclerMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>   {

    private final int VIEW_TYPE_MOVIE_DETAILS = 0;
    private final int VIEW_TYPE_MOVIE_REVIEWS = 1;
    private final int VIEW_TYPE_MOVIE_TRAILERS = 2;
    private final int MOVIE_FAVORITE = 1;
    Cursor cursor;
    private final ArrayList<SingleMovie> mdetailedMovies;
    private ArrayList<SingleReview> mReviewsOfMovies;
    private ArrayList<SingleTrailer> mTrailersOfMovies;


    private final Context mcontext;


    /*
    Constructor to take in the current context and the Movie list generated from the cursor loader
     */

    public RecyclerMovieAdapter(ArrayList<SingleMovie> moviesList, ArrayList<SingleReview> moviesReview, ArrayList<SingleTrailer> moviesTrailers,Context context) {
        mdetailedMovies = moviesList;
        mReviewsOfMovies = moviesReview;
        mTrailersOfMovies = moviesTrailers;
         mcontext = context;

    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0 ) {

            return VIEW_TYPE_MOVIE_DETAILS;
        }

        if (position > 0 && position <= mReviewsOfMovies.size()) {

            return VIEW_TYPE_MOVIE_REVIEWS;
        } else {

            return VIEW_TYPE_MOVIE_TRAILERS;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == VIEW_TYPE_MOVIE_DETAILS) {

            LayoutInflater inflater = LayoutInflater.from(mcontext);

            View movieDetailsView = inflater.inflate(R.layout.movie_summary_layout, parent, false);

            return new ViewHolderDetails(movieDetailsView);
        }


        if(viewType == VIEW_TYPE_MOVIE_REVIEWS) {

            LayoutInflater inflater = LayoutInflater.from(mcontext);

            View movieReviewView = inflater.inflate(R.layout.single_review, parent, false);

            return new ViewHolderReviews(movieReviewView);

        } else  {

            LayoutInflater inflater =
                    LayoutInflater.from(mcontext);

            View trailerView = inflater.inflate(R.layout.single_trailer,parent,false);

            return new ViewHolderTrailer(trailerView);


        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {



        switch (holder.getItemViewType()) {

            case VIEW_TYPE_MOVIE_DETAILS: {

                ViewHolderDetails viewHolderDetails = (ViewHolderDetails) holder;





                ImageView imageView = viewHolderDetails.posterImage;
                Picasso.with(mcontext).load(mdetailedMovies.get(position).getMoviePosterURL()).into(imageView);

                TextView ratingTextView = viewHolderDetails.muserRatingTextView;
                ratingTextView.setText(mdetailedMovies.get(position).getMovieUserRating());

                TextView originalTitleTextView = viewHolderDetails.muserOriginalTitleTextView;
                originalTitleTextView.setText(mdetailedMovies.get(position).getMovieOriginalTitle());

                TextView synopsisTextView = viewHolderDetails.mdetailSynopsisView;
                synopsisTextView.setText(mdetailedMovies.get(position).getMovieSynopsis());

                TextView releaseDate = viewHolderDetails.mdetailRelaseDateView;
                releaseDate.setText(mdetailedMovies.get(position).getMovieReleaseDate());

                int favoriteSelection = mdetailedMovies.get(position).getIsFavorite();

                if(favoriteSelection==1){
                    CheckBox cb = viewHolderDetails.cb;
                    cb.setChecked(true);
                }



                break;


            }

            case VIEW_TYPE_MOVIE_REVIEWS: {

                int newPosition = position-1;


                    ViewHolderReviews viewHolderReviews = (ViewHolderReviews) holder;

                    TextView reviewAuthorTextView = viewHolderReviews.reviewAuthor;
                    TextView reviewContentTextView = viewHolderReviews.reviewContent;
                        String authorName = mReviewsOfMovies.get(newPosition).getReviewAuthor();

                        reviewAuthorTextView.setText(authorName);

                        String reviewContent = mReviewsOfMovies.get(newPosition).getReviewContent();

                        reviewContentTextView.setText(reviewContent);
                break;




            }

            case VIEW_TYPE_MOVIE_TRAILERS: {

               int newpositionTrailer = position -1- mReviewsOfMovies.size();


                ViewHolderTrailer viewHolderTrailer = (ViewHolderTrailer) holder;

                    ImageView trailerImage = viewHolderTrailer.trailerImage;

                    SingleTrailer singleTrailer = mTrailersOfMovies.get(newpositionTrailer);

                    String trailerImageUrl = singleTrailer.getImagePath();

                    Picasso.with(mcontext).load(trailerImageUrl).into(trailerImage);

                    String trailerName = singleTrailer.getTrailerName();

                    TextView trailerNameTextView = viewHolderTrailer.trailerName;
                    trailerNameTextView.setText(trailerName);


                break;


            }


        }


    }

    @Override
    public int getItemCount() {

        return 1 + mReviewsOfMovies.size()+mTrailersOfMovies.size();
    }



    /*
    Viewholder for the movie details
     */


    public class ViewHolderDetails extends RecyclerView.ViewHolder {
        public final TextView mdetailSynopsisView;
        public final TextView mdetailRelaseDateView;
        public final TextView muserRatingTextView;
        public final TextView muserOriginalTitleTextView;
        public final ImageView posterImage;

        final CheckBox cb;

        /*
        View Holder to get references of all the views of the movies details layout file
         */

        public ViewHolderDetails(final View itemView) {


            super(itemView);



            posterImage = (ImageView) itemView.findViewById(R.id.DetailImage);
//

            muserRatingTextView = (TextView) itemView.findViewById(R.id.DetailUserRating);
//
            muserOriginalTitleTextView = (TextView) itemView.findViewById(R.id.DetailOriginalTitle);



            mdetailSynopsisView = (TextView) itemView.findViewById(R.id.DetailSynopsisTextView);


            mdetailRelaseDateView = (TextView) itemView.findViewById(R.id.DetailReleaseDate);

            cb = (CheckBox) itemView.findViewById(R.id.checkbox);

            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int adatperPosition = getAdapterPosition();

                    if(cb.isChecked()) {


                        ContentValues contentValues = new ContentValues();
                        contentValues.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_ID, mdetailedMovies.get(adatperPosition).getMovieId());
                        String movieTitle =  mdetailedMovies.get(adatperPosition).getMovieOriginalTitle();
                        contentValues.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_ORIGINAL_TITLE,movieTitle);
                        contentValues.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_RELEASE_DATE,mdetailedMovies.get(adatperPosition).getMovieReleaseDate() );
                        contentValues.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_VOTE_COUNT, mdetailedMovies.get(adatperPosition).getMovieVoteCount());
                        contentValues.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_SYNOPSIS, mdetailedMovies.get(adatperPosition).getMovieSynopsis());
                        contentValues.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_POSTER_PATH, mdetailedMovies.get(adatperPosition).getMoviePoster());
                        contentValues.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_RATING, mdetailedMovies.get(adatperPosition).getMovieUserRating());
                        contentValues.put(MoviesContract.Movie_Entry.COLUMN_MOVIE_FAVORITE_MARK,MOVIE_FAVORITE);
                        mcontext.getContentResolver().insert(MoviesContract.Movie_Entry.CONTENT_URI_FAVORITE, contentValues);
                        Toast.makeText(mcontext, "Movie - " + movieTitle  + " -  added to Favorites ", Toast.LENGTH_LONG).show();
                    } else {

                        Toast.makeText(mcontext, "Movie removed from Favorites", Toast.LENGTH_LONG).show();

                        String selectionMovie = String.valueOf(mdetailedMovies.get(adatperPosition).getMovieId());
                        String[] selectionArgs = new String[]{selectionMovie};

                        mcontext.getContentResolver().delete(MoviesContract.Movie_Entry.CONTENT_URI_FAVORITE, null, selectionArgs);


                    }

                }
            });


        }
    }

    /*
    View holder for the reviews
     */

    public class ViewHolderReviews extends RecyclerView.ViewHolder {

        public final TextView reviewAuthor;
        public final TextView reviewContent;


        public ViewHolderReviews(View itemView) {

            super(itemView);
            reviewAuthor = (TextView) itemView.findViewById(R.id.reivewAuthorTextView);
            reviewContent = (TextView) itemView.findViewById(R.id.reviewContentTextView);

        }
    }

    public class ViewHolderTrailer extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final ImageView trailerImage;
        public final TextView trailerName;

        public ViewHolderTrailer(View itemView) {

            super(itemView);
            trailerImage = (ImageView) itemView.findViewById(R.id.tailerImage);
            trailerName  = (TextView) itemView.findViewById(R.id.trailerName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int adapterPositionTrailer = getAdapterPosition()-1- mReviewsOfMovies.size();
            SingleTrailer trailer =  mTrailersOfMovies.get(adapterPositionTrailer);

            String trailerKey = trailer.getTrailerKey();
            Intent trailerIntent =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + trailerKey));
            view.getContext().startActivity(trailerIntent);


        }
    }

    public void updateReviews(ArrayList<SingleReview> reviewArrayList){

        this.mReviewsOfMovies = reviewArrayList;
        notifyDataSetChanged();
    }

    public void updateVideos(ArrayList<SingleTrailer> trailerArrayList){

        this.mTrailersOfMovies = trailerArrayList;
        notifyDataSetChanged();
    }
}
