package com.example.orderfoodapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.orderfoodapp.Model.User;
import com.example.orderfoodapp.Utils.PasswordUtils;

public class UserDAO {

    private CreateDatabase database;

    public UserDAO(Context context) {
        // Khởi tạo đối tượng CreateDatabase
        this.database = new CreateDatabase(context);
    }

    // Insert User vào database
    public long insertUSER(User user) {
        SQLiteDatabase db = database.open();
        ContentValues values = new ContentValues();

        // Mã hóa mật khẩu trước khi lưu
        String matKhauDaBam = PasswordUtils.bamMatKhau(user.getMatKhau());

        values.put(CreateDatabase.TB_USER_TENDN, user.getTenDangNhap());
        values.put(CreateDatabase.TB_USER_MATKHAU, matKhauDaBam);
        values.put(CreateDatabase.TB_USER_GIOITINH, user.getGioiTinh());
        values.put(CreateDatabase.TB_USER_NGAYSINH, user.getNgaySinh());
        values.put(CreateDatabase.TB_USER_EMAIL, user.getEmail());
        values.put(CreateDatabase.TB_USER_MAROLE, user.getMaRole());

        long result = db.insert(CreateDatabase.TB_USER, null, values);
        db.close();  // Đảm bảo đóng cơ sở dữ liệu sau khi sử dụng
        return result;
    }

    // Kiểm tra xem tên đăng nhập đã tồn tại chưa
    public boolean kiemTraTenDangNhap(String tenDN) {
        SQLiteDatabase db = database.open();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CreateDatabase.TB_USER +
                " WHERE " + CreateDatabase.TB_USER_TENDN + " =?", new String[]{tenDN});

        boolean tonTai = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return tonTai;
    }

    // Kiểm tra đăng nhập bằng tên và mật khẩu
    public User kiemTraDangNhap(String tenDangNhap, String matKhau) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        User user = null;

        try {
            db = database.open();  // Mở cơ sở dữ liệu

            // Mã hóa mật khẩu người dùng nhập vào
            String matKhauNDN = PasswordUtils.bamMatKhau(matKhau);

            // Truy vấn cơ sở dữ liệu để kiểm tra tên đăng nhập và mật khẩu
            cursor = db.rawQuery("SELECT * FROM " + CreateDatabase.TB_USER +
                            " WHERE " + CreateDatabase.TB_USER_TENDN + " =? AND " + CreateDatabase.TB_USER_MATKHAU + " =?",
                    new String[]{tenDangNhap, matKhauNDN});

            if (cursor != null && cursor.moveToFirst()) {
                // Lấy dữ liệu từ cursor và tạo đối tượng User
                String tenDangNhapResult = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_USER_TENDN));
                String matKhauResult = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_USER_MATKHAU));
                String gioiTinh = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_USER_GIOITINH));
                String ngaySinh = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_USER_NGAYSINH));
                String email = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_USER_EMAIL));
                int maRole = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_USER_MAROLE)); // Lấy mã role

                user = new User(tenDangNhapResult, matKhauResult, gioiTinh, ngaySinh, email, maRole);
            } else {
                Log.e("UserDAO", "No user found with username: " + tenDangNhap);
            }
        } catch (Exception e) {
            Log.e("UserDAO", "Error checking login", e);
        } finally {
            if (cursor != null) cursor.close();  // Đảm bảo đóng cursor
            if (db != null && db.isOpen()) db.close();  // Đảm bảo đóng database
        }
        return user;  // Trả về đối tượng User nếu tìm thấy, nếu không trả về null
    }
}
