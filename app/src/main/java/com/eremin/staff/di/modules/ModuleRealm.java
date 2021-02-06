package com.eremin.staff.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@Module
public class ModuleRealm {
    public ModuleRealm(Context context){
        Realm.init(context);
    }
    @Provides
    Realm getRealm(RealmConfiguration configuration){
        Realm realm = null;
        try{
            realm = Realm.getDefaultInstance();
        }catch (Exception e){
            realm = Realm.getInstance(configuration);
        }
        return realm;
    }
    @Provides
    RealmConfiguration getRealmConfig(){
        return new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
    }
}
