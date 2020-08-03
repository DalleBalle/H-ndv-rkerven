package com.danieljensen.hndvrkerven.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danieljensen.hndvrkerven.R;
import com.danieljensen.hndvrkerven.activities.AddNoteActivity;
import com.danieljensen.hndvrkerven.adapters.NotesViewAdapter;
import com.danieljensen.hndvrkerven.models.Note;
import com.danieljensen.hndvrkerven.viewmodels.DetailsActivityViewModel;

import java.util.ArrayList;

public class NotesFragment extends Fragment {

    private DetailsActivityViewModel viewModel;

    public NotesFragment(DetailsActivityViewModel viewModel) {

        this.viewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        RecyclerView notesRecyclerView = view.findViewById(R.id.noteList);
        notesRecyclerView.setAdapter(new NotesViewAdapter(viewModel.getNotes()));
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.notes_appbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addNote) {
            createNewNote();
        }

        return super.onOptionsItemSelected(item);
    }

    private void createNewNote() {
        Intent intent = new Intent(getActivity(), AddNoteActivity.class);
        startActivity(intent);
    }
}
