package com.example.practice06;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SimilarGameAdapter extends RecyclerView.Adapter<SimilarGameAdapter.ViewHolder> {
    private  final LayoutInflater inflater;
    private  final List<SimilarGame> games;

    SimilarGameAdapter(Context context, List<SimilarGame> games){
        this.games = games;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // find list_item - our layout
        View view = inflater.inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    public static  class ViewHolder extends  RecyclerView.ViewHolder{
        // get Views
        final TextView gameName;
        final TextView gameManufacturer;
        final ImageView gameImage;
        final RatingBar gameRating;
        ViewHolder(View view){
            super(view);
            gameName = view.findViewById(R.id.game_name);
            gameManufacturer = view.findViewById(R.id.game_manufacturer);
            gameImage = view.findViewById(R.id.image);
            gameRating = view.findViewById(R.id.game_rating);
        }
    }
    public int getItemCount(){
        return  games.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SimilarGame game = games.get(position);
        // set values in views
        holder.gameName.setText(game.getName());
        holder.gameManufacturer.setText(game.getManufacturer());
        holder.gameImage.setImageResource(game.getImage());
        holder.gameRating.setRating(game.getRating());
    }
}
