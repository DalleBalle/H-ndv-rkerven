package com.danieljensen.hndvrkerven.adapters;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danieljensen.hndvrkerven.R;
import com.danieljensen.hndvrkerven.activities.NoteDetailsActivity;
import com.danieljensen.hndvrkerven.models.Note;

import java.util.ArrayList;

public class NotesViewAdapter extends RecyclerView.Adapter<NotesViewAdapter.ViewHolder> {

    private ArrayList<Note> mNotes;

    public NotesViewAdapter(ArrayList<Note> mNotes) {
        this.mNotes = mNotes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_notelistitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(mNotes.get(position).getNoteText());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        LinearLayout noteParentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.noteText);
            noteParentLayout = itemView.findViewById(R.id.note_parent_layout);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), NoteDetailsActivity.class);
            intent.putExtra("noteText", mNotes.get(getAdapterPosition()).getNoteText());
            v.getContext().startActivity(intent);
        }
    }
}
