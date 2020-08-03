package com.danieljensen.hndvrkerven.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.danieljensen.hndvrkerven.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddNoteActivity extends AppCompatActivity {

    DocumentReference locationDocRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        String documentRef = getIntent().getStringExtra("document");
        locationDocRef = FirebaseFirestore.getInstance().document("locations/" + documentRef);
    }

    public void sendNote(View view) {
        EditText noteView = findViewById(R.id.noteEditText);
        String noteText = noteView.getText().toString();



        if (noteText.isEmpty()) {return;}
        Map<String, Object> docData = new HashMap<String, Object>();
        docData.put("noteText", noteText);
        docData.put("docRef", locationDocRef);
        CollectionReference noteDocumentReference = FirebaseFirestore.getInstance().collection("notes");
        noteDocumentReference.add(docData);
    }
}