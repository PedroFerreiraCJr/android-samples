package br.com.dotofocodex.android_sample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.com.dotofocodex.android_sample.R;

/**
 * More information about transitions on:
 *  https://stackoverflow.com/questions/10243557/how-to-apply-slide-animation-between-two-activities-in-android
 * */
public class Transition1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // another technique for apply activity transition
        //overridePendingTransition(R.anim.transition_slide_in, R.anim.transition1_slide_out);
        setContentView(R.layout.activity_transition_1);

        Button back = findViewById(R.id.bt_activity_transition_1_back);
        back.setOnClickListener((View view) -> startActivity(new Intent(Transition1Activity.this, TransitionActivity.class)));
    }

    @Override
    public void finish() {
        super.finish();
        // another technique for apply activity transition
        //overridePendingTransition(R.anim.from_right_to_left, R.anim.from_left_to_right);
    }
}