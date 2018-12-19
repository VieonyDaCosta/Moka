package com.example.vieony.mokapos.mvp.main.cart;

import com.example.vieony.mokapos.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public interface CartFragmentContract {

    interface View{
        void refreshCartItems(List<CartItem> cartItemList);
        void refreshCharges(String subTotal, String discount, String charge);
    }

    interface Presenter{
        void addItemToCart(CartItem cartItem);
        void loadCartItems();
        ArrayList<CartItem> getCartItems();
        void clearCart();
        void editCartItem(CartItem cartItem);
    }
}
