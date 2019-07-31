package com.example.notesapp.app;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
    }
}
