package br.com.dotofocodex.android_sample.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.dotofocodex.android_sample.App;
import br.com.dotofocodex.android_sample.R;
import br.com.dotofocodex.android_sample.adapter.RecyclerViewAdapter;

/***
 * More information on:
 *  https://www.androidhive.info/2016/01/android-working-with-recycler-view/
 * Recycler View scroll:
 *  https://stackoverflow.com/questions/26580723/how-to-scroll-to-the-bottom-of-a-recyclerview-scrolltoposition-doesnt-work
 * Recycler View swipe:
 *  https://www.androidhive.info/2017/09/android-recyclerview-swipe-delete-undo-using-itemtouchhelper/?utm_source=recyclerview&utm_medium=site&utm_campaign=refer_article
 * Recycler View filter:
 *  https://www.androidhive.info/2017/11/android-recyclerview-with-search-filter-functionality/?utm_source=recyclerview&utm_medium=site&utm_campaign=refer_article
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewActivity";

    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        Log.d(TAG, "onCreate called");
        init();
    }

    private void prepare() {
        this.data = new ArrayList<>(10);
        for (int i=1; i<=10; i++) {
            data.add("Value " + i);
        }
    }

    private void init() {
        Log.d(TAG, "init called");
        prepare();

        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this));
        //rv.addItemDecoration(new DividerItemDecoration(RecyclerViewActivity.this, LinearLayoutManager.VERTICAL));
        rv.setAdapter(new RecyclerViewAdapter(RecyclerViewActivity.this, RecyclerViewActivity.this.data));

        App.getInstance().getExecutorService().submit(() -> {
            FloatingActionButton fab = findViewById(R.id.fab_activity_recycler);
            fab.setOnClickListener((View v) -> {
                data.add("Value " + (new Random().nextInt(100) + 10));
                rv.getAdapter().notifyDataSetChanged();
                //rv.scrollToPosition(data.size() - 1);
                rv.smoothScrollToPosition(data.size() - 1);
            });
        });
    }
}
