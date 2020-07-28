package com.danieljensen.hndvrkerven;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements SearchViewAdapter.OnSearchViewListener {

    private CollectionReference mColRef = FirebaseFirestore.getInstance().collection("locations");
    private SearchViewAdapter adapter;
    private List<Location> recentSearches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SearchView search = findViewById(R.id.searchView);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()){
                    searchQuery(newText);
                }
                else{
                }
                return false;
            }
        });
        initRecyclerView();
        recentSearches = new ArrayList<>();
        loadRecentSearches();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveRecentSearches();
    }

    private void saveRecentSearches() {
        try {
            Gson gson = new GsonBuilder().create();
            Log.e("ven2", "2");
            FileOutputStream fos = this.openFileOutput("RecentSearches.json", Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            gson.toJson(recentSearches, writer);
            writer.close();
            fos.close();

        } catch (IOException e) {
            Log.e("ven2", String.valueOf(e));
            e.printStackTrace();
        }
    }

    private void loadRecentSearches() {
        try {
            FileInputStream inputStream = this.openFileInput("RecentSearches.json");
            String inputString = convertStreamToString(inputStream);
            Log.e("ven", inputString);
            Gson gson = new Gson();
            List<Location> locations = Arrays.asList(gson.fromJson(inputString, Location[].class));
            adapter.updateResults(locations);
        }
        catch (Exception e) {
            Log.e("ven2", "load", e);
        }
    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.searchSuggestions);
        adapter = new SearchViewAdapter(new ArrayList<Location>(), this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void searchQuery(String s) {
        Query search = mColRef.orderBy("store")
                .startAt(s)
                .endAt(s+"\uf8ff");
        Log.e("ven", "searchQuery: " + search.get());
        search.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.e("ven", String.valueOf(task.getResult().size()));
                QuerySnapshot result = task.getResult();
                Iterator<QueryDocumentSnapshot> iterator = result.iterator();
                ArrayList<Location> locations = new ArrayList<>();
                while (iterator.hasNext()) {
                    QueryDocumentSnapshot documentSnapshot = iterator.next();
                    String id = documentSnapshot.getReference().getId();
                    Map<String, Object> data = documentSnapshot.getData();
                    String store = (String) data.get("store");
                    String address = (String) data.get("address");
                    Location location = new Location(store, address, id);
                    locations.add(location);
                }
                recentSearches.clear();
                recentSearches.addAll(locations);
                saveRecentSearches();
                adapter.updateResults(locations);
            }
        });
    }

    @Override
    public void onSearchClick(int position) {
//        recentSearches.get(position);
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }
}