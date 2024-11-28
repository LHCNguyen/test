package com.example.orderfoodapp.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.orderfoodapp.Domain.FoodDomain;
import com.example.orderfoodapp.R;

import java.util.ArrayList;

public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ViewHolder> {
    public ArrayList<FoodDomain> popularFood;
    private OnAddButtonClickListener onAddButtonClickListener;  // Listener để xử lý sự kiện nút "Add"
    private ArrayList<FoodDomain> cartList; // Giỏ hàng để lưu các món ăn đã chọn

    // Constructor để truyền vào danh sách món ăn và giỏ hàng
    public PopularAdaptor(ArrayList<FoodDomain> popularFood, OnAddButtonClickListener listener) {
        this.popularFood = popularFood;
        this.cartList = cartList;  // Lưu trữ giỏ hàng
        this.onAddButtonClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate view cho từng item trong RecyclerView
        View infalse = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new ViewHolder(infalse);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodDomain food = popularFood.get(position);
        // Thiết lập tên món ăn và giá
        holder.titlepopular.setText(food.getTitle());
        holder.fee.setText(String.valueOf(food.getFee()));

        // Lấy đường dẫn hình ảnh
        String imagePath = food.getPicpopular();
        if (imagePath != null) {
            if (imagePath.startsWith("http") || imagePath.startsWith("https")) {
                // Nếu là URL, sử dụng Glide để tải hình ảnh
                Glide.with(holder.itemView.getContext())
                        .load(imagePath)
                        .placeholder(R.drawable.nguoinauan)
                        .error(R.drawable.nguoinauan)
                        .into(holder.picpopular);
            } else {
                // Nếu là tài nguyên drawable, lấy resource ID
                int drawableResourceId = holder.itemView.getContext().getResources()
                        .getIdentifier(imagePath, "drawable", holder.itemView.getContext().getPackageName());
                if (drawableResourceId != 0) {
                    holder.picpopular.setImageResource(drawableResourceId);
                } else {
                    holder.picpopular.setImageResource(R.drawable.nguoinauan);
                }
            }
        } else {
            // Nếu không có hình ảnh, sử dụng ảnh mặc định
            holder.picpopular.setImageResource(R.drawable.nguoinauan);
        }

        // Xử lý sự kiện khi nhấn nút "Add"
        holder.addBtn.setOnClickListener(v -> {
            // Kiểm tra xem listener có null không và gọi callback khi nhấn nút "Add"
            if (onAddButtonClickListener != null) {
                onAddButtonClickListener.onAddButtonClick(position); // Truyền món ăn vào
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularFood.size();
    }

    // ViewHolder để chứa các thành phần trong một item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titlepopular, fee;
        ImageView picpopular;
        Button addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titlepopular = itemView.findViewById(R.id.titlepopular);
            fee = itemView.findViewById(R.id.fee);
            picpopular = itemView.findViewById(R.id.picpopular);
            addBtn = itemView.findViewById(R.id.addBtn);  // Lấy nút "Add"
        }
    }

    // Interface để xử lý sự kiện khi nhấn nút "Add"
    public interface OnAddButtonClickListener {
        // Xử lý sự kiện khi nhấn nút "Add"
        void onAddButtonClick(int position);


    }
}
