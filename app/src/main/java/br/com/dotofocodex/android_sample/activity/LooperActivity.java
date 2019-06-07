package br.com.dotofocodex.android_sample.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.dotofocodex.android_sample.R;

public class LooperActivity extends AppCompatActivity {

    private static final String TAG = "LooperActivity";

    private int counter;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper);

        // gets the main looper
        Looper looper = getMainLooper();

        SimpleLooperThread t = new SimpleLooperThread();
        t.start();
        t.waitforStart();

        handler = new SimpleHandler(t.getLooper());

        Button button = findViewById(R.id.bt_activity_looper_0);
        button.setOnClickListener((View view) -> doWork());

        Button block = findViewById(R.id.bt_activity_looper_1);
        block.setOnClickListener((View view) -> {
            Thread main = Thread.currentThread();
            Log.d(TAG, "Thread Name: " + main.getName());
            if (main.getName().equals("main")) {
                Log.d(TAG, "waiting...");
                try {
                    doWork();
                    main.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void doWork() {
        new Thread(() -> handler.post(() -> {
            int i = 0;
            while (i < 5) {
                Log.d(TAG, "Thread name: " + Thread.currentThread().getName());
                Toast.makeText(LooperActivity.this, "Counting... " + (++counter), Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        })).start();
    }

    private static class SimpleLooperThread extends Thread {

        private static final String TAG = "SimpleLooperThread";

        private boolean started;
        private Object startMonitor;
        private Looper threadLooper;

        public SimpleLooperThread() {
            super();
            this.startMonitor = new Object();
        }

        @Override
        public void run() {
            Log.d(TAG, "SimpleLooperThread is running...");
            Looper.prepare();
            this.threadLooper = Looper.myLooper();
            synchronized (startMonitor){
                started = true;
                startMonitor.notifyAll();
            }
            Looper.loop();
        }

        public Looper getLooper() {
            return threadLooper;
        }

       public void waitforStart(){
            synchronized (startMonitor){
                while (!started){
                    try {
                        startMonitor.wait(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class SimpleHandler extends Handler {

        private static final String TAG = "SimpleHandler";

        public SimpleHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handling message...");
        }
    }

}
