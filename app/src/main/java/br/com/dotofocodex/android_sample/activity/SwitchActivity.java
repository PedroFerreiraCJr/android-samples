package br.com.dotofocodex.android_sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

import br.com.dotofocodex.android_sample.R;

public class SwitchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        Switch one = findViewById(R.id.sw_activity_switch_0);
        one.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {

        });

        Switch two = findViewById(R.id.sw_activity_switch_1);
        two.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {

        });

        Switch thr = findViewById(R.id.sw_activity_switch_2);
        thr.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {

        });

        Switch fou = findViewById(R.id.sw_activity_switch_3);
        fou.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {

        });
    }

}
