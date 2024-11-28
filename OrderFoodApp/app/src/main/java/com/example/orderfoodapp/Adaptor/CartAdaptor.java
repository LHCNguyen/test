package com.example.orderfoodapp.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.orderfoodapp.Domain.FoodDomain;
import com.example.orderfoodapp.R;

import java.util.ArrayList;

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.ViewHolder> {
    private ArrayList<FoodDomain> cartList;

    public CartAdaptor(ArrayList<FoodDomain> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodDomain food = cartList.get(position);
        holder.title.setText(food.getTitle());
        holder.price.setText(String.valueOf(food.getFee()));

        // Nếu có hình ảnh, bạn có thể hiển thị ảnh ở đây, sử dụng Glide
        Glide.with(holder.itemView.getContext())
                .load(food.getPicpopular())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, price;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cartTitle);
            price = itemView.findViewById(R.id.cartPrice);
            imageView = itemView.findViewById(R.id.cartImage);
        }
    }
}
