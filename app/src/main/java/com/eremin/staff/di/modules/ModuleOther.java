package com.eremin.staff.di.modules;

import com.eremin.staff.other.TextValidation;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleOther {
    @Provides
    TextValidation getTextValidation(){
        return new TextValidation();
    }
}
