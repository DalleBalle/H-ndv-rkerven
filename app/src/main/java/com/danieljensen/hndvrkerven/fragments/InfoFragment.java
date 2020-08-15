package com.danieljensen.hndvrkerven.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danieljensen.hndvrkerven.R;
import com.danieljensen.hndvrkerven.adapters.DocumentationAdapter;
import com.danieljensen.hndvrkerven.viewmodels.DetailsActivityViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoFragment extends Fragment {
    private DetailsActivityViewModel viewModel;

    public InfoFragment(DetailsActivityViewModel viewModel) {

        this.viewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        RecyclerView documentationList = view.findViewById(R.id.infoList);
        DocumentationAdapter documentationAdapter = new DocumentationAdapter(viewModel);
        documentationList.setAdapter(documentationAdapter);
        documentationList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }
}
