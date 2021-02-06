package com.eremin.staff.ui.fragments.list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.eremin.staff.App;
import com.eremin.staff.interfaces.IForLoading;
import com.eremin.staff.mapdata.UserData;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class ListUsersViewModel extends AndroidViewModel implements IForLoading {
    @Inject Realm realm;
    private final MutableLiveData<Boolean> statusLoading;
    private final MutableLiveData<List<UserData>> data;
    public ListUsersViewModel(@NonNull Application application) {
        super(application);
        ((App) application.getApplicationContext()).getAppComponent().injectToListUsersViewModel(this);
        statusLoading = new MutableLiveData<>();
        data = new MutableLiveData<>();
        List<UserData> dataList = realm.where(UserData.class).findAll();
        data.setValue(dataList);
    }

    @Override
    public MutableLiveData<Boolean> getStatusLoadingData() {
        return statusLoading;
    }
    public MutableLiveData<List<UserData>> getData() {
        return data;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        realm.close();
    }
}
