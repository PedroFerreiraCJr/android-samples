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
public class TransitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // another technique for apply activity transition
        //overridePendingTransition(R.anim.from_right_to_left, R.anim.transition_slide_out);
        setContentView(R.layout.activity_transition);

        Button go = findViewById(R.id.bt_activity_transition_go);
        go.setOnClickListener((View view) -> startActivity(new Intent(TransitionActivity.this, Transition1Activity.class)));
    }

    @Override
    public void finish() {
        super.finish();
        // another technique for apply activity transition
        //overridePendingTransition(R.anim.from_right_to_left, R.anim.from_left_to_right);
    }
}