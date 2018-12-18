package com.example.vieony.mokapos.mvp.main.additem;

import com.example.vieony.mokapos.model.Item;

public interface AddItemFragmentContract {

    interface View {
        void showItem(Item item);
        void refreshQuantity(int quantity);
    }

    interface Presenter{
        void setItem(Item item);
        void setQuantity(int quantity);
        void incrementQuantity();
        void decrementQuantity();
        void displayQuantity();
    }
}
