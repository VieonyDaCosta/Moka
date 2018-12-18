package com.example.vieony.mokapos.mvp.main.itemlist;

import com.example.vieony.mokapos.model.Item;

import java.util.List;

public interface ItemListContract {

    interface View {
        void showList(List<Item> list);
    }

    interface Presenter{
        void loadList();
    }
}
