package com.danieljensen.hndvrkerven.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.danieljensen.hndvrkerven.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddNoteActivity extends AppCompatActivity {

    DocumentReference locationDocRef;
    StorageReference storageRef = FirebaseStorage.getInstance().getReference("note_pictures");
    String documentId;
    Uri pictureDownloadUri;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        String documentRef = getIntent().getStringExtra("document");
        locationDocRef = FirebaseFirestore.getInstance().document("locations/" + documentRef);
    }

    public void sendNote(View view) {
        Button sendButton = findViewById(R.id.sendNoteButton);
        sendButton.setEnabled(false);
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
                    if (photoPath == null) {
                        Toast.makeText(getApplicationContext(), "Note uden billede tilføjet.", Toast.LENGTH_LONG).show();
                    } else {
                        documentId = task.getResult().getId();
                        uploadPicture();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Noget gik galt, prøv igen.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(this, "com.danieljensen.hndvrkerven.fileprovider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        String imageFileName = "WorkFriend_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        photoPath = image.getAbsolutePath();
        return image;
    }

    private void uploadPicture() {
        Uri file = Uri.fromFile(new File(photoPath));
        StorageReference pictureRef = storageRef.child(file.getLastPathSegment());
        UploadTask uploadTask = pictureRef.putFile(file);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return pictureRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    pictureDownloadUri = task.getResult();
                    setPictureDownloadUri();
                }
            }
        });
    }

    private void setPictureDownloadUri() {
        Map<String, Object> docData = new HashMap<>();
        docData.put("pictureRef", pictureDownloadUri.toString());
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("notes").document(documentId);
        documentReference.update(docData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Note med billede tilføjet.", Toast.LENGTH_LONG).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}