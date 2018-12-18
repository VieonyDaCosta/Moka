package com.example.vieony.mokapos.di.module;


import com.example.vieony.mokapos.di.scope.ApplicationScope;
import com.example.vieony.mokapos.retrofit.APIInterface;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Provides
    APIInterface getApiInterface(Retrofit retrofit){
        return retrofit.create(APIInterface.class);
    }

    @Provides
    @ApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").
                client(okHttpClient).
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

    @Provides
    @ApplicationScope
    public OkHttpClient getOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }

    @Provides
    @ApplicationScope
    public HttpLoggingInterceptor getLoggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
