package com.example.orderfoodapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.Adaptor.BanAdaptor;
import com.example.orderfoodapp.Adaptor.DonHangAdapter;
import com.example.orderfoodapp.Domain.FoodOrderItem;
import com.example.orderfoodapp.Domain.BanDomain;
import com.example.orderfoodapp.Database.ChiTietGoiMonDAO;  // Import DAO

import java.util.ArrayList;

public class EmployeeActivity extends AppCompatActivity {
    private RecyclerView recyclerViewBan;
    private ListView listViewDonHang; // Thay đổi RecyclerView thành ListView
    private BanAdaptor banAdaptor;
    private DonHangAdapter donHangAdapter;
    private ArrayList<BanDomain> listBan;
    private ArrayList<FoodOrderItem> cartList;
    private ChiTietGoiMonDAO chiTietGoiMonDAO;  // Khai báo DAO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_employee);

        recyclerViewBan = findViewById(R.id.recyclerViewBan);
        listViewDonHang = findViewById(R.id.listViewDonHang);

        listBan = new ArrayList<>();
        cartList = new ArrayList<>();

        // Gắn LayoutManager cho RecyclerView
        recyclerViewBan.setLayoutManager(new LinearLayoutManager(this));

        chiTietGoiMonDAO = new ChiTietGoiMonDAO(this);  // Khởi tạo ChiTietGoiMonDAO

        loadBanFromDatabase();

        // Lấy userId và tableId từ Intent
        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId", -1);
        int tableId = intent.getIntExtra("tableId", -1);

        if (tableId != -1) {
            loadOrderFromDatabase();  // Không cần truyền tableId và userId nữa
        }

        banAdaptor = new BanAdaptor(this, listBan, new BanAdaptor.OnItemClickListener() {
            @Override
            public void onStatusChange(BanDomain ban, boolean isChecked) {
                onBanUpdated(ban, isChecked);
                if (isChecked) {
                    loadOrderFromDatabase();  // Không cần truyền tableId và userId nữa
                } else {
                    listViewDonHang.setAdapter(null);
                }
            }
        });

        recyclerViewBan.setAdapter(banAdaptor);
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

    private void loadOrderFromDatabase() {
        ArrayList<FoodOrderItem> foodList = chiTietGoiMonDAO.getFoodOrderItems();  // Không cần tham số
        if (foodList != null && !foodList.isEmpty()) {
            donHangAdapter.updateData(foodList);
        }

        // Cập nhật dữ liệu lên ListView
        runOnUiThread(() -> {
            if (donHangAdapter == null) {
                donHangAdapter = new DonHangAdapter(this, foodList);
                listViewDonHang.setAdapter(donHangAdapter);
            } else {
                donHangAdapter.updateData(foodList); // Cập nhật dữ liệu mới cho adapter
            }
        });
    }

    private void onBanUpdated(BanDomain ban, boolean isChecked) {
        SQLiteDatabase database = openOrCreateDatabase("OrderFoodApp.db", MODE_PRIVATE, null);
        String newStatus = isChecked ? "Đang sử dụng" : "Trống";

        // Cập nhật trạng thái bàn trong cơ sở dữ liệu
        database.execSQL("UPDATE BANAN SET TINHTRANG = ? WHERE MABAN = ?", new Object[]{newStatus, ban.getId()});
        database.close();

        // Cập nhật trạng thái bàn trong danh sách
        ban.setTinhTrang(newStatus);

        // Tìm vị trí của bàn trong danh sách và cập nhật
        int position = listBan.indexOf(ban);
        if (position >= 0) {
            listBan.set(position, ban);
            banAdaptor.notifyItemChanged(position); // Cập nhật chỉ bàn đã thay đổi
        }

        Toast.makeText(this, "Cập nhật bàn " + ban.getId() + " thành công!", Toast.LENGTH_SHORT).show();
    }
}
