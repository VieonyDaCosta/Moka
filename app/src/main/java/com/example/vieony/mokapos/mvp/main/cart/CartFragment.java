package com.example.vieony.mokapos.mvp.main.cart;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vieony.mokapos.R;
import com.example.vieony.mokapos.model.CartItem;
import com.example.vieony.mokapos.mvp.main.itemlist.ItemListFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements CartFragmentContract.View {

    @BindView(R.id.cartList)
    RecyclerView cartListRecycler;

    @BindView(R.id.btnClearCart)
    Button btnClearCart;

    @BindView(R.id.tvCharge)
    TextView tvCharge;

    @BindView(R.id.tvSubtotal)
    TextView tvSubtotal;

    @BindView(R.id.tvDiscount)
    TextView tvDiscount;

    private CartListAdapter cartListAdapter;

    private Context context;

    private CartFragmentPresenterImpl presenter;


    public static CartFragment newInstance() {
        Bundle args = new Bundle();
        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);
        cartListRecycler.setLayoutManager(new LinearLayoutManager(context));
        cartListAdapter = new CartListAdapter(context);
        cartListRecycler.setAdapter(cartListAdapter);
       presenter = new CartFragmentPresenterImpl(this);
       presenter.loadCartItems();
       setRetainInstance(true);
        return view;
    }

    public void addCartItem(CartItem cartItem){
        presenter.addItemToCart(cartItem);
    }


    @Override
    public void refreshCartItems(List<CartItem> cartItemList) {
        cartListAdapter.setData(cartItemList);
    }

    @Override
    public void refreshCharges(String subTotal, String discount, String charge) {
        tvSubtotal.setText(subTotal);
        tvDiscount.setText(discount);
        tvCharge.setText(charge);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    public void setPresenter(CartFragmentPresenterImpl presenter){
        this.presenter = presenter;
    }


}
