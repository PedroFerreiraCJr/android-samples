package br.com.dotofocodex.android_sample.util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ViewAnimation {

    private ViewAnimation() {
        super();
    }

    /*
    old implementation
    public static void expand(View view) {
        view.measure(-1, -2);
        int measuredHeight = view.getMeasuredHeight();
        view.getLayoutParams().height = 0;
        view.setVisibility(View.VISIBLE);
        Animation anim = new Animation() {
            public boolean willChangeBounds() {
                return true;
            }

            protected void applyTransformation(float f, Transformation transformation) {
                view.getLayoutParams().height = (int) (measuredHeight * f);
                view.requestLayout();
            }
        };
        anim.setDuration(((int) (((float) measuredHeight) / view.getContext().getResources().getDisplayMetrics().density)));
        view.startAnimation(anim);
    }

    public static void collapse(View view) {
        final int measuredHeight = view.getMeasuredHeight();
        Animation anim = new Animation() {
            public boolean willChangeBounds() {
                return true;
            }

            protected void applyTransformation(float f, Transformation transformation) {
                if (f == 1.0f) {
                    view.setVisibility(View.GONE);
                    return;
                }
                view.getLayoutParams().height = measuredHeight - ((int) (((float) measuredHeight) * f));
                view.requestLayout();
            }
        };
        anim.setDuration((long) ((int) (((float) measuredHeight) / view.getContext().getResources().getDisplayMetrics().density)));
        view.startAnimation(anim);
    }
    */

    public static void expand(View view) {
        view.measure(-1, -2);
        int height = view.getMeasuredHeight();
        view.getLayoutParams().height = 0;
        view.setVisibility(View.VISIBLE);
        Animation anim = new DefaultAnimation(view, height, DefaultAnimation.EXPAND);
        anim.setDuration((int) ((float) height / view.getContext().getResources().getDisplayMetrics().density));
        view.startAnimation(anim);
    }

    public static void collapse(View view) {
        int height = view.getMeasuredHeight();
        Animation anim = new DefaultAnimation(view, height, DefaultAnimation.COLLAPSE);
        anim.setDuration((int) ((float) height / view.getContext().getResources().getDisplayMetrics().density));
        view.startAnimation(anim);
    }

    private static class DefaultAnimation extends Animation {

        private static final int EXPAND = 0;
        private static final int COLLAPSE = 1;

        private View view;
        private int measuredHeight;
        private int action;

        private DefaultAnimation(View view, int measuredHeight, int action) {
            super();
            this.view = view;
            this.measuredHeight = measuredHeight;
            this.action = action;
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            if (this.action == EXPAND) {
                expand(interpolatedTime);
            }
            else if (this.action == COLLAPSE) {
                collapse(interpolatedTime);
            }
        }

        private void expand(float f) {
            this.view.getLayoutParams().height = (int) (this.measuredHeight * f);
            this.view.requestLayout();
        }

        private void collapse(float f) {
            if (f == 1.0f) {
                this.view.setVisibility(View.GONE);
                return;
            }

            this.view.getLayoutParams().height = this.measuredHeight - ((int) (((float) this.measuredHeight) * f));
            this.view.requestLayout();
        }
    }

}
