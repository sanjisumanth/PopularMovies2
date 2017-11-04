package com.example.sumanthkrishna.popularmovies;

/**
 * Created by sumanthkrishna on 28-Oct-17.
 */

class Favorites {

        private SingleMovie singleMovie = null;
        private SingleTrailer singleTrailer = null;
        private SingleReview singleReview = null;

        void setFavorites(SingleTrailer singleTrailer, SingleMovie singleMovie, SingleReview singleReview){

            this.singleMovie = singleMovie;
            this.singleTrailer = singleTrailer;
            this.singleReview = singleReview;



        }

        public void setSingleMovie(SingleMovie singleMovie) {
            this.singleMovie = singleMovie;
        }


        public void setSingleReview(SingleReview singleReview) {
            this.singleReview = singleReview;
        }

        public void setSingleTrailer(SingleTrailer singleTrailer) {
            this.singleTrailer = singleTrailer;
        }

        public SingleMovie getSingleMovie() {
            return singleMovie;
        }

        public SingleTrailer getSingleTrailer() {
            return singleTrailer;
        }

        public SingleReview getSingleReview() {
            return singleReview;
        }


    }
