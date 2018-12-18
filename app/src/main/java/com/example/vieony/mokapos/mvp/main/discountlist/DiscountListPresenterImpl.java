package com.example.vieony.mokapos.mvp.main.discountlist;

import android.content.Context;

import com.example.vieony.mokapos.data.Discounts;
import com.example.vieony.mokapos.model.Discount;

import java.util.ArrayList;

public class DiscountListPresenterImpl implements DiscountListContract.Presenter {

    private DiscountListContract.View view;
    private Discounts discounts;

    public DiscountListPresenterImpl(Discounts discounts, DiscountListContract.View view){
        this.view = view;
        this.discounts = discounts;
    }

    @Override
    public void loadDiscounts() {
        view.showDiscounts(discounts.getDiscounts());
    }
}
