package com.example.orderfoodapp.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.orderfoodapp.Domain.MonAnDomain;
import com.example.orderfoodapp.R;

import java.util.List;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.MonAnViewHolder> {
    private List<MonAnDomain> monAnList;
    private Context context;

    public MonAnAdapter(Context context, List<MonAnDomain> monAnList) {
        this.context = context;
        this.monAnList = monAnList;
    }

    @NonNull
    @Override
    public MonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_monan, parent, false);
        return new MonAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnViewHolder holder, int position) {
        MonAnDomain monAn = monAnList.get(position);
        holder.tvTenMonAn.setText(monAn.getTenMonAn());
        holder.tvSoLuongMonAn.setText("Số lượng: " + monAn.getSoLuong());

        // Sử dụng Glide để tải hình ảnh
        Glide.with(context).load(monAn.getHinhAnh()).into(holder.imgMonAn);
    }

    @Override
    public int getItemCount() {
        return monAnList.size();
    }

    public static class MonAnViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenMonAn, tvSoLuongMonAn;
        ImageView imgMonAn;

        public MonAnViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenMonAn = itemView.findViewById(R.id.tvTenMonAn);
            tvSoLuongMonAn = itemView.findViewById(R.id.tvSoLuongMonAn);
            imgMonAn = itemView.findViewById(R.id.imgMonAn);
        }
    }
}
