package com.example.noteapp.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.R;
import com.example.noteapp.model.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    // Instance variables
    private List<Note> notes;
    private Context context;
    private OnNoteListener onNoteListener;

    // Constructor
    public NotesAdapter(List<Note> notes, Context context, OnNoteListener listener) {
        this.notes = notes;
        this.context = context;
        this.onNoteListener = listener;
    }

    // Methods
    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.note, parent, false);
        return new ViewHolder(itemView, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        holder.previewNoteName.setText(this.notes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return this.notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Instance variables
        private TextView previewNoteName;
        private OnNoteListener listener;

        // Constructor for this class
        public ViewHolder(@NonNull View itemView, OnNoteListener listener) {
            super(itemView);
            previewNoteName = itemView.findViewById(R.id.TV_note_name);
            this.listener = listener;

            itemView.setOnClickListener(this);
        }

        // onClick method to detect clicks
        @Override
        public void onClick(View v) {
            listener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener {
        // Use this method to send the position of the note when clicked
        void onNoteClick(int position);
    }
}
