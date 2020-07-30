package com.danieljensen.hndvrkerven;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Search> mSearches;
    private SearchViewOnClickListener searchViewOnClickListener;

    public SearchViewAdapter(ArrayList<Search> mSearches, SearchViewOnClickListener searchViewOnClickListener) {
        this.mSearches = mSearches;
        this.searchViewOnClickListener = searchViewOnClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_searchitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.store.setText(mSearches.get(position).getStore());
        viewHolder.address.setText(mSearches.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return mSearches.size();
    }

    public void updateResults(List<Search> searches) {
        mSearches.clear();
        mSearches.addAll(searches);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView store;
        TextView address;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            store = itemView.findViewById(R.id.store);
            address = itemView.findViewById(R.id.address);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("ven", "onClick: " + getAdapterPosition());
            Intent intent = new Intent(v.getContext(), DetailsActivity.class);
            intent.putExtra("document", mSearches.get(getAdapterPosition()).getDocumentReference());
            v.getContext().startActivity(intent);
            searchViewOnClickListener.onClick(mSearches.get(getAdapterPosition()));
        }
    }
}
