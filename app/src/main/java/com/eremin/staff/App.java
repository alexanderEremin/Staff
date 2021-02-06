package com.eremin.staff;

import android.app.Application;

import com.eremin.staff.di.AppComponent;
import com.eremin.staff.di.DaggerAppComponent;
import com.eremin.staff.di.modules.ModuleRealm;

public class App extends Application {
    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                        .moduleRealm(new ModuleRealm(this))
                        .build();
    }
    public AppComponent getAppComponent(){
        return appComponent;
    }
}
