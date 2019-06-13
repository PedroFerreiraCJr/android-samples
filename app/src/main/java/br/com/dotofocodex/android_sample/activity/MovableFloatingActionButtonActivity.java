package br.com.dotofocodex.android_sample.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import br.com.dotofocodex.android_sample.R;
import br.com.dotofocodex.android_sample.component.MovableFloatingActionButton;
import br.com.dotofocodex.android_sample.util.DisplayMetricsUtil;

/**
 * implementation from following link:
 *  https://stackoverflow.com/questions/46370836/android-movable-draggable-floating-action-button-fab
 * fab menu:
 *  https://www.sitepoint.com/animating-android-floating-action-button
 * device screen:
 *  https://stackoverflow.com/questions/4743116/get-screen-width-and-height-in-android
 */
public class MovableFloatingActionButtonActivity extends AppCompatActivity {

    private static final String TAG = "MovableFloatingActionBu";
    private boolean show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mfab);
        show = false;

        FloatingActionButton fab0 = findViewById(R.id.fab_activity_floating_action_button_0);
        fab0.setOnClickListener((View view) -> {
        });

        FloatingActionButton fab1 = findViewById(R.id.fab_activity_floating_action_button_1);
        fab1.setOnClickListener((View view) -> {
        });

        MovableFloatingActionButton fab = findViewById(R.id.mfab_activity_floating_action_button);
        fab.setOnClickListener((View view) -> {
            float wd = DisplayMetricsUtil.screenWidthByDPI(this);
            float hd = DisplayMetricsUtil.screenWidthByDPI(this);
            float x = view.getX() + view.getWidth() / 2.0f;
            float y = view.getY() + view.getHeight() / 2.0f;
            String template = "X: %f, Y: %f;";

            /*
            * left top 3.0, 38.0;
            * right top 393.0, 38.0;
            * left bottom 3.0, 713.0;
            * right bottom 393.0, 713.0;
            * finalizar prato (fab0), finalizar pedido (fab1);
            */

            float hsw = DisplayMetricsUtil.screenWidth(this) / 2.0f;
            float hsh = DisplayMetricsUtil.screenHeight(this) / 2.0f;

            // show fab to the left of the view
            if (x < hsw) {
                x += fab0.getWidth() / 2.0f + (20.0f * wd);
            }

            // show fab to the right of the view
            if (x >= hsw) {
                x -= fab0.getWidth() + fab0.getWidth() / 2.0f + (20.0f * wd);
            }

            // show fab at the bottom of the view
            if (y < hsh) {
                y += fab0.getHeight() / 2.0f + (20.0f * hd);
            }

            // show fab above of the view
            if (y >= hsh) {
                y -= fab0.getHeight() + fab0.getHeight() / 2.0f + (20.0f * hd);
            }

            if (show) {
                fab0.hide();
                show = false;
                return;
            }

            fab0.setX(x);
            fab0.setY(y);
            fab0.show();
            show = true;

            Toast.makeText(MovableFloatingActionButtonActivity.this, String.format(template, view.getX(), view.getY()), Toast.LENGTH_SHORT).show();
        });
    }

}
