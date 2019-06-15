package br.com.dotofocodex.android_sample.util;

import android.util.DisplayMetrics;

import br.com.dotofocodex.android_sample.App;

public class DisplayMetricsUtil {

    private static DisplayMetrics metrics;

    private DisplayMetricsUtil() {
        super();
    }

    private static final void init() {
        if (metrics == null) {
            synchronized (DisplayMetricsUtil.class) {
                if (metrics == null) {
                    metrics = App.getInstance().getResources().getDisplayMetrics();
                }
            }
        }
    }

    public static float screenWidthInPixels() {
        init();
        return metrics.widthPixels;
    }

    public static float screenHeightInPixels() {
        init();
        return metrics.heightPixels;
    }

    public static float screenWidthInPixelsDividedBy(float value) {
        init();
        return metrics.widthPixels / value;
    }

    public static float screenHeightInPixelsDividedBy(float value) {
        init();
        return metrics.heightPixels / value;
    }

    public static int pixelsToDip(float pixels) {
        init();
        return (int) (pixels / metrics.density);
    }

    public static float dipToPixels(float dip) {
        init();
        return dip * metrics.density;
    }
}
