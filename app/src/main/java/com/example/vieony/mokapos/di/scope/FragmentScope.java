package com.example.vieony.mokapos.di.scope;

import android.content.Context;

import com.example.vieony.mokapos.di.qualifier.ActivityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

import dagger.Provides;

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface FragmentScope {

}
