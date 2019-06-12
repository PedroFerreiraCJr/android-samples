package br.com.dotofocodex.android_sample.component;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import br.com.dotofocodex.android_sample.App;
import br.com.dotofocodex.android_sample.util.DisplayMetricsUtil;

public class MovableFloatingActionButton extends FloatingActionButton implements View.OnTouchListener {

    private static final float CLICK_DRAG_TOLERANCE = 10.0f;
    private static float SECURE_MARGIN_TOP = 0.0f;
    private static final int ALPHA_100 = 255;
    private static final int ALPHA_50 = 128;

    private float downRawX, downRawY;
    private float dX, dY;

    public MovableFloatingActionButton(Context context) {
        super(context);
        init();
    }

    public MovableFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MovableFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnTouchListener(this);
        SECURE_MARGIN_TOP = DisplayMetricsUtil.screenHeightByDPI((AppCompatActivity) getContext()) * 20.0f;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();

        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            downRawX = event.getRawX();
            downRawY = event.getRawY();
            dX = v.getX() - downRawX;
            dY = v.getY() - downRawY;
            return true; // Consumed
        }
        else if (action == MotionEvent.ACTION_MOVE) {
            int viewWidth = v.getWidth();
            int viewHeight = v.getHeight();

            View viewParent = (View) v.getParent();
            int parentWidth = viewParent.getWidth();
            int parentHeight = viewParent.getHeight();

            float newX = event.getRawX() + dX;
            newX = Math.max(layoutParams.leftMargin, newX); // Don't allow the FAB past the left hand side of the parent
            newX = Math.min(parentWidth - viewWidth - layoutParams.rightMargin, newX); // Don't allow the FAB past the right hand side of the parent

            float newY = event.getRawY() + dY;
            newY = Math.max(layoutParams.topMargin + SECURE_MARGIN_TOP, newY); // Don't allow the FAB past the top of the parent
            newY = Math.min(parentHeight - viewHeight - layoutParams.bottomMargin, newY); // Don't allow the FAB past the bottom of the parent

            v.animate()
                    .x(newX)
                    .y(newY)
                    .setDuration(0)
                    .start();

            v.getBackground().setAlpha(ALPHA_50);
            return true;
        }
        else if (action == MotionEvent.ACTION_UP) {
            float upRawX = event.getRawX();
            float upRawY = event.getRawY();

            float upDX = upRawX - downRawX;
            float upDY = upRawY - downRawY;

            v.getBackground().setAlpha(ALPHA_100);

            if (Math.abs(upDX) < CLICK_DRAG_TOLERANCE && Math.abs(upDY) < CLICK_DRAG_TOLERANCE) {
                return performClick();
            }
            return true;
        }
        else {
            return super.onTouchEvent(event);
        }
    }

}
