package com.example.vieony.mokapos.mvp.main.cart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vieony.mokapos.R;
import com.example.vieony.mokapos.model.CartItem;
import com.example.vieony.mokapos.model.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private List<CartItem> data;
    private Context context;
    private ClickListener clickListener;

    public interface ClickListener {
        void showEditItemDialog(CartItem item);
    }


    public CartListAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CartItem cartItem = data.get(position);
        holder.title.setText(cartItem.getItem().getTitle());
        holder.quantity.setText(String.format("x%d",cartItem.getQuantity()));
        holder.price.setText(cartItem.getFormattedPriceWithoutDiscount());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.showEditItemDialog(cartItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle)
        TextView title;

        @BindView(R.id.tvQuantity)
        TextView quantity;

        @BindView(R.id.tvPrice)
        TextView price;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setData(List<CartItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
