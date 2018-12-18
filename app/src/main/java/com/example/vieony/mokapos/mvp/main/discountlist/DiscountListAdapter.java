package com.example.vieony.mokapos.mvp.main.discountlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vieony.mokapos.R;
import com.example.vieony.mokapos.model.Discount;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscountListAdapter extends RecyclerView.Adapter<DiscountListAdapter.ViewHolder> {

    private List<Discount> data;
    private Context context;


    public DiscountListAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.discount_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Discount discount = data.get(position);
        holder.title.setText(discount.getName());
        holder.discount.setText(discount.getFormattedPercentage());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle)
        TextView title;

        @BindView(R.id.tvDiscount)
        TextView discount;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setData(List<Discount> data) {
        this.data = data;
        notifyDataSetChanged();
    }

}
