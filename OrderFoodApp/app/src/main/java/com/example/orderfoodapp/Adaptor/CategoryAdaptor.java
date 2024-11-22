package com.example.orderfoodapp.Adaptor;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.orderfoodapp.Domain.CategoryDomain;
import com.example.orderfoodapp.R;

import java.util.ArrayList;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder> {
    private ArrayList<CategoryDomain> categoryDomains;
    private OnCategoryClickListener onCategoryClickListener;

    // Constructor nhận thêm onCategoryClickListener và gán cho biến thành viên
    public CategoryAdaptor(ArrayList<CategoryDomain> categoryDomains, OnCategoryClickListener onCategoryClickListener) {
        this.categoryDomains = categoryDomains;
        this.onCategoryClickListener = onCategoryClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View infalse = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(infalse);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryDomain category = categoryDomains.get(position);

        // Thiết lập tên danh mục
        holder.categoryName.setText(category.getName());

        // Hiển thị hình ảnh từ URL hoặc tài nguyên drawable
        Glide.with(holder.itemView.getContext())
                .load(category.getImage()) // Giả sử category.getImage() trả về URL hoặc tên tài nguyên
                .into(holder.categoryPic);

        // Sự kiện khi người dùng click vào category
        holder.itemView.setOnClickListener(v -> {
            if (onCategoryClickListener != null) {
                onCategoryClickListener.onCategoryClick(position); // Truyền position (hoặc ID nếu có) của category
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            mainLayout = itemView.findViewById(R.id.mainLayoutHolder);
        }
    }

    // Interface để xử lý sự kiện click vào category
    public interface OnCategoryClickListener {
        void onCategoryClick(int categoryId);
    }
}
