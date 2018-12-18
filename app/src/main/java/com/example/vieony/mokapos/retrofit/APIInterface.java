package com.example.vieony.mokapos.retrofit;



import com.example.vieony.mokapos.retrofit.model.Item;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface APIInterface {
    @GET("photos")
    Observable<List<Item>> getData();
}
