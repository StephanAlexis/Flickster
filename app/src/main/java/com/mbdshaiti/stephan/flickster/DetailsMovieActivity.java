package com.mbdshaiti.stephan.flickster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mbdshaiti.stephan.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class DetailsMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);
        Movie m = (Movie) getIntent().getSerializableExtra("movie");
        TextView tvOverview = (TextView) findViewById(R.id.tv_overview_detail);
        tvOverview.setText(m.getOverview());
        TextView tvTitle= (TextView) findViewById(R.id.tv_title_detail);
        tvTitle.setText(m.getOriginalTitle());
        TextView tvVote= (TextView) findViewById(R.id.tvVote);
        tvVote.setText("Ratings: "+m.getAverageVote());
        TextView tvPopularity= (TextView) findViewById(R.id.tvPopularity);
        tvPopularity.setText("Popularity: "+m.getPopularity());
        ImageView ivImage=(ImageView) findViewById(R.id.ivImageDetails);

        RatingBar rbRating=(RatingBar) findViewById(R.id.ratingBar);
        rbRating.setRating(Float.parseFloat(m.getAverageVote())*5/10);
        //Rounded
        Picasso.with(this).load(m.getPosterPath()).transform(new RoundedCornersTransformation(15,15)).into(ivImage);
    }
}
