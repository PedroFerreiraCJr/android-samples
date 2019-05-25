package br.com.dotofocodex.simple_v1.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import br.com.dotofocodex.simple_v1.R;
import br.com.dotofocodex.simple_v1.adapter.OrderTabAdapter;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        init();
    }

    private void init() {
        // bind to the view element
        ViewPager vp = findViewById(R.id.vp_order0);
        TabLayout tl = findViewById(R.id.tl_order0);

        // create the adapter
        vp.setAdapter(new OrderTabAdapter(getSupportFragmentManager()));

        // configure de tablayout with the adapter
        tl.setupWithViewPager(vp);

        FloatingActionButton fab = findViewById(R.id.fab_order);
        fab.setOnClickListener(view -> Snackbar
            .make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show());
    }

}