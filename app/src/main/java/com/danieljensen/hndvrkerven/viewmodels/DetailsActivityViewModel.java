package com.danieljensen.hndvrkerven.viewmodels;

import android.util.Log;

import com.danieljensen.hndvrkerven.models.Location;
import com.danieljensen.hndvrkerven.models.Note;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class DetailsActivityViewModel {

    private Location location;
    private ArrayList<Note> notes;

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public DetailsActivityViewModel(String documentReference) {
        notes = new ArrayList<>();
        initLocation(documentReference);
        initNotes(documentReference);
    }

    private void initLocation(String documentRef) {
        DocumentReference docRef = FirebaseFirestore.getInstance().document("locations/" + documentRef);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    location = documentSnapshot.toObject(Location.class);
                }
            }
        });
    }

    public StorageReference getFloorPlanReference() {
        StorageReference floorPlanReference = FirebaseStorage.getInstance().getReferenceFromUrl(location.getFloorplanRef());
        return floorPlanReference;
    }

    public void initNotes(String documentReference) {
        DocumentReference docRef = FirebaseFirestore.getInstance().document("locations/" + documentReference);
        final Task<QuerySnapshot> query = FirebaseFirestore.getInstance().collection("notes")
                .whereEqualTo("docRef", docRef).get();
        Log.d("notes", documentReference);

        query.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.d("notes", String.valueOf(queryDocumentSnapshots));
                for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                    notes.add(queryDocumentSnapshot.toObject(Note.class));
                    Log.d("notes", queryDocumentSnapshot.toString());
                }
            }
        });
    }
}
