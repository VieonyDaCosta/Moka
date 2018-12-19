package com.example.vieony.mokapos.mvp.main.additem;

import android.widget.CompoundButton;

import com.example.vieony.mokapos.data.Discounts;
import com.example.vieony.mokapos.model.CartItem;
import com.example.vieony.mokapos.model.Discount;
import com.example.vieony.mokapos.model.Item;

import java.text.DecimalFormat;
import java.util.ArrayList;

import utils.Utils;

public class AddItemFragmentPresenterImp implements AddItemFragmentContract.Presenter {

    private AddItemFragmentContract.View view;

    private int quantity;
    private Item item;
    private Discount discount;
    private ArrayList<Discount> discountList;
    DecimalFormat df = new DecimalFormat("#.##");
    private boolean isEdit = false;


    public AddItemFragmentPresenterImp(Discounts discounts, AddItemFragmentContract.View view){
        this.view = view;
        this.discountList = discounts.getDiscounts();
    }

    @Override
    public void setItem(Item item) {
        this.item = item;
        view.showItem(item);
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        view.showQuantity(quantity);
    }

    @Override
    public void setQuantity(String quantity) {
        this.quantity = Utils.convertStringToInt(quantity);
        view.showQuantity(this.quantity);
    }

    @Override
    public void incrementQuantity() {
        if(quantity == 999){
            return;
        }
        quantity++;
        view.showQuantity(quantity);
    }

    @Override
    public void decrementQuantity() {
        if(quantity == 1){
            return;
        }
        quantity--;
        view.showQuantity(quantity);
    }

    @Override
    public void displayQuantity() {
        view.showQuantity(quantity);
    }

    @Override
    public ArrayList<Discount> getDiscounts() {
        return discountList;
    }


    @Override
    public void clearPreviousDiscount() {
        discount = null;
        view.clearPreviousDiscount();
    }

    @Override
    public void setDiscount(int position) {
        discount = discountList.get(position);
    }

    @Override
    public void addItemToCart(CartItem cartItem) {
        view.onCartItemAdded(cartItem);
    }

    @Override
    public Discount getDiscount() {
        return discount;
    }

    @Override
    public String getDiscountText(int position) {
        Discount discount = discountList.get(position);
        return String.format("%s (%s%%)", discount.getName(), df.format(discount.getPercentage()));
    }

    @Override
    public void setEditItem(boolean edit) {
        isEdit = edit;
    }

    @Override
    public boolean isEditItem() {
        return isEdit;
    }

    @Override
    public void setDiscount(Discount discount) {
        this.discount = discount;
        for(int i= 0; i <  discountList.size(); i++){
            if(discount == discountList.get(i)){
                view.showDiscount(i);
                return;
            }
        }

    }
}
