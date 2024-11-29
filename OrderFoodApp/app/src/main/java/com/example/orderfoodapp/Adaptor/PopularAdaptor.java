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
    private OnAddButtonClickListener onAddButtonClickListener;

    public PopularAdaptor(ArrayList<FoodDomain> popularFood, OnAddButtonClickListener listener) {
        this.popularFood = popularFood;
        this.onAddButtonClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodDomain food = popularFood.get(position);

        // Cập nhật thông tin món ăn
        holder.titlepopular.setText(food.getTitle());
        holder.fee.setText(String.valueOf(food.getFee()));

        // Hiển thị hình ảnh món ăn
        String imagePath = food.getPicpopular();
        if (imagePath != null) {
            if (imagePath.startsWith("http") || imagePath.startsWith("https")) {
                Glide.with(holder.itemView.getContext())
                        .load(imagePath)
                        .placeholder(R.drawable.nguoinauan) // Hình ảnh mặc định khi đang tải
                        .error(R.drawable.nguoinauan) // Hình ảnh khi có lỗi
                        .into(holder.picpopular);
            } else {
                // Nếu là tài nguyên drawable
                int drawableResourceId = holder.itemView.getContext().getResources()
                        .getIdentifier(imagePath, "drawable", holder.itemView.getContext().getPackageName());
                holder.picpopular.setImageResource(drawableResourceId != 0 ? drawableResourceId : R.drawable.nguoinauan);
            }
        } else {
            holder.picpopular.setImageResource(R.drawable.nguoinauan); // Hình ảnh mặc định nếu không có đường dẫn
        }

        // Xử lý sự kiện nhấn nút "Thêm"
        holder.addBtn.setOnClickListener(v -> {
            if (onAddButtonClickListener != null) {
                onAddButtonClickListener.onAddButtonClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularFood.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titlepopular, fee;
        ImageView picpopular;
        Button addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titlepopular = itemView.findViewById(R.id.titlepopular);
            fee = itemView.findViewById(R.id.fee);
            picpopular = itemView.findViewById(R.id.picpopular);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }

    // Interface để xử lý sự kiện khi nhấn nút "Thêm"
    public interface OnAddButtonClickListener {
        void onAddButtonClick(int position);
    }
}
