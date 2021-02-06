package com.eremin.staff.di;

import com.eremin.staff.di.modules.ModuleComposeDisposable;
import com.eremin.staff.di.modules.ModuleOther;
import com.eremin.staff.di.modules.ModuleRealm;
import com.eremin.staff.di.modules.ModuleSharedPreference;
import com.eremin.staff.ui.fragments.list.ListUsersViewModel;
import com.eremin.staff.ui.fragments.refactor.RefactorViewModel;

import dagger.Component;

@Component(modules = {ModuleComposeDisposable.class,
                      ModuleSharedPreference.class,
                      ModuleRealm.class,
                      ModuleOther.class})
public interface AppComponent {
    void injectToRefactorViewModel(RefactorViewModel refactorViewModel); // Модель создания/редактироания сотрудника
    void injectToListUsersViewModel(ListUsersViewModel listUsersViewModel); // Модель списка пользователей
}
