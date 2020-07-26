package com.danieljensen.hndvrkerven;

import android.content.Context;
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

    private ArrayList<Location> mLocations;
    private Context mContext;

    public SearchViewAdapter(ArrayList<Location> mLocations, Context mContext) {
        this.mLocations = mLocations;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_searchitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.store.setText(mLocations.get(position).getStore());
        viewHolder.address.setText(mLocations.get(position).getAddress());

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(mContext, Location.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }

    public void updateResults(List<Location> locations) {
        mLocations.clear();
        mLocations.addAll(locations);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView store;
        TextView address;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            store = itemView.findViewById(R.id.store);
            address = itemView.findViewById(R.id.address);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
