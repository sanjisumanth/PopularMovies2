package com.example.sumanthkrishna.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Describe the state and behvaviour of the Single Movie object.
 */

public class SingleMovie  implements Parcelable {


    private final int movieID;
    private final String movieUserRating;
    private final String movieOriginalTitle;
    private final String movieSynopsis;
    private final String moviePoster;
    private final String movieVoteCount;
    private final String movieReleaseDate;
    private final String moviePosterURL;
    private  int isFavorite;




    private SingleMovie(Parcel in) {
        movieID = in.readInt();
        movieUserRating = in.readString();
        movieOriginalTitle = in.readString();
        movieSynopsis = in.readString();
        moviePoster = in.readString();
        movieVoteCount = in.readString();
        movieReleaseDate = in.readString();
        moviePosterURL = in.readString();
        isFavorite = in.readInt();

    }




    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(movieID);
        dest.writeString(movieUserRating);
        dest.writeString(movieOriginalTitle);
        dest.writeString(movieSynopsis);
        dest.writeString(moviePoster);
        dest.writeString(movieVoteCount);
        dest.writeString(movieReleaseDate);
        dest.writeString(moviePosterURL);
        dest.writeInt(isFavorite);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SingleMovie> CREATOR = new Creator<SingleMovie>() {
        @Override
        public SingleMovie createFromParcel(Parcel in) {
            return new SingleMovie(in);
        }

        @Override
        public SingleMovie[] newArray(int size) {
            return new SingleMovie[size];
        }
    };


    public int getMovieId(){
        return  movieID;

    }

    public String getMovieVoteCount(){

        return movieVoteCount;
    }

    public String getMoviePoster(){

        return moviePoster;
    }
    public String getMoviePosterURL() {
        return moviePosterURL;
    }

    public String getMovieUserRating() {
        return movieUserRating;
    }

    public String getMovieOriginalTitle() {
        return movieOriginalTitle;
    }

    public int getIsFavorite(){
        return isFavorite;
    }

    public void setFavorite(int b){

        this.isFavorite = b;
    }

    public SingleMovie(int movieID, String movieUserRating, String movieOriginalTitle, String movieSynopsis, String moviePoster, String movieVoteCount, String movieReleaseDate,String moviePosterURL,int mfavorite) {
        this.movieID = movieID;
        this.movieUserRating = movieUserRating;
        this.movieOriginalTitle = movieOriginalTitle;
        this.movieSynopsis = movieSynopsis;
        this.moviePoster = moviePoster;
        this.movieVoteCount = movieVoteCount;
        this.movieReleaseDate = movieReleaseDate;
        this.moviePosterURL = moviePosterURL;
        this.isFavorite= mfavorite;

    }

    public String getMovieSynopsis() {
        return movieSynopsis;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    @Override
    public String toString() {
        return "SingleMovie{" +
                "movieID='" + movieID + '\'' +
                ", movieUserRating='" + movieUserRating + '\'' +
                ", movieOriginalTitle='" + movieOriginalTitle + '\'' +
                ", movieSynopsis='" + movieSynopsis + '\'' +
                ", moviePoster='" + moviePoster + '\'' +
                ", movieVoteCount='" + movieVoteCount + '\'' +
                ", movieReleaseDate='" + movieReleaseDate + '\'' +
                ", moviePosterURL='" + moviePosterURL + '\'' +
                ", movieFavorite='" + isFavorite  + '\'' +
                '}';
    }
}
