package br.com.dotofocodex.android_sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.dotofocodex.android_sample.R;

/**
 * More information about it on:
 *  https://stackoverflow.com/questions/1964789/move-layouts-up-when-soft-keyboard-is-shown
 * */
public class KeyboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
    }

}
