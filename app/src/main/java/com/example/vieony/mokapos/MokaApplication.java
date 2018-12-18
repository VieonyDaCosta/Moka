package com.example.vieony.mokapos;

import android.app.Activity;
import android.app.Application;

import com.example.vieony.mokapos.di.component.ApplicationComponent;
import com.example.vieony.mokapos.di.component.DaggerApplicationComponent;
import com.example.vieony.mokapos.di.module.ContextModule;

public class MokaApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static MokaApplication get(Activity activity){
        return (MokaApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
