package com.danieljensen.hndvrkerven.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.danieljensen.hndvrkerven.R;

public class NoteDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        String note = getIntent().getStringExtra("noteText");
        TextView noteText = findViewById(R.id.noteDetailText);
        noteText.setText(note);
    }
}