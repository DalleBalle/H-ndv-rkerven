package com.danieljensen.hndvrkerven.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.danieljensen.hndvrkerven.R;
import com.danieljensen.hndvrkerven.viewmodels.DetailsActivityViewModel;

public class InfoFragment extends Fragment {
    private DetailsActivityViewModel viewModel;

    public InfoFragment(DetailsActivityViewModel viewModel) {

        this.viewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }
}
