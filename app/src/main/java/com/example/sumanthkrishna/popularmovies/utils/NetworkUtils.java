package com.example.sumanthkrishna.popularmovies.utils;

import android.content.ContentValues;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * These utilities will be used to communicate with the MovieAPI and Fetch the JSON results and parse it.
 */

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();





    private NetworkUtils() {
    }

    public static ContentValues[] fetchTrailer(String queryURL) throws JSONException{

        Log.d("Networkutils","In netowrk utils");


        URL url = createURL(queryURL);
        String JSONResponse = null;
        try {
            JSONResponse = createHttpConnection(url);


        } catch (IOException e) {
            e.printStackTrace();
        }
        ContentValues[] trailerData =  MoviesTrailerExtraction.extractMovieRtrailers(JSONResponse);
        Log.d("Trailer data " , Arrays.toString(trailerData));



        return trailerData;
    }




    public static ContentValues[] fetchReview(String queryURL) throws JSONException{

        Log.d("Networkutils","In netowrk utils"+queryURL);




        URL url = createURL(queryURL);

        String JSONResponse = null;
        try {
            JSONResponse = createHttpConnection(url);

            Log.d("Networkutils", "In netowrk utils reivew" + JSONResponse);


        } catch (IOException e) {
            e.printStackTrace();
        }
        ContentValues[] reviewsData =  MoviesReviewsExtraction.extractMovieReviews(JSONResponse);
        Log.d("Reviews data " , Arrays.toString(reviewsData));



        return reviewsData;
    }

    public static ContentValues[] fetchMovieData(String queryURL) throws JSONException {

        Log.d("Networkutils","In netowrk utils");


        URL url = createURL(queryURL);
        String JSONResponse = null;
        try {
            JSONResponse = createHttpConnection(url);


        } catch (IOException e) {
            e.printStackTrace();
        }
        ContentValues[] moviesData =  MoviesDataExtraction.extractJSONResponseData(JSONResponse);
        Log.d("Movies data " , Arrays.toString(moviesData));



        return moviesData;
    }





    /**
     * Builds the URL used to talk to the movie api using the urlString.
     *
     * @param UrlString the Query string.
     */

    private static URL createURL(String UrlString) {
        URL url = null;
        try {
            url = new URL(UrlString);

        } catch (MalformedURLException e) {

            Log.e(LOG_TAG, "Error with creating URL", e);
        }

        return url;
    }

    /**
     * Used to enable the HttpConnection with the movie Api and return the String JSON repsonse
     *
     * @param url the Query string.
     */

    private static String createHttpConnection(URL url) throws IOException {

        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                Log.d("UTILS", "In create HTTPURLConnection before extracting the data from stream");
                jsonResponse = extractDataFromStream(inputStream);
                Log.d("UTILS", "In create HTTPURLConnection after extracting the data from stream");
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error Establising connection", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * This will enable to read the response from the HttpUrl connection and output a String of the response.
     *
     * @param inputStream the response from the HttpUrl conection.
     */

    private static String extractDataFromStream(InputStream inputStream) {

        StringBuilder builder = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(streamReader);
            Log.d("UTILS", "in extractDatafromstream method before reading the string data");
            try {
                String str = br.readLine();
                builder.append(str);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }



}
