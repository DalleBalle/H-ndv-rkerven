package com.danieljensen.hndvrkerven.models;

import androidx.annotation.Nullable;

public class Search {
    private String store;
    private String address;
    private String documentReference;

    public Search(String store, String address, String documentReference) {
        this.store = store;
        this.address = address;
        this.documentReference = documentReference;
    }

    public Search() {}

    ;

    public String getStore() {
        return store;
    }

    public String getAddress() {
        return address;
    }

    public String getDocumentReference() {
        return documentReference;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return ((Search)obj).store.equals(store) && ((Search)obj).address.equals(address);
    }
}
