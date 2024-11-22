package com.example.orderfoodapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.orderfoodapp.Model.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoryDAO {

    private SQLiteDatabase database;
    private CreateDatabase createDatabase;

    public CategoryDAO(Context context) {
        this.createDatabase = new CreateDatabase(context);
    }


    // Mở kết nối cơ sở dữ liệu
    public void open() {
        this.database = createDatabase.getWritableDatabase();  // Mở kết nối với cơ sở dữ liệu
    }

    // Đóng kết nối
    public void close() {
        createDatabase.close();
    }

    // Lấy danh sách các LoaiMonAn từ cơ sở dữ liệu
    public List<Category> getAllLoaiMonAn() {
        List<Category> loaiMonAnList = new ArrayList<>();

        // Câu truy vấn SQL để lấy danh sách các LoaiMonAn
        String query = "SELECT * FROM LOAIMONAN";  // Câu lệnh SQL cho bảng LOAIMONAN
        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int maLoai = cursor.getInt(cursor.getColumnIndex("MALOAI"));
                    String tenLoai = cursor.getString(cursor.getColumnIndex("TENLOAI"));
                    String moTa = cursor.getString(cursor.getColumnIndex("MOTA"));

                    Category category = new Category(maLoai, tenLoai, moTa);
                    loaiMonAnList.add(category);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return loaiMonAnList;
    }

    // Thêm một LoaiMonAn vào cơ sở dữ liệu
    public long addLoaiMonAn(Category category) {
        ContentValues values = new ContentValues();
        values.put("TENLOAI", category.getTenLoai());
        values.put("MOTA", category.getMoTa());

        return database.insert("LOAIMONAN", null, values);
    }
}
