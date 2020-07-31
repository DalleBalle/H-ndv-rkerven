package com.danieljensen.hndvrkerven.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.danieljensen.hndvrkerven.models.Location;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

public class DetailsActivityViewModel {

    private Location location;
    private StorageReference storage;
    private byte[] floorPlanPicture;

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

    private void getFloorPlan() {
        StorageReference floorPlanRef = storage.getStorage().getReferenceFromUrl(location.getFloorPlanRef());

        final long ONE_MEGABYTE = 1024 * 1024;
        floorPlanRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                floorPlanPicture = bytes;
            }
        });
    }

    public byte[] getFloorPlanPicture() {
        return floorPlanPicture;
    }
}
