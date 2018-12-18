package com.example.vieony.mokapos.di.component;

import android.app.Application;
import android.content.Context;

import com.example.vieony.mokapos.di.module.AppModule;
import com.example.vieony.mokapos.di.module.ContextModule;
import com.example.vieony.mokapos.di.qualifier.ApplicationContext;
import com.example.vieony.mokapos.di.scope.ApplicationScope;
import com.example.vieony.mokapos.retrofit.APIInterface;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, AppModule.class})
public interface ApplicationComponent {

    APIInterface getApiInterface();

    @ApplicationContext
    Context getContext();

    void injectApplication(Application myApplication);

}
