package com.example.vieony.mokapos.mvp.main.itemlist;

import com.example.vieony.mokapos.data.DBHelper;
import com.example.vieony.mokapos.mvp.main.MainActivityContract;
import com.example.vieony.mokapos.retrofit.APIInterface;

import javax.inject.Inject;

public class ItemListPresenterImpl implements ItemListContract.Presenter {

    private DBHelper dbHelper;
    private ItemListContract.View view;

    @Inject
    public ItemListPresenterImpl(DBHelper dbHelper, ItemListContract.View view){
        this.dbHelper = dbHelper;
        this.view = view;
    }

    @Override
    public void loadItems() {
        view.showItems(dbHelper.getAllItems());
    }
}
