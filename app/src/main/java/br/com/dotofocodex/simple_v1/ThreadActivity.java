package br.com.dotofocodex.simple_v1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class ThreadActivity extends AppCompatActivity {

    // ONE_MILLISECOND = 1000
    // HW_RATE = 60
    // ONE_MILLISECOND/HW_RATE = 16.6666666667
    private static final float MAX_TIME_PER_RENDER = 16.67f;
    private static final int FRAMES_TO_SKIP = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        findViewById(R.id.bt_thread).setOnClickListener(v -> sw());
    }

    private final void sw() {
        sl();
        ts();
    }

    private final void sl() {
        try {
            nf(Thread.currentThread());
            Thread.sleep(tm());
        } catch (InterruptedException e) { }
    }

    private final void nf(Thread t) {
        Log.i("ABC", String.valueOf(t.getId()));
        Log.i("ABC", t.getName());
        Log.i("ABC", String.valueOf(t.getPriority()));
        Log.i("ABC", t.getState().name());
        Log.i("ABC", t.getThreadGroup().getName());
    }

    private final void ts() {
        Toast.makeText(ThreadActivity.this, "Touch again...", Toast.LENGTH_SHORT).show();
    }

    private final int tm() {
        return (int) (MAX_TIME_PER_RENDER * FRAMES_TO_SKIP);
    }

}
