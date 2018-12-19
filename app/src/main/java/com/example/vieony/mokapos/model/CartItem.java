package com.example.vieony.mokapos.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Item item;
    private int quantity = 1;
    private Discount discount;

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public Discount getDiscount() {
        return discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPriceWithoutDiscount() {
        return quantity * item.price();
    }

    public double getDiscountOnItem(){
        return  getPriceWithoutDiscount() - getPriceWithDiscount();
    }

    public String getFormattedPriceWithoutDiscount() {
        return String.format("$%.0f", getPriceWithoutDiscount());
    }

    public double getPriceWithDiscount() {

        if (this.discount == null || this.discount.getPercentage() == 0) {
            return getPriceWithoutDiscount();
        }

        return quantity * (item.price() - item.price() * discount.getPercentage() / 100);
    }
}

