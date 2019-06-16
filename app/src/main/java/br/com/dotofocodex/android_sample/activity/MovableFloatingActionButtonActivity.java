package br.com.dotofocodex.android_sample.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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
 * action bar height
 *  https://gist.github.com/hamakn/8939eb68a920a6d7a498
 * convert pixels to dip and vice versa
 *  https://acomputerengineer.wordpress.com/2016/06/23/convert-pixel-to-dp-or-dip-and-dp-to-pixel-in-android/
 *  https://stackoverflow.com/questions/11862391/getheight-px-or-dpi
 */
public class MovableFloatingActionButtonActivity extends AppCompatActivity {

    private static final String TAG = "MovableFloatingActionBu";
    private static float MARGIN;
    private static float HALF_SCREEN_WIDTH;
    private static float HALF_SCREEN_HEIGHT;

    private boolean show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mfab);

        MARGIN = DisplayMetricsUtil.pixelsToDip(80.0f);
        HALF_SCREEN_WIDTH = DisplayMetricsUtil.screenWidthInPixelsDividedBy(2.0f);
        HALF_SCREEN_HEIGHT = DisplayMetricsUtil.screenHeightInPixelsDividedBy(2.0f);

        show = false;

        FloatingActionButton fab0 = findViewById(R.id.fab_activity_movable_floating_action_button_0);
        fab0.setOnClickListener((View view) -> {
            Toast.makeText(MovableFloatingActionButtonActivity.this, "Finalizando prato e adicionando novo.", Toast.LENGTH_SHORT).show();
        });

        FloatingActionButton fab1 = findViewById(R.id.fab_activity_movable_floating_action_button_1);
        fab1.setOnClickListener((View view) -> {
            Toast.makeText(MovableFloatingActionButtonActivity.this, "Finalizando pedido...", Toast.LENGTH_SHORT).show();
        });

        MovableFloatingActionButton fab = findViewById(R.id.mfab_activity_floating_action_button);
        fab.setOnClickListener((View view) -> {
            float x = view.getX() + view.getWidth() / 2.0f;
            float y = view.getY() + view.getHeight() / 2.0f;
            String template = "X: %f, Y: %f;";

            /*
            * finalizar prato (fab0), finalizar pedido (fab1)
            */

            if (show) {
                show = false;
                fab.setMove(true);
                fab0.hide();
                fab1.hide();
                return;
            }

            setPositionFab0(x, y, fab0);
            setPositionFab1(x, y, fab1);

            show = true;
            fab.setMove(false);
            fab0.show();
            fab1.show();

            Toast.makeText(MovableFloatingActionButtonActivity.this, String.format(template, x, y), Toast.LENGTH_SHORT).show();
        });
    }

    /***
     * finalizar prato (fab0)
     */
    private void setPositionFab0(float x, float y, FloatingActionButton fab) {
        float newX = x;
        float newY = y;

        newX -= fab.getWidth() / 2.0f;

        // show fab at the bottom of the view
        if (y < HALF_SCREEN_HEIGHT) {
            newY += fab.getHeight() / 2.0f + DisplayMetricsUtil.dipToPixels(MARGIN);
        }
        else if (y >= HALF_SCREEN_HEIGHT) { // show fab above of the view
            newY -= fab.getHeight() + fab.getHeight() / 2.0f + DisplayMetricsUtil.dipToPixels(MARGIN);
        }

        fab.setX(newX);
        fab.setY(newY);
    }

    /***
     * finalizar pedido (fab1)
     */
    private void setPositionFab1(float x, float y, FloatingActionButton fab) {
        float newX = x;
        float newY = y;

        if (x < HALF_SCREEN_WIDTH) {
            newX += fab.getWidth() / 2.0f + DisplayMetricsUtil.dipToPixels(MARGIN);
        }
        else if (x >= HALF_SCREEN_WIDTH) {
            newX -= fab.getWidth() + fab.getWidth() / 2.0f + DisplayMetricsUtil.dipToPixels(MARGIN);
        }

        newY -= fab.getHeight() / 2.0f;

        fab.setX(newX);
        fab.setY(newY);
    }
}
