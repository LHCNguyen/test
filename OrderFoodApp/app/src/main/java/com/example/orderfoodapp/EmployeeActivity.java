package com.example.orderfoodapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderfoodapp.Domain.BanDomain;
import com.example.orderfoodapp.Adaptor.BanAdaptor;
import java.util.ArrayList;

public class EmployeeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BanAdaptor banAdaptor;
    private ArrayList<BanDomain> listBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_employee);

        recyclerView = findViewById(R.id.recyclerViewBan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listBan = new ArrayList<>();
        loadBanFromDatabase();

        // Truyền context và listener vào constructor của BanAdaptor
        banAdaptor = new BanAdaptor(this, listBan, new BanAdaptor.OnItemClickListener() {
            @Override
            public void onStatusChange(BanDomain ban, boolean isChecked) {
                onBanUpdated(ban, isChecked);
            }
        });

        recyclerView.setAdapter(banAdaptor);
    }

    private void loadBanFromDatabase() {
        SQLiteDatabase database = openOrCreateDatabase("OrderFoodApp.db", MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT MABAN, TENBAN, TINHTRANG FROM BANAN", null);

        if (cursor.moveToFirst()) {
            do {
                int maBan = cursor.getInt(0);
                String tenBan = cursor.getString(1);
                String tinhTrang = cursor.getString(2);
                listBan.add(new BanDomain(maBan, tenBan, tinhTrang));
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
    }

    private void onBanUpdated(BanDomain ban, boolean isChecked) {
        SQLiteDatabase database = openOrCreateDatabase("OrderFoodApp.db", MODE_PRIVATE, null);
        String newStatus = isChecked ? "Đang sử dụng" : "Trống";  // Sửa lại theo trạng thái isChecked

        database.execSQL("UPDATE BANAN SET TINHTRANG = ? WHERE MABAN = ?", new Object[]{newStatus, ban.getId()});
        database.close();

        ban.setTinhTrang(newStatus); // Cập nhật trạng thái của bàn
        recyclerView.post(() -> {
            banAdaptor.notifyDataSetChanged();
        });

        Toast.makeText(this, "Cập nhật bàn " + ban.getTenBan() + " thành công!", Toast.LENGTH_SHORT).show();
    }
}
