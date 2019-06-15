package com.st18apps.testvrg.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.st18apps.testvrg.R;
import com.st18apps.testvrg.model.NewsData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsHolder> {
    private List<NewsData> data;
    private ItemClickListener itemClickListener;

    public NewsRecyclerAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public List<NewsData> getData() {
        return data;
    }

    public void setData(List<NewsData> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void updateLike(int position){
        if (data.get(position).isLiked()) {
            data.get(position).setLiked(false);
        } else {
            data.get(position).setLiked(true);
        }

        notifyItemChanged(position);
    }

    public void deleteItem(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }


    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        final NewsHolder holder = new NewsHolder(view);

        holder.layout.setOnClickListener(view1 ->
                itemClickListener.onItemClick(holder.getAdapterPosition()));

        holder.like.setOnClickListener(view2 ->
                itemClickListener.onLikeClick(holder.getAdapterPosition()));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsHolder holder, int position) {

        NewsData result = data.get(position);

        Drawable likeImage = result.isLiked() ? ContextCompat.getDrawable(holder.getContext(),
                R.drawable.ic_favorite_full_24dp) : ContextCompat.getDrawable(holder.getContext(),
                R.drawable.ic_favorite_border_24dp);

        holder.title.setText(result.getTitle());
        holder.date.setText(result.getPublishedDate());
        holder.like.setImageDrawable(likeImage);

        Glide.with(holder.getContext())
                .load(result.getImageUrl())
                .into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public interface ItemClickListener {
        void onItemClick(int position);

        void onLikeClick(int position);
    }

    static class NewsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView_title)
        TextView title;

        @BindView(R.id.textView_date)
        TextView date;

        @BindView(R.id.imageView_photo)
        ImageView photo;

        @BindView(R.id.imageView_like)
        ImageView like;

        private View layout;

        NewsHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            layout = v;
        }

        public Context getContext() {
            return layout.getContext();
        }
    }
}
