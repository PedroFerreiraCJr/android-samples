package br.com.dotofocodex.android_sample.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.com.dotofocodex.android_sample.R;

import android.text.Layout;

/***
 * More information on:
 *  https://stackoverflow.com/questions/1292575/android-textview-justify-text
 *  https://stackoverflow.com/questions/1292575/android-textview-justify-text/42991773#42991773
 */
public class TextViewJustifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview_justify);

        TextView tv = findViewById(R.id.tv_activity_textview_justify);

        /***
         * More information on:
         *  https://stackoverflow.com/questions/3093365/how-can-i-check-the-system-version-of-android
         */
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tv.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }
    }

}
