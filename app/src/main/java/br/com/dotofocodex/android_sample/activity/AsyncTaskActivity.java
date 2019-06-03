package br.com.dotofocodex.android_sample.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;

import br.com.dotofocodex.android_sample.R;


/***
 * More information on:
 *  https://stackoverflow.com/questions/9671546/asynctask-android-example
 */
public class AsyncTaskActivity extends AppCompatActivity {

    private static final String TAG = "AsyncTaskActivity";

    private SimpleAsyncTask current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        Button start = findViewById(R.id.bt_activity_async_task_start);
        start.setOnClickListener((View view) -> {
            Log.d(TAG, "onCreate: start!");
            current = new SimpleAsyncTask(AsyncTaskActivity.this);
            current.execute();
            start.setEnabled(false);
        });

        Button cancel = findViewById(R.id.bt_activity_async_task_cancel);
        cancel.setOnClickListener((View view) -> {
            Log.d(TAG, "onCreate: cancel!");
            current.cancel(false);
            if (current.isCancelled()) {
                start.setEnabled(true);
            }
        });
    }

    private static class SimpleAsyncTask extends AsyncTask<String, Integer, String> {

        private final WeakReference<Context> context;

        SimpleAsyncTask(Context context) {
            super();
            this.context = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // here we can access the main thread;
        }

        @Override
        protected String doInBackground(String... strings) {
            // here we can not access the main thread, because is executed in a background thread;
            int counter = 0;
            while (counter < 10) {
                try {
                    Thread.sleep(1000);
                    if (isCancelled()) {
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter++;
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // here we can access the main thread;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}
