package com.amrita.vidyut;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApp extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("realm")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

}


