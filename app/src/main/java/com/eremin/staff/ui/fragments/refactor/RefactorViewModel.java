package com.eremin.staff.ui.fragments.refactor;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.eremin.staff.App;
import com.eremin.staff.interfaces.IForLoading;
import com.eremin.staff.other.TextValidation;
import com.eremin.staff.mapdata.UserData;

import javax.inject.Inject;

import io.realm.Realm;

public class RefactorViewModel extends AndroidViewModel implements IForLoading {
    @Inject Realm realm;
    @Inject TextValidation textValidation;
    private final MutableLiveData<Boolean> statusLoading; // Статус загрузки данных
    private final MutableLiveData<String> errors; // Ошибки
    private final MutableLiveData<UserData> userData; // Данные пользователя из БД
    public RefactorViewModel(@NonNull Application application) {
        super(application);
        statusLoading = new MutableLiveData<>();
        errors = new MutableLiveData<>();
        userData = new MutableLiveData<>();
        ((App) application.getApplicationContext()).getAppComponent().injectToRefactorViewModel(this);
    }

    /**
     * Загрузить данные по сотруднику
     * @param id идентификатор
     */
    protected void loadDataUser(String id){
        statusLoading.setValue(true);
        if(!realm.isInTransaction())
            realm.beginTransaction();
        userData.setValue(realm.where(UserData.class).equalTo("id", id).findFirst());
        statusLoading.setValue(false);
    }

    @Override
    public MutableLiveData<Boolean> getStatusLoadingData() {
        return statusLoading;
    }
    public MutableLiveData<String> getErrors() {
        return errors;
    }
    public MutableLiveData<UserData> getUserData() {
        return userData;
    }

    /**
     * Изменить|Сохранить данные пользователя
     */
    protected void safeData(UserData data){
        if (textValidation.notEmpty(data)){
            if(!realm.isInTransaction())
                realm.beginTransaction();
            realm.copyToRealmOrUpdate(textValidation.removeSpaces(data));
            realm.commitTransaction();
        }else {
            errors.setValue("Пустые поля");
        }
    }

    /**
     * Удаление записи о сотруднике
     * @param id идентификатор в БД
     */
    protected void deleteData(String id){
        if(!realm.isInTransaction())
            realm.beginTransaction();
        UserData deleteData = realm.where(UserData.class).equalTo("id", id).findFirst();
        if (deleteData != null) {
            deleteData.deleteFromRealm();
        }
        realm.commitTransaction();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        realm.close();
    }
}
