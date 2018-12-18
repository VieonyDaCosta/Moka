package com.example.vieony.mokapos.mvp.main.additem;

import com.example.vieony.mokapos.model.Item;

public class AddItemFragmentPresenterImp implements AddItemFragmentContract.Presenter {

    private AddItemFragmentContract.View view;

    private int quantity;
    private Item item;

    public AddItemFragmentPresenterImp(AddItemFragmentContract.View view){
        this.view = view;
    }

    @Override
    public void setItem(Item item) {
        this.item = item;
        view.showItem(item);
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        view.refreshQuantity(quantity);
    }


}
