package com.danieljensen.hndvrkerven;

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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private CollectionReference mColRef = FirebaseFirestore.getInstance().collection("locations");
    private RecyclerView recyclerView;
    private SearchViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView searchSuggestions = findViewById(R.id.searchSuggestions);

        SearchView search = findViewById(R.id.searchView);
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
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.searchSuggestions);
        adapter = new SearchViewAdapter(this, new ArrayList<String>(), new ArrayList<String>());
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
                ArrayList<String> mStore = new ArrayList<>();
                ArrayList<String> mAddress = new ArrayList<>();
                while (iterator.hasNext()) {
                    Map<String, Object> data = iterator.next().getData();
                    mAddress.add((String) data.get("address"));
                    mStore.add((String) data.get("store"));
                    Log.e("ven", mAddress.toString());
                }
                adapter.updateResults(mStore, mAddress);
            }
        });
    }
}