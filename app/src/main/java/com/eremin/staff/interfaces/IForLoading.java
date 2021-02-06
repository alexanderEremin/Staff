package com.eremin.staff.interfaces;

import androidx.lifecycle.MutableLiveData;

public interface IForLoading {
    /**
     * Метод позволяет получить статус загрузки данных,
     * при загрузке отобразить это пользователю
     * @return boolean true идет загрузка false ничего не происходит
     */
    MutableLiveData<Boolean> getStatusLoadingData();
}
