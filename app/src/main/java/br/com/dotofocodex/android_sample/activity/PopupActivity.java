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

/***
 * More information on:
 *  https://stackoverflow.com/questions/5944987/how-to-create-a-popup-window-popupwindow-in-android
 *  https://stackoverflow.com/questions/7440334/how-do-gravity-values-effect-popupwindow-showatlocation-in-android/50187508#50187508
 */
import br.com.dotofocodex.android_sample.R;

public class PopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        Button button = findViewById(R.id.bt_activity_popup_show);
        button.setOnClickListener((View v) -> {
            popup(v);
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public void popup(View view) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        // focusable true means, lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener((View v, MotionEvent event) -> {
            popupWindow.dismiss();
            return true;
        });
    }
}
