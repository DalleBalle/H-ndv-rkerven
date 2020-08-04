package com.danieljensen.hndvrkerven.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.danieljensen.hndvrkerven.R;
import com.google.android.gms.tasks.OnCompleteListener;
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
        Map<String, Object> docData = new HashMap<>();
        docData.put("noteText", noteText);
        docData.put("docRef", locationDocRef);
        CollectionReference noteDocumentReference = FirebaseFirestore.getInstance().collection("notes");
        noteDocumentReference.add(docData).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Note tilføjet.", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Noget gik galt, prøv igen.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}