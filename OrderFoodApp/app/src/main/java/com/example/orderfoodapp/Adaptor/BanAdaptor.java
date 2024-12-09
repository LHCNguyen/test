package com.example.orderfoodapp.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderfoodapp.Domain.BanDomain;
import com.example.orderfoodapp.R;

import java.util.ArrayList;

public class BanAdaptor extends RecyclerView.Adapter<BanAdaptor.BanViewHolder> {
    private Context context;
    private ArrayList<BanDomain> danhSachBan;
    private OnItemClickListener listener;

    public BanAdaptor(Context context, ArrayList<BanDomain> danhSachBan, OnItemClickListener listener) {
        this.context = context;
        this.danhSachBan = danhSachBan;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ban, parent, false);
        return new BanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BanViewHolder holder, int position) {
        BanDomain ban = danhSachBan.get(position);
        holder.tenBan.setText(ban.getTenBan());
        holder.tinhTrang.setText(ban.getTinhTrang());
        holder.trangThai.setChecked(ban.getTinhTrang().equals("Đang sử dụng"));

        holder.trangThai.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (listener != null) {
                listener.onStatusChange(ban, isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhSachBan.size();
    }

    public interface OnItemClickListener {
        void onStatusChange(BanDomain ban, boolean isChecked);
    }

    static class BanViewHolder extends RecyclerView.ViewHolder {
        TextView tenBan, tinhTrang;
        CheckBox trangThai;

        public BanViewHolder(@NonNull View itemView) {
            super(itemView);
            tenBan = itemView.findViewById(R.id.tenBan);
            tinhTrang = itemView.findViewById(R.id.tinhTrangBan);
            trangThai = itemView.findViewById(R.id.checkBoxTrangThai);
        }
    }
}
