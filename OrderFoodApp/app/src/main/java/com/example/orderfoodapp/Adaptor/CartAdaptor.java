package com.example.orderfoodapp.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.example.orderfoodapp.CartActivity;
import com.example.orderfoodapp.Domain.FoodDomain;
import com.example.orderfoodapp.R;

import java.util.ArrayList;

public class CartAdaptor extends BaseAdapter {
    private Context context;
    private ArrayList<FoodDomain> cartList;

    public CartAdaptor(Context context, ArrayList<FoodDomain> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @Override
    public int getCount() {
        return cartList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.viewholder_cart, null);
        }

        ImageView imageViewFood = convertView.findViewById(R.id.imageViewFood);
        TextView textViewFoodTitle = convertView.findViewById(R.id.textViewFoodTitle);
        TextView textViewFoodPrice = convertView.findViewById(R.id.textViewFoodPrice);
        final TextView textViewQuantity = convertView.findViewById(R.id.textViewQuantity);
        Button buttonIncrease = convertView.findViewById(R.id.buttonIncrease);
        Button buttonDecrease = convertView.findViewById(R.id.buttonDecrease);
        ImageButton buttonRemove = convertView.findViewById(R.id.buttonRemove);

        final FoodDomain food = cartList.get(position);

        // Set thông tin món ăn
        textViewFoodTitle.setText(food.getTitle());
        textViewFoodPrice.setText(food.getFee() + " VND");
        textViewQuantity.setText(String.valueOf(food.getQuantity()));

        // Xử lý tăng số lượng
        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food.setQuantity(food.getQuantity() + 1);
                textViewQuantity.setText(String.valueOf(food.getQuantity()));
                // Cập nhật tổng giá giỏ hàng khi số lượng thay đổi
                ((CartActivity) context).updateTotalPrice();
            }
        });

        // Xử lý giảm số lượng
        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food.getQuantity() > 1) {
                    food.setQuantity(food.getQuantity() - 1);
                    textViewQuantity.setText(String.valueOf(food.getQuantity()));
                    // Cập nhật tổng giá giỏ hàng khi số lượng thay đổi
                    ((CartActivity) context).updateTotalPrice();
                }
            }
        });

        // Xử lý xóa món ăn khỏi giỏ hàng
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartList.remove(position);
                notifyDataSetChanged();
                // Cập nhật tổng giá giỏ hàng khi món ăn bị xóa
                ((CartActivity) context).updateTotalPrice();
            }
        });

        return convertView;
    }
}
