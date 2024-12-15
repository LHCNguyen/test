package com.example.orderfoodapp.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;

import com.example.orderfoodapp.R;
import com.example.orderfoodapp.Domain.FoodOrderItem;

import java.util.ArrayList;

public class DonHangAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<FoodOrderItem> cartList;

    public DonHangAdapter(Context context, ArrayList<FoodOrderItem> cartList) {
        this.context = context;
        this.cartList = cartList != null ? cartList : new ArrayList<>(); // Kiểm tra null và khởi tạo nếu cần
    }

    @Override
    public int getCount() {
        return cartList.size(); // Số lượng món ăn trong giỏ hàng
    }

    @Override
    public Object getItem(int position) {
        return cartList.get(position); // Trả về món ăn tại vị trí cụ thể
    }

    @Override
    public long getItemId(int position) {
        return position; // Trả về ID của món ăn tại vị trí
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        // Sử dụng ViewHolder để tối ưu hiệu suất
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.viewholder_donhang, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewFoodName = convertView.findViewById(R.id.tvFoodName);
            viewHolder.textViewFoodQuantity = convertView.findViewById(R.id.tvFoodQuantity);
            viewHolder.textViewFoodPrice = convertView.findViewById(R.id.tvFoodPrice); // Thêm giá món ăn
            convertView.setTag(viewHolder); // Lưu ViewHolder để sử dụng lại
        } else {
            viewHolder = (ViewHolder) convertView.getTag(); // Lấy ViewHolder đã lưu lại
        }

        FoodOrderItem food = cartList.get(position);

        // Gán dữ liệu vào các TextView
        viewHolder.textViewFoodName.setText(food.getName()); // Tên món ăn
        viewHolder.textViewFoodQuantity.setText("Số lượng: " + food.getQuantity()); // Số lượng món ăn
        viewHolder.textViewFoodPrice.setText("Giá: " + food.getPrice()); // Giá món ăn

        return convertView;
    }

    // Cập nhật dữ liệu cho ListView
    public void updateData(ArrayList<FoodOrderItem> newFoodOrderItems) {
        this.cartList.clear();  // Xóa tất cả dữ liệu cũ
        this.cartList.addAll(newFoodOrderItems);  // Thêm dữ liệu mới vào danh sách
        notifyDataSetChanged();  // Cập nhật ListView
    }

    // ViewHolder lớp tĩnh để lưu trữ các View trong mỗi item của ListView
    static class ViewHolder {
        TextView textViewFoodName;
        TextView textViewFoodQuantity;
        TextView textViewFoodPrice; // Thêm TextView cho giá món ăn
    }
}
