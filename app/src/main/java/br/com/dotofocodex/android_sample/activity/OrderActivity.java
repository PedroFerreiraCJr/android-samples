package br.com.dotofocodex.android_sample.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import br.com.dotofocodex.android_sample.R;
import br.com.dotofocodex.android_sample.adapter.OrderTabAdapter;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        init();
    }

    private void init() {
        // bind to the view element
        TabLayout tl = findViewById(R.id.tl_order0);
        ViewPager vp = findViewById(R.id.vp_order0);

        // create the adapter
        vp.setAdapter(new OrderTabAdapter(getSupportFragmentManager()));

        // configure de tablayout with the adapter
        tl.setupWithViewPager(vp);

        // bind the fab
        FloatingActionButton fab = findViewById(R.id.fab_order);
        fab.setOnClickListener(view -> {
            fab.hide();
            new Handler().postDelayed(() -> fab.show(), 2000);
        });

        /*
        fab.setOnClickListener(view -> Snackbar
            .make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show());
        */
    }

}