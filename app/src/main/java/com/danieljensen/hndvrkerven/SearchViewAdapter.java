package com.danieljensen.hndvrkerven;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> mStore = new ArrayList<>();
    private ArrayList<String> mAddress = new ArrayList<>();
    private Context mContext;

    public SearchViewAdapter(Context context, ArrayList<String> mStore, ArrayList<String> mAddress) {
        this.mStore = mStore;
        this.mAddress = mAddress;
        this.mContext = context;
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

        viewHolder.store.setText(mStore.get(position));
        viewHolder.address.setText(mAddress.get(position));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(mContext, Location.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStore.size();
    }

    public void updateResults(ArrayList<String> store, ArrayList<String> address) {
        mStore.clear();
        mAddress.clear();
        mStore.addAll(store);
        mAddress.addAll(address);
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
