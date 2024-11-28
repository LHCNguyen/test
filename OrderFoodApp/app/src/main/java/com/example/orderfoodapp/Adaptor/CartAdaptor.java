package com.example.orderfoodapp.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        // Kiểm tra nếu convertView null, nếu có thì tái sử dụng, nếu không sẽ tạo mới
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.viewholder_cart, parent, false);
        }

        // Lấy món ăn tại vị trí `position`
        FoodDomain food = cartList.get(position);

        // Gán dữ liệu vào các view trong `viewholder_cart.xml`
        TextView title = convertView.findViewById(R.id.cartTitle);
        TextView price = convertView.findViewById(R.id.cartPrice);
        ImageView imageView = convertView.findViewById(R.id.cartImage);

        // Cập nhật các thông tin món ăn
        title.setText(food.getTitle());
        price.setText(String.valueOf(food.getFee()));

        // Hiển thị hình ảnh bằng Glide
        Glide.with(context)
                .load(food.getPicpopular())
                .into(imageView);

        return convertView;
    }
}
