package br.com.dotofocodex.android_sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import br.com.dotofocodex.android_sample.R;
import br.com.dotofocodex.android_sample.component.MovableFloatingActionButton;

/**
 * implementation from following link:
 *  https://stackoverflow.com/questions/46370836/android-movable-draggable-floating-action-button-fab
 * fab menu:
 *  https://www.sitepoint.com/animating-android-floating-action-button
 */
public class MovableFloatingActionButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mfab);

        MovableFloatingActionButton fab = findViewById(R.id.mfab_activity_floating_action_button);
        fab.setOnClickListener((View view) -> {
            String template = "X: %f, Y: %f;";
            Toast.makeText(MovableFloatingActionButtonActivity.this, String.format(template, view.getX(), view.getY()), Toast.LENGTH_SHORT).show();
        });
    }
}
