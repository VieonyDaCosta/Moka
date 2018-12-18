package com.example.vieony.mokapos.di.component;

import android.content.Context;

import com.example.vieony.mokapos.di.module.ActivityContextModule;
import com.example.vieony.mokapos.di.qualifier.ActivityContext;
import com.example.vieony.mokapos.di.qualifier.ApplicationContext;
import com.example.vieony.mokapos.di.scope.ActivityScope;
import com.example.vieony.mokapos.mvp.main.MainActivity;
import com.example.vieony.mokapos.mvp.main.MainActivityMvpModule;

import dagger.Component;

@ActivityScope
@Component(modules = {ActivityContextModule.class, MainActivityMvpModule.class}, dependencies= ApplicationComponent.class)
public interface ActivityComponent {

    @ActivityContext
    Context getContext();

    void injectMainActivity(MainActivity mainActivity);
}
