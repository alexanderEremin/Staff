package com.eremin.staff.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Module
public abstract class ModuleComposeDisposable {
    @Provides
    @Singleton
    static CompositeDisposable getComposeDisposable(){
        return new CompositeDisposable();
    }
}
