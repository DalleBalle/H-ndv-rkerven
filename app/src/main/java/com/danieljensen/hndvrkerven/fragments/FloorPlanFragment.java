package com.danieljensen.hndvrkerven.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.danieljensen.hndvrkerven.R;
import com.danieljensen.hndvrkerven.viewmodels.DetailsActivityViewModel;

import java.util.zip.Inflater;

public class FloorPlanFragment extends Fragment {

    private DetailsActivityViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        viewModel = new ViewModelProvider(this).get(DetailsActivityViewModel.class);

        return inflater.inflate(R.layout.fragment_floorplan, container, false);

    }
}
