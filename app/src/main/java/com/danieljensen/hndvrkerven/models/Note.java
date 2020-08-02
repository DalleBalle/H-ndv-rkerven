package com.danieljensen.hndvrkerven.models;

import com.google.firebase.firestore.DocumentReference;

public class Note {
    private DocumentReference docRef;
    private String noteText;
    private String pictureRef;

    public Note() {
    }

    public Note(DocumentReference docRef, String noteText, String pictureRef) {
        this.docRef = docRef;
        this.noteText = noteText;
        this.pictureRef = pictureRef;
    }

    public DocumentReference getDocRef() {
        return docRef;
    }

    public void setDocRef(DocumentReference docRef) {
        this.docRef = docRef;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getPictureRef() {
        return pictureRef;
    }

    public void setPictureRef(String pictureRef) {
        this.pictureRef = pictureRef;
    }
}
