package br.com.dotofocodex.android_sample.util;

import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

public class DisplayMetricsUtil {

    public static float screenWidth(AppCompatActivity appCompatActivity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        appCompatActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static float screenHeight(AppCompatActivity appCompatActivity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        appCompatActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int screenWidthByDPI(AppCompatActivity appCompatActivity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        appCompatActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels / displayMetrics.densityDpi;
    }

    public static int screenHeightByDPI(AppCompatActivity appCompatActivity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        appCompatActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels / displayMetrics.densityDpi;
    }
}
