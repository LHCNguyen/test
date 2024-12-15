package com.example.orderfoodapp.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.Model.MonAn;
import com.example.orderfoodapp.R;
import com.example.orderfoodapp.fragment.RepairFoodFragment;

import java.util.List;

public class EditMonAnAdapter extends RecyclerView.Adapter<EditMonAnAdapter.ViewHolder> {

    private Context context;
    private List<MonAn> monAnList;
    private RepairFoodFragment fragment;

    // Constructor cần đảm bảo luôn truyền fragment vào
    public EditMonAnAdapter(Context context, List<MonAn> monAnList, RepairFoodFragment fragment) {
        this.context = context;
        this.monAnList = monAnList;
        this.fragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MonAn monAn = monAnList.get(position);
        holder.tvTenMonAn.setText(monAn.getTenMonAn());
        holder.tvGiaTien.setText(monAn.getGiaTien());

        // Xử lý sự kiện sửa món ăn
        holder.btnEdit.setOnClickListener(v -> {
            if (fragment != null) {
                fragment.openEditDialog(monAn);  // Gọi phương thức từ fragment
            }
        });

        // Xử lý sự kiện xóa món ăn
        holder.btnDelete.setOnClickListener(v -> {
            if (fragment != null) {
                fragment.showDeleteConfirmation(monAn.getMaMonAn());  // Gọi phương thức xác nhận xóa
            }
        });
    }

    @Override
    public int getItemCount() {
        return monAnList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTenMonAn, tvGiaTien;
        public Button btnEdit, btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTenMonAn = itemView.findViewById(R.id.tvTenMonAn);
            tvGiaTien = itemView.findViewById(R.id.tvGiaTien);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
