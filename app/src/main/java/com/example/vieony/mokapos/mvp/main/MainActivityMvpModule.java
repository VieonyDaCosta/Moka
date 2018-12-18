package com.example.vieony.mokapos.mvp.main;

import com.example.vieony.mokapos.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityMvpModule {

    private final MainActivityContract.View mView;


    public MainActivityMvpModule(MainActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    MainActivityContract.View provideView() {
        return mView;
    }
}
