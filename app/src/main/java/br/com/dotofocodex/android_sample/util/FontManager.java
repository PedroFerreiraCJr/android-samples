package br.com.dotofocodex.android_sample.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/***
 * More information on:
 *  https://proandroiddev.com/font-typeface-in-android-application-572766edddd6
 *  https://stackoverflow.com/questions/10978038/restrict-edittext-to-single-line
 * Where to put custom fonts:
 *  https://stackoverflow.com/questions/27588965/how-to-use-custom-font-in-a-project-written-in-android-studio
 */
public class FontManager {

    private static final String TAG = "FontManager";

    private static FontManager instance;
    private static Map<Integer, Typeface> fontMap;

    private FontManager(Context context) {
        super();
        AssetManager am = context.getResources().getAssets();
        fontMap = new HashMap<>(3);
        fontMap.put(Typeface.NORMAL, Typeface.createFromAsset(am, "fonts/zcool_regular.ttf"));
        //fontMap.put(Typeface.BOLD, Typeface.createFromAsset(am, "fonts/zcool_bold.ttf"));
        //fontMap.put(Typeface.ITALIC, Typeface.createFromAsset(am, "fonts/zcool_italic.ttf"));
    }

    public void replace(ViewGroup viewGroup) {
        View child;
        for (int i=0; i<viewGroup.getChildCount(); i++) {
            child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup) {
                replace((ViewGroup) child);
            }
            else if (child instanceof TextView) {
                TextView textView = (TextView) child;

                int style = textView.getTypeface().getStyle();

                if (style == Typeface.NORMAL) {
                    textView.setTypeface(fontMap.get(Typeface.NORMAL));
                }
                else if (style == Typeface.BOLD) {
                    textView.setTypeface(fontMap.get(Typeface.BOLD));
                }
                else if (style == Typeface.ITALIC) {
                    textView.setTypeface(fontMap.get(Typeface.ITALIC));
                }
            }
        }
    }

    public static FontManager getInstance(Context context) {
        if (instance == null) {
            synchronized (FontManager.class) {
                if (instance == null) {
                    instance = new FontManager(context);
                }
            }
        }
        return instance;
    }
}
