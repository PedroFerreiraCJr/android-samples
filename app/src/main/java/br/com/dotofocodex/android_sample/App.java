package br.com.dotofocodex.android_sample;

import android.app.Application;
import android.content.res.Configuration;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /*
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                // Utilize esse método para tratar um erro inesperado;
            }
        });
        */
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
