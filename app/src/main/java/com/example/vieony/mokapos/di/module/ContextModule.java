package com.example.vieony.mokapos.di.module;

import android.content.Context;

import com.example.vieony.mokapos.di.qualifier.ApplicationContext;
import com.example.vieony.mokapos.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }

}
