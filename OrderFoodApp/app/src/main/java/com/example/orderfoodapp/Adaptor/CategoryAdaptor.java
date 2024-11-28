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

        // Kiểm tra và tải hình ảnh
        String imagePath = category.getImage();
        if (imagePath != null) {
            if (imagePath.startsWith("http") || imagePath.startsWith("/")) {
                // Hình ảnh là URL hoặc đường dẫn file
                Glide.with(holder.itemView.getContext())
                        .load(imagePath)
                        .placeholder(R.drawable.picturesignup_transparent) // Ảnh mặc định khi tải
                        .error(R.drawable.picturesignup_transparent) // Ảnh hiển thị nếu lỗi
                        .into(holder.categoryPic);
            } else {
                // Hình ảnh là tên tài nguyên drawable
                @SuppressLint("DiscouragedApi") int resourceId = holder.itemView.getContext().getResources()
                        .getIdentifier(imagePath, "drawable", holder.itemView.getContext().getPackageName());

                // Kiểm tra xem tài nguyên có tồn tại không
                if (resourceId != 0) {
                    holder.categoryPic.setImageResource(resourceId);
                } else {
                    // Nếu không tìm thấy tài nguyên, sử dụng ảnh mặc định
                    holder.categoryPic.setImageResource(R.drawable.picturesignup_transparent);
                }
            }
        } else {
            // Nếu không có hình ảnh, sử dụng ảnh mặc định
            holder.categoryPic.setImageResource(R.drawable.picturesignup_transparent);
        }

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
