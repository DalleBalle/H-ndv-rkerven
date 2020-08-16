package com.danieljensen.hndvrkerven.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.danieljensen.hndvrkerven.R;

import java.util.List;

public class InfoDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        List<String> documentationColumn = getIntent().getStringArrayListExtra("documentationColumn");
        Log.d("veninfo", documentationColumn.toString());
        List<String> documentationData = getIntent().getStringArrayListExtra("documentationData");
        Log.d("veninfo", documentationData.toString());

        LinearLayout linearLayout = findViewById(R.id.infoLayout);
        for (int i = 0; i < documentationColumn.size(); i++) {
            TextView column = new TextView(this);
            column.setText(documentationColumn.get(i));
            column.setTypeface(null, Typeface.BOLD);
            column.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

            linearLayout.addView(column);
            TextView data = new TextView(this);
            data.setText(documentationData.get(i));
            data.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            data.setPadding(0,0,0,25);
            linearLayout.addView(data);
        }
    }
}