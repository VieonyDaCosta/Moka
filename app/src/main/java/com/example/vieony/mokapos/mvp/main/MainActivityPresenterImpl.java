package com.example.vieony.mokapos.mvp.main;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.vieony.mokapos.data.DBHelper;
import com.example.vieony.mokapos.mvp.main.cart.CartFragment;
import com.example.vieony.mokapos.mvp.main.discountlist.DiscountListFragment;
import com.example.vieony.mokapos.mvp.main.itemlist.ItemListFragment;
import com.example.vieony.mokapos.mvp.main.library.LibraryFragment;
import com.example.vieony.mokapos.retrofit.APIInterface;
import com.example.vieony.mokapos.retrofit.model.Item;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivityPresenterImpl implements MainActivityContract.Presenter {

    int leftType = 0;
    private MainActivityContract.View view;
    private  APIInterface apiInterface;
    private DBHelper dbHelper;

    @Inject
    public MainActivityPresenterImpl(APIInterface apiInterface, DBHelper dbHelper, MainActivityContract.View view){
        this.apiInterface = apiInterface;
        this.dbHelper = dbHelper;
        this.view = view;
    }

    @Override
    public void loadItemList() {
        apiInterface.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Item>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<Item> data) {
                        try {
                            System.out.println("processing item on thread"+Thread.currentThread());
                            dbHelper.insertItem(data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void loadLeftFragment(int type) {
        leftType = type;
        switch (type){
            case 1:
                LibraryFragment libraryFragment =  LibraryFragment.newInstance();
                view.loadLibraryFragment(libraryFragment);
                break;
            case 2:
                view.loadItemListFragment(ItemListFragment.newInstance());
                break;
            case 3:
                view.loadDiscountListFragment(DiscountListFragment.newInstance());
                break;
        }

    }

    @Override
    public void loadCartFragment() {
        view.loadICartFragment(CartFragment.newInstance());
    }
}
