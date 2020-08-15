package com.danieljensen.hndvrkerven.adapters;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danieljensen.hndvrkerven.R;
import com.danieljensen.hndvrkerven.activities.InfoDetailsActivity;
import com.danieljensen.hndvrkerven.viewmodels.DetailsActivityViewModel;
import com.danieljensen.hndvrkerven.viewmodels.ViewModelUpdateListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DocumentationAdapter extends RecyclerView.Adapter<DocumentationAdapter.ViewHolder> implements ViewModelUpdateListener {

    private Map<String, List<String>> mDocumentation;
    private DetailsActivityViewModel viewModel;

    public DocumentationAdapter(DetailsActivityViewModel viewModel) {
        this.mDocumentation = viewModel.getDocumentation();
        this.viewModel = viewModel;
        viewModel.addListener(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_doclistitem, parent, false);
        return new DocumentationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<String> names = mDocumentation.get("0");
        holder.camName.setText(names != null ? names.get(position) : "");
        List<String> ipaddresses = mDocumentation.get("1");
        holder.ipAddress.setText(ipaddresses != null ? ipaddresses.get(position) : "");
    }

    @Override
    public int getItemCount() {
        if (mDocumentation == null || mDocumentation.get("0") == null) {
            return 0;
        }
        return mDocumentation.get("0").size();
    }

    @Override
    public void onDataChanged() {
        mDocumentation = viewModel.getDocumentation();
        Log.d("ven", "hfhjf");
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView camName;
        TextView ipAddress;
        RelativeLayout docLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            camName = itemView.findViewById(R.id.camName);
            ipAddress = itemView.findViewById(R.id.ipAddress);
            docLayout = itemView.findViewById(R.id.documentation_layout);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), InfoDetailsActivity.class);

            intent.putExtra("documentationColumn", (Parcelable) viewModel.getDocumentationColumn());

            ArrayList<String> data = new ArrayList<>();
            for (Map.Entry<String, List<String>> entry : viewModel.getDocumentation().entrySet()) {
                data.add(entry.getValue().get(getAdapterPosition()));
            }
            intent.putExtra("documentationData", (Parcelable) data);

//            v.getContext().startActivity(intent);
        }
    }
}
