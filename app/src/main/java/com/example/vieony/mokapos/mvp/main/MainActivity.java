package com.example.vieony.mokapos.mvp.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vieony.mokapos.MokaApplication;
import com.example.vieony.mokapos.R;
import com.example.vieony.mokapos.di.component.ActivityComponent;
import com.example.vieony.mokapos.di.component.ApplicationComponent;
import com.example.vieony.mokapos.di.component.DaggerActivityComponent;
import com.example.vieony.mokapos.di.module.ActivityContextModule;
import com.example.vieony.mokapos.di.qualifier.ActivityContext;
import com.example.vieony.mokapos.model.CartItem;
import com.example.vieony.mokapos.model.Item;
import com.example.vieony.mokapos.mvp.main.additem.AddItemFragment;
import com.example.vieony.mokapos.mvp.main.cart.CartFragment;
import com.example.vieony.mokapos.mvp.main.discountlist.DiscountListFragment;
import com.example.vieony.mokapos.mvp.main.itemlist.ItemListAdapter;
import com.example.vieony.mokapos.mvp.main.itemlist.ItemListFragment;
import com.example.vieony.mokapos.mvp.main.library.LibraryFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View,
        LibraryFragment.LibraryFragmentCallback, ItemListAdapter.ClickListener,
        AddItemFragment.CartItemListener{

    ActivityComponent mainActivityComponent;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    MainActivityPresenterImpl presenter;

    private CartFragment cartFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApplicationComponent applicationComponent = MokaApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerActivityComponent.builder()
                .activityContextModule(new ActivityContextModule(this))
                .applicationComponent(applicationComponent)
                .mainActivityMvpModule(new MainActivityMvpModule(this))
                .build();

        mainActivityComponent.injectMainActivity(this);

        presenter.loadItemList();
        if(savedInstanceState == null){
            presenter.loadLeftFragment(1);
            presenter.loadCartFragment();
        }

    }

    private void addFragment(int container,Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .add(container, fragment)
                .commit();
    }

    private void replaceFragment(int container,Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(container, fragment)
                .addToBackStack(fragment.getTag())
                .commit();
    }

    @Override
    public void onItemListClicked() {
        presenter.loadLeftFragment(2);
    }

    @Override
    public void onDiscountListClicked() {
        presenter.loadLeftFragment(3);
    }

    @Override
    public void loadLibraryFragment(LibraryFragment fragment) {
        fragment.SetCallback(this);
        addFragment(R.id.leftFrame, fragment);
    }

    @Override
    public void loadItemListFragment(ItemListFragment fragment) {
        fragment.setItemClickListener(this);
        replaceFragment(R.id.leftFrame, fragment);
    }

    @Override
    public void loadDiscountListFragment(DiscountListFragment fragment) {
        replaceFragment(R.id.leftFrame, fragment);
    }

    @Override
    public void loadICartFragment(CartFragment fragment) {
        addFragment(R.id.rightFrame, fragment);
    }

    @Override
    public void showAddItemDialog(Item item) {
        AddItemFragment addItemFragment = AddItemFragment.newInstance(item);
        addItemFragment.setCartItemListener(this);
        addItemFragment.show(getSupportFragmentManager(), AddItemFragment.class.getName());
    }

    @Override
    public void onCartItemAdded(CartItem cartItem) {

    }
}
