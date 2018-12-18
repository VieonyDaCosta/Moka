package com.example.vieony.mokapos.mvp.main.itemlist;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vieony.mokapos.R;
import com.example.vieony.mokapos.data.DBHelper;
import com.example.vieony.mokapos.model.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemListFragment extends Fragment implements ItemListContract.View{

    private Context context;
    @BindView(R.id.itemList)
    RecyclerView itemList;

    private ItemListAdapter itemListAdapter;
    private ItemListAdapter.ClickListener clickListener;

    public static ItemListFragment newInstance() {
        Bundle args = new Bundle();
        ItemListFragment fragment = new ItemListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ItemListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);

        itemList.setLayoutManager(new LinearLayoutManager(context));
        itemListAdapter = new ItemListAdapter(context, clickListener);
        itemList.setAdapter(itemListAdapter);
        ItemListPresenterImpl presenter = new ItemListPresenterImpl(new DBHelper(context), this);
        presenter.loadItems();
        return view;
    }

    @Override
    public void showItems(List<Item> list) {
        itemListAdapter.setData(list);
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    public void setItemClickListener(ItemListAdapter.ClickListener clickListener){
        this.clickListener = clickListener;
    }

}
