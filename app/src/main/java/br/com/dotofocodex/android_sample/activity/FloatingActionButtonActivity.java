package br.com.dotofocodex.android_sample.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

import br.com.dotofocodex.android_sample.R;

public class FloatingActionButtonActivity extends AppCompatActivity {

    private static final String TAG = "FloatingActionButtonAct";

    private FloatingActionButton fab3;
    private FloatingActionButton fab0;
    private Animation fabOpen;
    private Animation fabClose;
    private Animation rotateBackward;
    private Animation rotateForward;
    private Animation translateUp;
    private Animation translateDown;
    private boolean isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);

        fab0 = findViewById(R.id.fab_activity_floating_action_button_3_inner_0);
        fab0.hide();
        isOpen = false;

        fab3 = findViewById(R.id.fab_activity_floating_action_button_3);
        fab3.setOnClickListener((View v) -> animate());

        load();
    }

    private void load() {
        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);
        translateUp = AnimationUtils.loadAnimation(this, R.anim.fab_translate_up);
        translateDown = AnimationUtils.loadAnimation(this, R.anim.fab_translate_down);
    }

    public void animate() {
        if(isOpen) {
            close();
        }
        else {
            open();
        }
    }

    private void open() {
        fab0.show();
        AnimationSet as = new AnimationSet(true);
        as.setFillEnabled(true);
        as.setInterpolator(new LinearInterpolator());

        fab3.startAnimation(rotateForward);
        as.addAnimation(translateUp);
        //as.addAnimation(fabOpen);
        //fab0.startAnimation(as);

        //fab0.startAnimation(fabOpen);
        fab0.startAnimation(translateUp);
        isOpen = true;

    }

    private void close() {
        AnimationSet as = new AnimationSet(true);
        as.setFillEnabled(true);
        as.setInterpolator(new LinearInterpolator());
        as.addAnimation(translateDown);

        // add more animation in the animation set;
        //as.addAnimation(fabClose);
        fab3.startAnimation(rotateBackward);
        fab0.startAnimation(as);
        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(TAG, "onAnimationStart...");
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG, "onAnimationEnd...");
                isOpen = false;
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

    }
}
