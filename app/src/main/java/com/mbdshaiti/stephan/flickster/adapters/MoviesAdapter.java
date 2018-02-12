package com.mbdshaiti.stephan.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbdshaiti.stephan.flickster.R;
import com.mbdshaiti.stephan.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Stephan on 2/11/2018.
 */

public class MoviesAdapter extends ArrayAdapter<Movie> {
    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;
    }

    int orientation;
    public MoviesAdapter(Context context, List<Movie> movies, int orientation) {
        super(context, android.R.layout.simple_list_item_1,movies);
        this.orientation=orientation;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Movie movie=getItem(position);

        ViewHolder viewHolder;
        if(convertView==null) {
            viewHolder=new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.item_movie,parent,false);
            viewHolder.tvTitle=(TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.ivImage=(ImageView) convertView.findViewById(R.id.idMovieImage);
            viewHolder.tvOverview=(TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.ivImage.setImageResource(0);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder=(ViewHolder) convertView.getTag();






        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());
        if(orientation== Configuration.ORIENTATION_PORTRAIT)
            Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.ivImage);
        else
            Picasso.with(getContext()).load(movie.getBackdropPath()).into(viewHolder.ivImage);
        return convertView;
    }
}
