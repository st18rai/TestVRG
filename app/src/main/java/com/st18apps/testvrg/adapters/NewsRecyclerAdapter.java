package com.st18apps.testvrg.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.st18apps.testvrg.R;
import com.st18apps.testvrg.model.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsHolder> {
    private List<Result> data;
    private ItemClickListener itemClickListener;

    public NewsRecyclerAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public List<Result> getData() {
        return data;
    }

    public void setData(List<Result> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        final NewsHolder holder = new NewsHolder(view);

        holder.layout.setOnClickListener(view1 ->
                itemClickListener.onItemClick(holder.getAdapterPosition()));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsHolder holder, int position) {

        Result result = data.get(position);

        holder.title.setText(result.getTitle());
        holder.date.setText(result.getPublishedDate());

        Glide.with(holder.getContext())
                .load(result.getMedia().get(0).getMediaMetadata().get(2).getUrl())
                .into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    static class NewsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView_title)
        TextView title;

        @BindView(R.id.textView_date)
        TextView date;

        @BindView(R.id.imageView)
        ImageView photo;

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
