package br.com.dotofocodex.android_sample.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import br.com.dotofocodex.android_sample.R;

/***
 * More information on:
 *  https://stackoverflow.com/questions/5944987/how-to-create-a-popup-window-popupwindow-in-android
 *  https://stackoverflow.com/questions/7440334/how-do-gravity-values-effect-popupwindow-showatlocation-in-android/50187508#50187508
 */

public class PopupActivity extends AppCompatActivity {

    private PopupWindow popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        Button button = findViewById(R.id.bt_activity_popup_show);
        button.setOnClickListener((View v) -> popup = popup(v));
    }

    @SuppressLint("ClickableViewAccessibility")
    public PopupWindow popup(View view) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_popup_window, null, false);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        // focusable true means, lets taps outside the popup also dismiss it
        PopupWindow popup = new PopupWindow(popupView, width, height, false);
        /*
        PopupWindow popup = new PopupWindow(this);
        popup.setFocusable(true);
        popup.setOutsideTouchable(true);
        popup.setWidth(width);
        popup.setHeight(height);
        popup.setContentView(popupView);
        */

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popup.showAtLocation(view, Gravity.CENTER, 0, 0);
        //popup.update(0, 0, width, height);

        // dismiss the popup window when touched
        popupView.setOnTouchListener((View v, MotionEvent event) -> {
            popup.dismiss();
            return true;
        });

        return popup;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // one way of not receive memory leak for the popup window
        if (this.popup != null) {
            this.popup.dismiss();
        }
    }
}
