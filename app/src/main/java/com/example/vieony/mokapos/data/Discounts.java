package com.example.vieony.mokapos.data;

import android.content.Context;

import com.example.vieony.mokapos.R;
import com.example.vieony.mokapos.model.Discount;

import java.util.ArrayList;
import java.util.List;

public class Discounts {

    private static Discounts instance = null;
    private ArrayList<Discount> discounts;

    public static synchronized Discounts getContext(Context context){
        if(instance == null){
            instance = new Discounts(context);
        }
        return instance;
    }


    private Discounts(Context context){
        discounts = new ArrayList<>();
        createDiscounts(context);
    }

    private  void  createDiscounts(Context context){
        discounts.add(new Discount(1,  context.getString(R.string.discount_a), 0));
        discounts.add(new Discount(2,  context.getString(R.string.discount_b), 10));
        discounts.add(new Discount(3,  context.getString(R.string.discount_c), 35.5));
        discounts.add(new Discount(4,  context.getString(R.string.discount_d), 50));
        discounts.add(new Discount(5,  context.getString(R.string.discount_e), 100));
    }

    public ArrayList<Discount> getDiscounts(){
        return discounts;
    }

}
