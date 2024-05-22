package com.example.flowers;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.ViewHolder> {
    private List<Flower> flowers;

    FlowerAdapter(List<Flower> flowers) {
        this.flowers = flowers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Flower flower = flowers.get(position);
        holder.flowerTextView.setText(flower.toString());
    }

    @Override
    public int getItemCount() {
        if (flowers == null)
            return 0;
        return flowers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView flowerTextView;

        ViewHolder(View itemView) {
            super(itemView);
            flowerTextView = itemView.findViewById(R.id.textView_item_flower);
        }
    }
}
