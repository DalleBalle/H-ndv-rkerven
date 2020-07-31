package com.danieljensen.hndvrkerven.viewmodels;

import androidx.lifecycle.ViewModel;

import com.danieljensen.hndvrkerven.models.Search;

import java.util.LinkedList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private LinkedList<Search> recentSearches;

    public MainActivityViewModel() {
        this.recentSearches = new LinkedList<>();
    }

    public List<Search> getRecentSearches() {
        return recentSearches;
    }

    public void addRecentSearches(Search search) {
        this.recentSearches.addFirst(search);
    }
}
