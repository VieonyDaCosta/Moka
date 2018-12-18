package com.example.vieony.mokapos.mvp.main;

import android.support.v4.app.Fragment;
import android.view.View;

import com.example.vieony.mokapos.mvp.main.cart.CartFragment;
import com.example.vieony.mokapos.mvp.main.discountlist.DiscountListFragment;
import com.example.vieony.mokapos.mvp.main.itemlist.ItemListFragment;
import com.example.vieony.mokapos.mvp.main.library.LibraryFragment;
import com.example.vieony.mokapos.retrofit.model.Item;

public interface MainActivityContract {

    interface View{
        void loadLibraryFragment(LibraryFragment fragment);
        void loadItemListFragment(ItemListFragment fragment);
        void loadDiscountListFragment(DiscountListFragment fragment);
        void loadICartFragment(CartFragment fragment);
    }

    interface Presenter{
        void loadItemList();
        void loadLeftFragment(int type);
        void loadCartFragment();
    }
}
