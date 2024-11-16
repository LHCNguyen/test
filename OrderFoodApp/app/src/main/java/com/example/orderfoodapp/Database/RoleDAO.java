package com.example.orderfoodapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.orderfoodapp.Model.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleDAO {
    private SQLiteDatabase database;
    private CreateDatabase createDatabase;

    public RoleDAO(Context context) {
        createDatabase = new CreateDatabase(context);
        database = createDatabase.getWritableDatabase();
    }

    // Lấy danh sách tất cả vai trò
    public List<Role> layTatCaVaiTro() {
        List<Role> danhSachVaiTro = new ArrayList<>();
        String query = "SELECT * FROM " + CreateDatabase.TB_ROLE;
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int maRole = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_ROLE_MAROLE));
                String chucVu = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_ROLE_CHUCVU));
                String moTa = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_ROLE_MOTA));

                Role role = new Role(maRole, chucVu, moTa);
                danhSachVaiTro.add(role);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return danhSachVaiTro;
    }

    // Lấy thông tin vai trò theo mã
    public Role layRoleTheoMa(int maRole) {
        Role role = null;
        String query = "SELECT * FROM " + CreateDatabase.TB_ROLE + " WHERE " + CreateDatabase.TB_ROLE_MAROLE + " = ?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(maRole)});

        if (cursor.moveToFirst()) {
            String chucVu = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_ROLE_CHUCVU));
            String moTa = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_ROLE_MOTA));
            role = new Role(maRole, chucVu, moTa);
        }
        cursor.close();
        return role;
    }

    // Thêm vai trò mới
    public boolean themRole(Role role) {
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.TB_ROLE_CHUCVU, role.getChucVu());
        values.put(CreateDatabase.TB_ROLE_MOTA, role.getMoTa());

        long result = database.insert(CreateDatabase.TB_ROLE, null, values);
        return result != -1;
    }

    // Xóa vai trò
    public boolean xoaRole(int maRole) {
        int result = database.delete(CreateDatabase.TB_ROLE, CreateDatabase.TB_ROLE_MAROLE + " = ?", new String[]{String.valueOf(maRole)});
        return result > 0;
    }
}
