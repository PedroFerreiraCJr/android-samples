package br.com.dotofocodex.android_sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.dotofocodex.android_sample.R;
import br.com.dotofocodex.android_sample.adapter.RecyclerViewAdapter;

public class RecyclerViewActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        Log.d(TAG, "onCreate called");
        init();
    }

    private void init() {
        Log.d(TAG, "init called");
        // bind to recycler view
        RecyclerView rv = findViewById(R.id.rv);

        // layout manager
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<String> data = new ArrayList<String>(100);
        for (int i=1; i<=100; i++) {
            data.add("Value " + i);
        }

        // adapter
        RecyclerViewAdapter rva = new RecyclerViewAdapter(this, data);
        rv.setAdapter(rva);
    }
}
