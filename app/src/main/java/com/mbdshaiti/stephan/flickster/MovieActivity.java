package com.mbdshaiti.stephan.flickster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mbdshaiti.stephan.flickster.adapters.MoviesAdapter;
import com.mbdshaiti.stephan.flickster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MovieActivity extends AppCompatActivity {
ArrayList<Movie> movies;
    MoviesAdapter moviesAdapter;
    ListView lvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int orientation = getResources().getConfiguration().orientation;
        setContentView(R.layout.activity_movie);
        lvItems=(ListView) findViewById(R.id.lvMovies);
        movies=new ArrayList<>();
        moviesAdapter = new MoviesAdapter(this, movies,getResources().getConfiguration().orientation);
        lvItems.setAdapter(moviesAdapter);
        String url="https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults=null;
                try {
                    movieJsonResults=response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    moviesAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MovieActivity.this, DetailsMovieActivity.class);
                Movie m=movies.get(i);
                intent.putExtra("movie", m);
                startActivity(intent);
            }
        });
    }

}
