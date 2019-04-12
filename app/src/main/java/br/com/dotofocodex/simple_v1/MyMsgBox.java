package br.com.dotofocodex.simple_v1;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyMsgBox {

    private static final int ANIMATION_DEFAULT_DURATION = 600;

    private int hideDelay;
    private int gravity;

    private View mContainer;
    private TextView mTextView;

    private AlphaAnimation fadeIn;
    private AlphaAnimation fadeOut;

    private Handler mHandler;

    public MyMsgBox(Context context, int hideDelay, int gravity) {
        super();
        this.hideDelay = hideDelay;
        this.gravity = gravity;
        ViewGroup container = ((AppCompatActivity) context).findViewById(android.R.id.content);
        View v = ((AppCompatActivity) context).getLayoutInflater().inflate(R.layout.newmb_messagebar, container);
        init(v);
    }

    private void init(View v) {
        mContainer = v.findViewById(R.id.mbContainer);
        mTextView = v.findViewById(R.id.mbMessage);

        fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(ANIMATION_DEFAULT_DURATION);

        fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(ANIMATION_DEFAULT_DURATION);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mContainer.setVisibility(View.VISIBLE);
                Log.i("ABC", "começando animação fade in.");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("ABC", "terminando animação fade in.");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("ABC", "começando animação fade out.");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mContainer.setVisibility(View.GONE);
                Log.i("ABC", "terminando animação fade out.");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mHandler = new Handler();
    }

    public void show(String message) {
        ((LinearLayout) mContainer).setGravity(gravity | Gravity.CENTER_VERTICAL);
        mTextView.setText(message);
        mContainer.startAnimation(fadeIn);
        mHandler.postDelayed(mHideRunnable, hideDelay);
    }

    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mContainer.startAnimation(fadeOut);
        }
    };

}
