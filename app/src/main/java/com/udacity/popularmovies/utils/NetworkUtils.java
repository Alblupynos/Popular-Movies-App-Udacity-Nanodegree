package com.udacity.popularmovies.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/discover/movie";
    private static final String MOVIE_POSTER_URL = "https://image.tmdb.org/t/p/w500";
    private static final String KEY = "";
    public static final String SORT_POPULAR = "popularity.desc";
    public static final String SORT_TOP_RATED = "vote_average.desc";

    private static final String KEY_PARAM = "api_key";
    private static final String SORT_PARAM = "sort_by";

    public static URL buildUrl(String sortType) {

        Uri buildUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendQueryParameter(KEY_PARAM, KEY)
                .appendQueryParameter(SORT_PARAM, sortType)
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static Uri buildPosterUri(String poster) {
        return Uri.parse(MOVIE_POSTER_URL + poster);
    }

    public static String getResponseFromHttpUrl (URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
