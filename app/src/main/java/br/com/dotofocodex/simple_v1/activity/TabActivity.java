package br.com.dotofocodex.simple_v1.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.dotofocodex.simple_v1.R;
import br.com.dotofocodex.simple_v1.adapter.TabAdapter;

public class TabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        init();
    }

    private void init() {
        // bind to the view element
        ViewPager vp = findViewById(R.id.vp_example);
        TabLayout tl = findViewById(R.id.tl_example);

        // create the adapter
        vp.setAdapter(new TabAdapter(getSupportFragmentManager()));

        // configure de tablayout with the adapter
        tl.setupWithViewPager(vp);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar
                    .make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });
    }

}