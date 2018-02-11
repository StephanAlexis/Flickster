package com.mbdshaiti.stephan.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Stephan on 2/11/2018.
 */

public class Movie {
    String posterPath;
    String backdropPath;

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }
    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath);
    }
    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    String originalTitle;
    String overview;

    public Movie (JSONObject jsonObject) throws JSONException
    {
        this.posterPath=jsonObject.getString("poster_path");
        this.originalTitle=jsonObject.getString("original_title");
        this.overview=jsonObject.getString("overview");
        this.backdropPath=jsonObject.getString("backdrop_path");

    }

    public static ArrayList<Movie> fromJSONArray (JSONArray array)
    {
        ArrayList<Movie> results=new ArrayList<>();
        for(int x=0;x< array.length();x++)
        {
            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}