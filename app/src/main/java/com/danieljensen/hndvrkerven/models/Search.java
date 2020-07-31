package com.danieljensen.hndvrkerven.models;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

public class Search {
    private String store;
    private String address;
    private String documentReference;

    public Search(String store, String address, String documentReference) {
        this.store = store;
        this.address = address;
        this.documentReference = documentReference;
    }

    public Search() {};

    public String getStore() {
        return store;
    }

    public String getAddress() {
        return address;
    }

    public String getDocumentReference() {
        return documentReference;
    }
}
