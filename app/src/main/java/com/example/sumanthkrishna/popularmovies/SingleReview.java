package com.example.sumanthkrishna.popularmovies;

/**
 * Created by sumanthkrishna on 19-Oct-17.
 */

public class SingleReview  {

    private String reviewAuthor;
    private int reviewMovieId;
    private String reviewContent;


    public SingleReview(String reviewAuthor, int reviewMovieId, String reviewContent) {
        this.reviewAuthor = reviewAuthor;
        this.reviewMovieId = reviewMovieId;
        this.reviewContent = reviewContent;
    }


    public String getReviewAuthor() {
        return reviewAuthor;
    }

    public void setReviewAuthor(String reviewAuthor) {
        this.reviewAuthor = reviewAuthor;
    }

    public int getReviewMovieId() {
        return reviewMovieId;
    }

    public void setReviewMovieId(int reviewMovieId) {
        this.reviewMovieId = reviewMovieId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }
}