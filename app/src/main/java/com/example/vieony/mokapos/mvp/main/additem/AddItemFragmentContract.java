package com.example.vieony.mokapos.mvp.main.additem;

import com.example.vieony.mokapos.model.CartItem;
import com.example.vieony.mokapos.model.Discount;
import com.example.vieony.mokapos.model.Item;

import java.util.ArrayList;

public interface AddItemFragmentContract {

    interface View {
        void showItem(Item item);
        void showQuantity(int quantity);
        void clearPreviousDiscount();
        void onCartItemAdded(CartItem cartItem);
    }

    interface Presenter{
        void setItem(Item item);
        void setQuantity(int quantity);
        void incrementQuantity();
        void decrementQuantity();
        void displayQuantity();
        ArrayList<Discount> getDiscounts();
        void clearPreviousDiscount();
        void setDiscount(int position);
        void addItemToCart(CartItem cartItem);
    }
}
