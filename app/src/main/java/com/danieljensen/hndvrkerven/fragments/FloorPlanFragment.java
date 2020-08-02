package com.danieljensen.hndvrkerven.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.danieljensen.hndvrkerven.R;
import com.danieljensen.hndvrkerven.viewmodels.DetailsActivityViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.zip.Inflater;

public class FloorPlanFragment extends Fragment {

    private DetailsActivityViewModel viewModel;

    public FloorPlanFragment(DetailsActivityViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StorageReference storageReference = viewModel.getFloorPlanReference();
        View view = inflater.inflate(R.layout.fragment_floorplan, container, false);
        ImageView imageView = view.findViewById(R.id.floorPlanView);
        Glide.with(view /* context */)
                .load(storageReference)
                .placeholder(R.drawable.ic_baseline_camera_24)
                .into(imageView);
        return view;
    }
}
