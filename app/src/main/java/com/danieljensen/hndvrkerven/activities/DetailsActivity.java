package com.danieljensen.hndvrkerven.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.danieljensen.hndvrkerven.fragments.FloorPlanFragment;
import com.danieljensen.hndvrkerven.fragments.InfoFragment;
import com.danieljensen.hndvrkerven.R;
import com.danieljensen.hndvrkerven.fragments.NotesFragment;
import com.danieljensen.hndvrkerven.viewmodels.DetailsActivityViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;

public class DetailsActivity extends AppCompatActivity {

    private DocumentSnapshot mDocSnapshot;
    private DetailsActivityViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InfoFragment(viewModel)).commit();



        String documentRef = getIntent().getStringExtra("document");
        viewModel = new DetailsActivityViewModel(documentRef);
        Log.d("ven", documentRef);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_info:
                            selectedFragment = new InfoFragment(viewModel);
                            break;
                        case R.id.nav_floorplan:
                            selectedFragment = new FloorPlanFragment(viewModel);
                            break;
                        case R.id.nav_notes:
                            selectedFragment = new NotesFragment(viewModel);
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}
