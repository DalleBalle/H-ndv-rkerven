package com.danieljensen.hndvrkerven.viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;

import com.danieljensen.hndvrkerven.models.Location;
import com.danieljensen.hndvrkerven.models.Note;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DetailsActivityViewModel {

    private Location location;
    private ArrayList<Note> notes;

    public DetailsActivityViewModel(String documentReference) {
        notes = new ArrayList<>();
        getLocationObj(documentReference);
        getNotes(documentReference);
    }

    private void getLocationObj(String documentRef) {
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

    public void getNotes(String documentReference) {
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
