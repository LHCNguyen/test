package com.example.orderfoodapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.orderfoodapp.Model.MonAn;

import java.util.ArrayList;
import java.util.List;

public class MonAnDAO {
    private CreateDatabase createDatabase;

    public MonAnDAO(Context context) {
        createDatabase = new CreateDatabase(context);
    }

    // Phương thức thêm món ăn
    public boolean insertMonAn(String tenMonAn, String giaTien, int maLoai, String hinhAnhPath) {
        SQLiteDatabase db = createDatabase.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put("TENMONAN", tenMonAn);
        contentValues.put("GIATIEN", giaTien);
        contentValues.put("MALOAI", maLoai);
        contentValues.put("HINHANH", hinhAnhPath);

        long result = db.insert("MONAN", null, contentValues);
        db.close();

        return result != -1;
    }

    // Phương thức lấy danh sách món ăn
    public List<MonAn> getAllMonAn() {
        List<MonAn> monAnList = new ArrayList<>();
        SQLiteDatabase db = createDatabase.open();

        String query = "SELECT * FROM MONAN";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int maMonAn = cursor.getInt(cursor.getColumnIndexOrThrow("MAMONAN"));
                String tenMonAn = cursor.getString(cursor.getColumnIndexOrThrow("TENMONAN"));
                String giaTien = cursor.getString(cursor.getColumnIndexOrThrow("GIATIEN"));
                int maLoai = cursor.getInt(cursor.getColumnIndexOrThrow("MALOAI"));
                String hinhAnh = cursor.getString(cursor.getColumnIndexOrThrow("HINHANH"));

                MonAn monAn = new MonAn(maMonAn, tenMonAn, giaTien, maLoai, hinhAnh);
                monAnList.add(monAn);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return monAnList;
    }

    // Phương thức lấy món ăn theo mã loại
    public List<MonAn> getMonAnByLoai(int maLoai) {
        List<MonAn> monAnList = new ArrayList<>();
        SQLiteDatabase db = createDatabase.open();

        String query = "SELECT * FROM MONAN WHERE MALOAI = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(maLoai)});

        if (cursor.moveToFirst()) {
            do {
                int maMonAn = cursor.getInt(cursor.getColumnIndexOrThrow("MAMONAN"));
                String tenMonAn = cursor.getString(cursor.getColumnIndexOrThrow("TENMONAN"));
                String giaTien = cursor.getString(cursor.getColumnIndexOrThrow("GIATIEN"));
                String hinhAnh = cursor.getString(cursor.getColumnIndexOrThrow("HINHANH"));

                MonAn monAn = new MonAn(maMonAn, tenMonAn, giaTien, maLoai, hinhAnh);
                monAnList.add(monAn);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return monAnList;
    }

    // Phương thức xóa món ăn
    public boolean deleteMonAn(int maMonAn) {
        SQLiteDatabase db = createDatabase.open();
        int result = db.delete("MONAN", "MAMONAN = ?", new String[]{String.valueOf(maMonAn)});
        db.close();

        return result > 0;
    }

    // Phương thức cập nhật món ăn
    public boolean updateMonAn(int maMonAn, String tenMonAn, String giaTien, int maLoai, String hinhAnhPath) {
        SQLiteDatabase db = createDatabase.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put("TENMONAN", tenMonAn);
        contentValues.put("GIATIEN", giaTien);
        contentValues.put("MALOAI", maLoai);
        contentValues.put("HINHANH", hinhAnhPath);

        int result = db.update("MONAN", contentValues, "MAMONAN = ?", new String[]{String.valueOf(maMonAn)});
        db.close();

        return result > 0;
    }

    // Phương thức lấy món ăn theo mã
    public MonAn getMonAnById(int maMonAn) {
        SQLiteDatabase db = createDatabase.open();
        MonAn monAn = null;

        String query = "SELECT * FROM MONAN WHERE MAMONAN = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(maMonAn)});

        if (cursor.moveToFirst()) {
            String tenMonAn = cursor.getString(cursor.getColumnIndexOrThrow("TENMONAN"));
            String giaTien = cursor.getString(cursor.getColumnIndexOrThrow("GIATIEN"));
            int maLoai = cursor.getInt(cursor.getColumnIndexOrThrow("MALOAI"));
            String hinhAnh = cursor.getString(cursor.getColumnIndexOrThrow("HINHANH"));

            monAn = new MonAn(maMonAn, tenMonAn, giaTien, maLoai, hinhAnh);
        }

        cursor.close();
        db.close();

        return monAn;
    }
}
