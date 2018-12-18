package com.example.vieony.mokapos.mvp.main.discountlist;


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
import com.example.vieony.mokapos.data.Discounts;
import com.example.vieony.mokapos.model.Discount;
import com.example.vieony.mokapos.mvp.main.itemlist.ItemListAdapter;
import com.example.vieony.mokapos.mvp.main.itemlist.ItemListFragment;
import com.example.vieony.mokapos.mvp.main.itemlist.ItemListPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscountListFragment extends Fragment implements DiscountListContract.View{

    private Context context;
    @BindView(R.id.discountList)
    RecyclerView discountList;

    private DiscountListAdapter discountListAdapter;

    public DiscountListFragment() {
        // Required empty public constructor
    }

    public static DiscountListFragment newInstance() {
        Bundle args = new Bundle();
        DiscountListFragment fragment = new DiscountListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discount_list, container, false);
        ButterKnife.bind(this, view);
        discountList.setLayoutManager(new LinearLayoutManager(context));
        discountListAdapter = new DiscountListAdapter(context);
        discountList.setAdapter(discountListAdapter);
        DiscountListPresenterImpl presenter = new DiscountListPresenterImpl( Discounts.getContext(context), this);
        presenter.loadDiscounts();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void showDiscounts(List<Discount> list) {
        discountListAdapter.setData(list);
    }
}
