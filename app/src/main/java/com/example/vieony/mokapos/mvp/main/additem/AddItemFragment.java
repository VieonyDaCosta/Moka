package com.example.vieony.mokapos.mvp.main.additem;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.vieony.mokapos.R;

import utils.InputFilterMinMax;
import utils.Utils;
import com.example.vieony.mokapos.data.Discounts;
import com.example.vieony.mokapos.model.CartItem;
import com.example.vieony.mokapos.model.Discount;
import com.example.vieony.mokapos.model.Item;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddItemFragment extends DialogFragment implements
        AddItemFragmentContract.View, View.OnClickListener,
        CompoundButton.OnCheckedChangeListener, TextWatcher {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.btnIncrement)
    ImageButton btnIncrement;

    @BindView(R.id.btnDecrement)
    ImageButton btnDecrement;

    @BindView(R.id.etQuantity)
    EditText etQuantity;

    @BindView(R.id.switchDiscount0)
    Switch switchDiscount0;

    @BindView(R.id.switchDiscount1)
    Switch switchDiscount1;

    @BindView(R.id.switchDiscount2)
    Switch switchDiscount2;

    @BindView(R.id.switchDiscount3)
    Switch switchDiscount3;

    @BindView(R.id.switchDiscount4)
    Switch switchDiscount4;

    @BindView(R.id.btnSave)
    Button btnSave;

    @BindView(R.id.btnCancel)
    Button btnCancel;


    public static final String ITEM = "item";
    public static final String CART_ITEM = "cart_item";

    private CartItem cartItem;

    private AddItemFragmentPresenterImp presenter;

    private Context context;

    private CartItemListener cartItemListener;

    private boolean isEdit = false;


    public interface CartItemListener{
        void onCartItemAdded(CartItem cartItem, boolean isEdit);
    }

    public static AddItemFragment newInstance(Item item) {
        AddItemFragment fragment = new AddItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    public static AddItemFragment newInstance(CartItem item) {
        AddItemFragment fragment = new AddItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(CART_ITEM, item);
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
        cartItem = new CartItem();
        if (args != null) {
            Item item = (Item) args.getSerializable(ITEM);
            if (item != null) {
                cartItem.setItem(item);
            }
            CartItem cartItem = (CartItem) args.getSerializable(CART_ITEM);
            if (cartItem != null) {
                this.cartItem = cartItem;
                isEdit = true;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);
        ButterKnife.bind(this, view);
        switchDiscount0.setOnCheckedChangeListener(this);
        switchDiscount1.setOnCheckedChangeListener(this);
        switchDiscount2.setOnCheckedChangeListener(this);
        switchDiscount3.setOnCheckedChangeListener(this);
        switchDiscount4.setOnCheckedChangeListener(this);
        presenter = new AddItemFragmentPresenterImp(Discounts.getContext(context), this);
        presenter.setItem(cartItem.getItem());
        presenter.setQuantity(cartItem.getQuantity());
        presenter.setDiscount(cartItem.getDiscount());
        presenter.setEditItem(isEdit);
        btnIncrement.setOnClickListener(this);
        btnDecrement.setOnClickListener(this);
        initialiseDiscounts();
        btnSave.setOnClickListener(this);
        etQuantity.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 1000)});
        etQuantity.addTextChangedListener(this);
        btnCancel.setOnClickListener(this);
        return view;
    }

    private void initialiseDiscounts(){
        switchDiscount0.setText(presenter.getDiscountText(0));
        switchDiscount1.setText(presenter.getDiscountText(1));
        switchDiscount2.setText(presenter.getDiscountText(2));
        switchDiscount3.setText(presenter.getDiscountText(3));
        switchDiscount4.setText(presenter.getDiscountText(4));
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void showItem(Item item) {
        tvTitle.setText(String.format("%s - %s",item.getTitle().substring(0, 5), item.getFormattedPrice()));
    }

    @Override
    public void showQuantity(int quantity) {
        etQuantity.removeTextChangedListener(this);
        etQuantity.setText(String.format("%d", quantity));
        etQuantity.addTextChangedListener(this);
    }

    @Override
    public void showDiscount(int position) {
        switch(position){
            case 0 :
                switchDiscount0.setChecked(true);
                break;
            case 1 :
                switchDiscount1.setChecked(true);
                break;
            case 2 :
                switchDiscount2.setChecked(true);
                break;
            case 3 :
                switchDiscount3.setChecked(true);
                break;
            case 4 :
                switchDiscount4.setChecked(true);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIncrement:
                presenter.incrementQuantity();
                break;

            case R.id.btnDecrement:
                presenter.decrementQuantity();
                break;

            case R.id.btnSave:
                cartItem.setQuantity(Utils.convertStringToInt(etQuantity.getText().toString()));
                cartItem.setDiscount(presenter.getDiscount());
                presenter.addItemToCart(cartItem);
                dismiss();
                break;

            case R.id.btnCancel:
                dismiss();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            presenter.clearPreviousDiscount();
            clearPreviousDiscount(buttonView);
            switch (buttonView.getId()){
                case R.id.switchDiscount0:
                    presenter.setDiscount(0);
                    break;
                case R.id.switchDiscount1:
                    presenter.setDiscount(1);
                    break;
                case R.id.switchDiscount2:
                    presenter.setDiscount(2);
                    break;
                    case R.id.switchDiscount3:
                    presenter.setDiscount(3);
                    break;
                case R.id.switchDiscount4:
                    presenter.setDiscount(4);
                    break;
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void clearPreviousDiscount() {
    }

    public void clearPreviousDiscount(CompoundButton compoundButton) {
        if(compoundButton == switchDiscount0){
            setSwitchCheckedFalse(switchDiscount1, switchDiscount2, switchDiscount3, switchDiscount4);
        } else if(compoundButton == switchDiscount1){
            setSwitchCheckedFalse(switchDiscount0, switchDiscount2, switchDiscount3, switchDiscount4);
        } else if(compoundButton == switchDiscount2){
            setSwitchCheckedFalse(switchDiscount0, switchDiscount1, switchDiscount3, switchDiscount4);
        } else if(compoundButton == switchDiscount3){
            setSwitchCheckedFalse(switchDiscount0, switchDiscount1, switchDiscount2, switchDiscount4);
        } else if(compoundButton == switchDiscount4){
            setSwitchCheckedFalse(switchDiscount0, switchDiscount1, switchDiscount2, switchDiscount3);
        }

    }
    private void setSwitchCheckedFalse(Switch switch1, Switch switch2, Switch switch3, Switch switch4){
        switch1.setChecked(false);
        switch2.setChecked(false);
        switch3.setChecked(false);
        switch4.setChecked(false);
    }


    public void setCartItemListener(CartItemListener cartItemListener){
        this.cartItemListener = cartItemListener;
    }

    @Override
    public void onCartItemAdded(CartItem cartItem) {
        if(cartItemListener != null){
            cartItemListener.onCartItemAdded(cartItem, presenter.isEditItem());
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        presenter.setQuantity(s.toString());
    }


}
