package com.example.vieony.mokapos.mvp.main.cart;

import com.example.vieony.mokapos.model.CartItem;

import java.util.ArrayList;

public class CartFragmentPresenterImpl implements CartFragmentContract.Presenter {

    private ArrayList<CartItem> cartItemsList;
    private CartFragmentContract.View view;

    public CartFragmentPresenterImpl(CartFragmentContract.View view) {
        this.view = view;
        cartItemsList = new ArrayList<>();
    }

    @Override
    public void addItemToCart(CartItem cartItem) {
        boolean found = false;
        for (CartItem cartItem1: cartItemsList){
            if((cartItem.getItem() == cartItem1.getItem()) && (cartItem.getDiscount() == cartItem1.getDiscount())){
                cartItem1.setQuantity(cartItem1.getQuantity()+cartItem.getQuantity());
                found = true;
                break;
            }
        }
        if(!found){
            cartItemsList.add(cartItem);
        }

        view.refreshCartItems(cartItemsList);
        view.refreshCharges(getFormattedSubTotal(), getFormattedDiscount(), getFormattedCharge());
    }

    public void setCartItemsList(ArrayList<CartItem> cartItemsList) {
        this.cartItemsList = cartItemsList;
    }

    @Override
    public void loadCartItems() {
        view.refreshCartItems(cartItemsList);
    }

    @Override
    public ArrayList<CartItem> getCartItems() {
        return cartItemsList;
    }

    public String getFormattedSubTotal() {

        return String.format("$%.0f", getSubTotal());
    }

    public double getSubTotal() {

        if (cartItemsList.size() == 0) {
            return 0;
        }
        double subTotal = 0;

        for (CartItem cartItem : cartItemsList) {
            subTotal += subTotal + cartItem.getPriceWithoutDiscount();
        }
        return subTotal;
    }

    public String getFormattedDiscount() {
        return String.format("$%.0f", getDiscount());
    }

    public double getDiscount() {
        if (cartItemsList.size() == 0) {
            return 0;
        }
        double discount = 0;

        for (CartItem cartItem : cartItemsList) {
            discount += discount + cartItem.getDiscountOnItem();
        }
        return discount;
    }

    public String getFormattedCharge() {
        return String.format("$%.0f", getCharge());
    }

    public double getCharge() {
        double charge = getSubTotal() - getDiscount();;
        return charge;
    }

}
