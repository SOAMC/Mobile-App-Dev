package com.example.tourismapp;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.VerticalViewHolder>{
    private List<com.example.tourismapp.Vertical> verticalList;
    private Context context;
    private OnRowClickListener listener;

    public VerticalAdapter(List<Vertical> verticalList, Context context, OnRowClickListener listener) {

        this.verticalList = verticalList;
        this.context = context;
        this.listener = listener;

    }

    public interface OnRowClickListener {
        void onItemClick (int position);
    }

    @NonNull
    @Override
    public VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.vertical, parent, false);
        return new VerticalViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalViewHolder verticalViewHolder, int i) {
        verticalViewHolder.verticalImageView.setImageResource(verticalList.get(i).getImage());
        verticalViewHolder.placeNameTV.setText(verticalList.get(i).getPlace_name());
        verticalViewHolder.placeDescripTV.setText(verticalList.get(i).getPlace_descrip());
    }

    @Override
    public int getItemCount() {
        return verticalList.size();
    }

    public class VerticalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView verticalImageView;
        public TextView placeNameTV, placeDescripTV;
        public OnRowClickListener onRowClickListener;

        public VerticalViewHolder(@NonNull View itemView, OnRowClickListener onRowClickListener) {
            super(itemView);
            verticalImageView = itemView.findViewById(R.id.imageViewVertical);
            placeNameTV = itemView.findViewById(R.id.tvPlaceVert);
            placeDescripTV = itemView.findViewById(R.id.tvDescripVert);
            this.onRowClickListener = onRowClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRowClickListener.onItemClick(getAdapterPosition());
        }
    }


}
