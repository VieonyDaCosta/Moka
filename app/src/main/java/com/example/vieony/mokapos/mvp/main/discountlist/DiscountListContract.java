package com.example.vieony.mokapos.mvp.main.discountlist;

import com.example.vieony.mokapos.model.Discount;


import java.util.List;

public class DiscountListContract {
    interface View {
        void showDiscounts(List<Discount> list);
    }

    interface Presenter{
        void loadDiscounts();
    }


}
