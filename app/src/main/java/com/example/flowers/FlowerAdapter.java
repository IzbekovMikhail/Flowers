package com.example.flowers;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.ViewHolder> {
    private final static String PHOTO_URL = "https://services.hanselandpetal.com/photos/";
    private List<Flower> mFlowers;
    private Context mContext;

    FlowerAdapter(List<Flower> flowers) {
        this.mFlowers = flowers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Flower flower = mFlowers.get(position);
        holder.nameTextView.setText(flower.getName());

        Picasso.with(mContext)
                .load(PHOTO_URL + flower.getPhoto())
                .resize(200, 150)
                .into(holder.flowerImageView);
    }

    @Override
    public int getItemCount() {
        if (mFlowers == null)
            return 0;
        return mFlowers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView flowerImageView;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            flowerImageView = itemView.findViewById(R.id.itemImageView);
        }
    }
}
