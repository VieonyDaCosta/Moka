package com.example.vieony.mokapos.mvp.main.itemlist;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.vieony.mokapos.GlideApp;
import com.example.vieony.mokapos.R;
import com.example.vieony.mokapos.model.Item;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Item> data;
    private RecyclerViewAdapter.ClickListener clickListener;
    private Context context;

    @Inject
    public RecyclerViewAdapter(Context context, ClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item item = data.get(position);
        holder.title.setText(item.getTitle());
        holder.price.setText(item.getFormattedPrice());
        Glide.with(context)
                .load(item.getThumbnailUrl())
                .thumbnail(0.5f)
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .into(holder.thumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.showAddItemDialog(item);
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

        @BindView(R.id.thumbnail)
        ImageView thumbnail;

        @BindView(R.id.tvPrice)
        TextView price;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ClickListener {
        void showAddItemDialog(Item item);
    }

    public void setData(List<Item> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}

