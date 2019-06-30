package br.com.dotofocodex.android_sample.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import br.com.dotofocodex.android_sample.R;


/**
 * RecyclerView inner NestedScrollView:
 *  https://stackoverflow.com/questions/31000081/how-to-use-recyclerview-inside-nestedscrollview
 * Very good tutorial of DrawerLayout:
 *  https://guides.codepath.com/android/fragment-navigation-drawer
 *  https://www.androidpro.com.br/blog/design-layout/material-design-criando-navigation-drawer
 *  http://www.androiddeft.com/navigation-drawer-android-fragments
 *  https://www.journaldev.com/9958/android-navigation-drawer-example-tutorial
 * Tutorials about Android:
 *  https://guides.codepath.com/android
 * */
public class DrawerLayoutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        toolbar = findViewById(R.id.tb_activity_drawer_layout);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.dl_activity_drawer_layout);

        navigationView = findViewById(R.id.nv_activity_drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Switch sw = navigationView.getMenu().findItem(R.id.nav_item_one).getActionView().findViewById(R.id.sw_activity_drawer_layout);
        sw.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            if (isChecked) {
                Toast.makeText(DrawerLayoutActivity.this, "Turn the lights ON!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_one: {
                Toast.makeText(this, "Menu 1", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_item_two: {
                Toast.makeText(this, "Menu 2", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_item_three: {
                Toast.makeText(this, "Menu 3", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_item_four: {
                Toast.makeText(this, "Menu 4", Toast.LENGTH_SHORT).show();
                break;
            }
            default: {
                Toast.makeText(this, "Menu Default", Toast.LENGTH_SHORT).show();
                break;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}