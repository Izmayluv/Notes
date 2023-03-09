package com.example.educationalpractice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    public List<Note> notes;
    private final OnNoteClickListener onClickListener;

    NoteAdapter(Context context, List<Note> notes, OnNoteClickListener onClickListener){
        this.notes = notes;
        this.inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = inflater.inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteAdapter.ViewHolder holder, int position) {
        Note note = notes.get(position);

        int adapterPos = holder.getAdapterPosition();
        long itemId = holder.getItemId();
        holder.textViewNoteName.setText(note.getNoteName());
        holder.textViewNoteDate.setText(note.getNoteDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onNoteClick(note, itemId, adapterPos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder  extends  RecyclerView.ViewHolder{

        final TextView textViewNoteName, textViewNoteDate;

        public ViewHolder(@NonNull View view) {
            super(view);
            textViewNoteName = view.findViewById(R.id.textViewNoteName);
            textViewNoteDate = view.findViewById(R.id.textViewNoteDate);
        }
    }

    interface OnNoteClickListener{
        void onNoteClick(Note note,long itemId, int adapterPos);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void UpdateAdapter(List<Note> newList){
        notes.clear();
        notes.addAll(newList);
        notifyDataSetChanged();
    }

}
