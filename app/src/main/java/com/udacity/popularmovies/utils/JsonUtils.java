package com.udacity.popularmovies.utils;

import com.udacity.popularmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String JSON_ORIGINAL_TITLE = "title";
    private static final String JSON_POSTER = "poster_path";
    private static final String JSON_OVERVIEW = "overview";
    private static final String JSON_RATING = "vote_average";
    private static final String JSON_RELEASE_DATA = "release_date";
    private static final String JSON_ARRAY_RESULTS = "results";

    public static List<Movie> parseMovieJson (String json) throws JSONException {
        JSONObject movies = new JSONObject(json);
        JSONArray results = movies.getJSONArray(JSON_ARRAY_RESULTS);
        List<Movie> movieList = new ArrayList<>(results.length());
        for (int i=0; i<results.length(); i++){
            JSONObject movie = results.getJSONObject(i);
            movieList.add(new Movie(movie.optString(JSON_ORIGINAL_TITLE),
                    movie.optString(JSON_POSTER),
                    movie.optString(JSON_OVERVIEW),
                    movie.optString(JSON_RATING),
                    movie.optString(JSON_RELEASE_DATA)));
        }
        return movieList;
    }
}
