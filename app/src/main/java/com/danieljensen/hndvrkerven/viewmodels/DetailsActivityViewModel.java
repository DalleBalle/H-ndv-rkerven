package com.danieljensen.hndvrkerven.viewmodels;

import com.danieljensen.hndvrkerven.models.Location;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailsActivityViewModel {

    private Location location;

    public DetailsActivityViewModel(String documentReference) {
        getLocationObj(documentReference);
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
}
