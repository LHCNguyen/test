package com.example.orderfoodapp.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderfoodapp.Domain.BanDomain;
import com.example.orderfoodapp.R;
import java.util.ArrayList;

public class BanAdaptor extends RecyclerView.Adapter<BanAdaptor.BanViewHolder> {
    private Context context;
    private ArrayList<BanDomain> listBan;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onStatusChange(BanDomain ban, boolean isChecked);
    }

    public BanAdaptor(Context context, ArrayList<BanDomain> listBan, OnItemClickListener listener) {
        this.context = context;
        this.listBan = listBan;
        this.listener = listener;
    }

    @Override
    public BanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_ban, parent, false);
        return new BanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BanViewHolder holder, int position) {
        final BanDomain ban = listBan.get(position);
        holder.textViewTenBan.setText(ban.getName());
        holder.checkBoxTinhTrang.setChecked(ban.getTinhTrang().equals("Đang sử dụng"));
        holder.checkBoxTinhTrang.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Hoãn thay đổi trạng thái cho đến khi RecyclerView đã hoàn tất việc tính toán layout
            holder.itemView.post(() -> listener.onStatusChange(ban, isChecked));
        });
    }

    @Override
    public int getItemCount() {
        return listBan.size();
    }

    public class BanViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTenBan;
        CheckBox checkBoxTinhTrang;

        public BanViewHolder(View itemView) {
            super(itemView);
            textViewTenBan = itemView.findViewById(R.id.textViewBanName);
            checkBoxTinhTrang = itemView.findViewById(R.id.checkBoxOccupied);
        }
    }
}
