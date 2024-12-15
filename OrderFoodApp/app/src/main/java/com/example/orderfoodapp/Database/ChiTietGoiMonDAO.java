package com.example.orderfoodapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.orderfoodapp.Domain.FoodOrderItem;

import java.util.ArrayList;
import java.util.List;

public class ChiTietGoiMonDAO {
    private CreateDatabase db;

    public ChiTietGoiMonDAO(Context context) {
        this.db = new CreateDatabase(context);
    }

    public ArrayList<FoodOrderItem> getFoodOrderItems() {
        ArrayList<FoodOrderItem> foodOrderItems = new ArrayList<>();
        SQLiteDatabase database = db.getReadableDatabase();

        // Truy vấn lấy MAMONAN và SOLUONG từ bảng CHITIETGOIMON mà không có điều kiện WHERE
        String query = "SELECT MAMONAN, SOLUONG FROM CHITIETGOIMON";

        Cursor cursor = database.rawQuery(query, null);  // Không cần tham số WHERE

        if (cursor.moveToFirst()) {
            do {
                int maMonAn = cursor.getInt(cursor.getColumnIndex("MAMONAN"));
                int soLuong = cursor.getInt(cursor.getColumnIndex("SOLUONG"));

                // Chỉ sử dụng giá trị mặc định cho tên món và giá
                String tenMonAn = "Món ăn " + maMonAn;  // Hoặc có thể để trống
                double giaTien = 100000;  // Hoặc giá mặc định

                // Tạo đối tượng FoodOrderItem
                FoodOrderItem foodOrderItem = new FoodOrderItem(tenMonAn, giaTien, soLuong);
                foodOrderItems.add(foodOrderItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return foodOrderItems;
    }


}
