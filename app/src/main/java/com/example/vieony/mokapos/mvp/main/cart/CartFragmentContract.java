package com.example.vieony.mokapos.mvp.main.cart;

import com.example.vieony.mokapos.model.CartItem;

import java.util.List;

public interface CartFragmentContract {

    interface View{
        void loadCartItems(List<CartItem> cartItemList);
    }

    interface Presenter{
        void addItemToCart(CartItem cartItem);
    }
}
