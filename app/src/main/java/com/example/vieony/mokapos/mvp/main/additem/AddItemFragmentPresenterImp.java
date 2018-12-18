package com.example.vieony.mokapos.mvp.main.additem;

import com.example.vieony.mokapos.data.Discounts;
import com.example.vieony.mokapos.model.CartItem;
import com.example.vieony.mokapos.model.Discount;
import com.example.vieony.mokapos.model.Item;

import java.util.ArrayList;

public class AddItemFragmentPresenterImp implements AddItemFragmentContract.Presenter {

    private AddItemFragmentContract.View view;

    private int quantity;
    private Item item;
    private Discount discount;
    private ArrayList<Discount> discountList;


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
    public void incrementQuantity() {
        quantity++;
    }

    @Override
    public void decrementQuantity() {
        quantity--;
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


}
