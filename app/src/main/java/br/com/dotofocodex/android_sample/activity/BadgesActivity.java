package br.com.dotofocodex.android_sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import br.com.dotofocodex.android_sample.R;

public class BadgesActivity extends AppCompatActivity {

    private int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badges);

        TextView nc = findViewById(R.id.badge_notification_1);
        Button nb = findViewById(R.id.bt_notif);
        nb.setOnClickListener((v) -> {
            nc.setText((++value < 100) ? String.valueOf(value) : "+@");
        });
    }
}
