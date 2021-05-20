package com.example.tourismapp;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder> {
    private List<com.example.tourismapp.Horizontal> horizontalList;
    private Context context;

    public HorizontalAdapter(List<Horizontal> horizontalList, Context context) {
        this.horizontalList = horizontalList;
        this.context = context;
    }

    @NonNull
    @Override
    public HorizontalAdapter.HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.horizontal, viewGroup, false);
        return new HorizontalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalAdapter.HorizontalViewHolder horizontalViewHolder, int i) {
        horizontalViewHolder.imageViewHorizontal.setImageResource(horizontalList.get(i).getImage());
    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewHorizontal;
        public HorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewHorizontal = itemView.findViewById(R.id.imageViewHorizontal);
        }
    }
}
