package br.com.dotofocodex.simple_v1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.dotofocodex.simple_v1.component.MyMsgBox;
import br.com.dotofocodex.simple_v1.R;

public class CustomToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_toast);

        Button customToast1 = findViewById(R.id.custom_toast_1);
        customToast1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToastActivity.this.show();
            }
        });

        Button customToast2 = findViewById(R.id.custom_toast_2);
        customToast2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anotherToast();
            }
        });
    }

    private void show() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, findViewById(R.id.toast_layout_root));

        ImageView image = layout.findViewById(R.id.image);
        image.setImageResource(R.mipmap.ic_launcher);
        TextView text = layout.findViewById(R.id.text);
        text.setText("Say Hello! To my custom toast.");

        Toast toast = new Toast(getApplicationContext());
        int axisHorizontal = 0, axisVertical = 100;
        //int axisHorizontal = 0, axisVertical = 0;
        // Top left
        //toast.setGravity(Gravity.TOP|Gravity.LEFT, axisHorizontal, axisVertical);
        // Center
        //toast.setGravity(Gravity.CENTER_VERTICAL, axisHorizontal, axisVertical);
        // Bottom right
        //toast.setGravity(Gravity.BOTTOM|Gravity.RIGHT, axisHorizontal, axisVertical);
        toast.setGravity(Gravity.BOTTOM, axisHorizontal, axisVertical);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    private void anotherToast() {
        new MyMsgBox(this, 5000, Gravity.BOTTOM).show("Hello this is a custom toast");
    }
}
