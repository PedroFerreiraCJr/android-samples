package br.com.dotofocodex.android_sample;

import android.app.Application;
import android.content.res.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App extends Application {

    private static App instance;
    private final ExecutorService service;

    public App() {
        super();
        this.service = Executors.newFixedThreadPool(5);
        instance = this;
    }

    public ExecutorService getExecutorService() {
        return this.service;
    }

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

    public static final App getInstance() {
        return instance;
    }

}
