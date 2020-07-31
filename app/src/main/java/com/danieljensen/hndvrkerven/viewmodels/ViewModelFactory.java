package com.danieljensen.hndvrkerven.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.firestore.DocumentReference;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;
    private DocumentReference mDocRef;

    public ViewModelFactory(Application mApplication, DocumentReference mDocRef) {
        this.mApplication = mApplication;
        this.mDocRef = mDocRef;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return null;
    }
}
