package com.example.vieony.mokapos.mvp.main.additem;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vieony.mokapos.R;
import com.example.vieony.mokapos.model.Item;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddItemFragment extends DialogFragment implements AddItemFragmentContract.View {


    @BindView(R.id.tvTitle)
    TextView tvTitle;

    public static final String ITEM = "item";

    private Item item;

    private AddItemFragmentPresenterImp presenter;


    public static AddItemFragment newInstance(Item item){
        AddItemFragment fragment = new AddItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    public AddItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            item = (Item) args.getSerializable(ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_item, container, false);
        ButterKnife.bind(this, view);
        presenter = new AddItemFragmentPresenterImp(this);
        presenter.setItem(item);
        return view;
    }

    @Override
    public void showItem(Item item) {
        tvTitle.setText(item.getTitle());
    }

    @Override
    public void refreshQuantity(int quantity) {

    }
}
