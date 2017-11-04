package com.example.sumanthkrishna.popularmovies;

/**
 * Created by sumanthkrishna on 21-Oct-17.
 */

public class SingleTrailer {

    private final String trailerKey;
    private final String trailerName;


    public SingleTrailer(String trailerkey,String trailername){


        this.trailerKey= trailerkey;
        this.trailerName=trailername;

    }


    public String getTrailerName() {
        return trailerName;
    }



    public String getTrailerKey(){

        return trailerKey;
    }


    public String getImagePath(){

        String IMAGE_BASE_URL_PATH = "https://img.youtube.com/vi/";
        String IMAGE_QUALITY = "/hq2.jpg";
        return IMAGE_BASE_URL_PATH + trailerKey + IMAGE_QUALITY;
    }

    public String getTrailerSite(){

        return "YouTube";
    }

    public String getUrlForTrailer(){

        return "https://www.youtube.com/watch?v=" + trailerKey;

    }
}
