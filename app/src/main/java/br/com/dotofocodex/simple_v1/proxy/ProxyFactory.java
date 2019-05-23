package br.com.dotofocodex.simple_v1.proxy;

import android.util.Log;
import android.view.View;

import java.util.Date;

public class ProxyFactory {

    public static final class OnClickLoggingListener implements View.OnClickListener {
        private View.OnClickListener listener;

        public OnClickLoggingListener(View.OnClickListener listener) {
            super();
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            Log.i("ABC", "Before call onClick...");
            listener.onClick(v);
            Log.i("ABC", "After call onClick...");
        }
    }

    public static final class OnClickTimestampListener implements View.OnClickListener {
        private View.OnClickListener listener;

        public OnClickTimestampListener(View.OnClickListener listener) {
            super();
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            Log.i("ABC", String.valueOf(new Date().getTime()));
            listener.onClick(v);
            Log.i("ABC", String.valueOf(new Date().getTime()));
        }
    }
}
