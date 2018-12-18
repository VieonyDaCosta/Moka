package com.example.vieony.mokapos.di.module;

import android.content.Context;

import com.example.vieony.mokapos.di.qualifier.ActivityContext;
import com.example.vieony.mokapos.di.scope.ActivityScope;
import com.example.vieony.mokapos.di.scope.FragmentScope;
import com.example.vieony.mokapos.mvp.main.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityContextModule {
    private MainActivity mainActivity;

    public Context context;

    public ActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainActivity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }
}
