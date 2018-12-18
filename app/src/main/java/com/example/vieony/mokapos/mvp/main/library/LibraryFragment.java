package com.example.vieony.mokapos.mvp.main.library;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vieony.mokapos.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LibraryFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.discounts)
    ConstraintLayout viewDiscounts;

    @BindView(R.id.items)
    ConstraintLayout viewItems;

    private LibraryFragmentCallback callback;

    public interface LibraryFragmentCallback{
        void onItemListClicked();
        void onDiscountListClicked();
    }

    public static LibraryFragment newInstance() {
        Bundle args = new Bundle();
        LibraryFragment fragment = new LibraryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void SetCallback(LibraryFragmentCallback callback){
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_library, container, false);
        ButterKnife.bind(this, view);
        viewItems.setOnClickListener(this);
        viewDiscounts.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(callback == null){
            return;
        }
        switch (v.getId()){
            case R.id.items:
                callback.onItemListClicked();
                break;

            case R.id.discounts:
                callback.onDiscountListClicked();
                break;
        }
    }
}
