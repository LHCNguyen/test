package com.example.orderfoodapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bumptech.glide.util.Util;
import com.example.orderfoodapp.Model.User;
import com.example.orderfoodapp.Utils.PasswordUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CreateDatabase extends SQLiteOpenHelper {

    public static String TB_USER = "USER";
    public static String TB_MONAN = "MONAN";
    public static String TB_LOAIMONAN = "LOAIMONAN";
    public static String TB_BANAN = "BANAN";
    public static String TB_GOIMON = "GOIMON";
    public static String TB_CHITIETGOIMON = "CHITIETGOIMON";
    public static String TB_ROLE = "ROLE";
    public static String TB_NGUYENLIEU = "NGUYENLIEU";
    public static String TB_NGUYENLIEUMONAN = "NGUYENLIEUMONAN";
    public static String TB_PHUONGTHUCTHANHTOAN = "PHUONGTHUCTHANHTOAN";

    public static String TB_USER_MAUSER = "MAUSER";
    public static String TB_USER_TENDN = "TENDN";
    public static String TB_USER_MATKHAU = "MATKHAU";
    public static String TB_USER_GIOITINH = "GIOITINH";
    public static String TB_USER_NGAYSINH = "NGAYSINH";
    public static String TB_USER_EMAIL = "EMAIL";
    public static String TB_USER_MAROLE = "MAROLE";
    public static String TB_USER_SDT = "SDT";
    public static String TB_USER_FULLNAME = "FULLNAME";
    public static String TB_USER_NGAYTAO = "NGAYTAO";
    public static String TB_USER_DIEMTICHLUY = "DIEMTICHLUY";

    public static String TB_MONAN_MAMONAN = "MAMONAN";
    public static String TB_MONAN_TENMONAN = "TENMONAN";
    public static String TB_MONAN_GIATIEN = "GIATIEN";
    public static String TB_MONAN_MALOAI = "MALOAI";
    public static String TB_MONAN_HINHANH = "HINHANH";
    public static String TB_MONAN_MANGUYENLIEU = "MANGUYENLIEU";

    public static String TB_LOAIMONAN_MALOAI = "MALOAI";
    public static String TB_LOAIMONAN_TENLOAI = "TENLOAI";
    public static String TB_LOAIMONAN_MOTA = "MOTA";

    public static String TB_BANAN_MABAN = "MABAN";
    public static String TB_BANAN_TENBAN = "TENBAN";
    public static String TB_BANAN_TINHTRANG = "TINHTRANG";

    public static String TB_GOIMON_MAGOIMON = "MAGOIMON";
    public static String TB_GOIMON_MAUSER = "MAUSER";
    public static String TB_GOIMON_NGAYGOI = "NGAYGOI";
    public static String TB_GOIMON_MABAN = "MABAN";
    public static String TB_GOIMON_NGAYTHANHTOAN = "NGAYTHANHTOAN";
    public static String TB_GOIMON_TONGSOMON = "TONGSOMON";
    public static String TB_GOIMON_TONGTIEN = "TONGTIEN";
    public static String TB_GOIMON_THUAVAT = "THUEVAT";
    public static String TB_GOIMON_MAPHUONGTHUCTHANHTOAN = "MAPHUONGTHUCTHANHTOAN";

    public static String TB_CHITIETGOIMON_MAGOIMON = "MAGOIMON";
    public static String TB_CHITIETGOIMON_MAMONAN = "MAMONAN";
    public static String TB_CHITIETGOIMON_SOLUONG = "SOLUONG";
    public static String TB_CHITIETGOIMON_TRANGTHAI = "TRANGTHAI";

    public static String TB_ROLE_MAROLE = "MAROLE";
    public static String TB_ROLE_CHUCVU = "CHUCVU";
    public static String TB_ROLE_MOTA = "MOTA";

    public static String TB_NGUYENLIEU_MANGUYENLIEU = "MANGUYENLIEU";
    public static String TB_NGUYENLIEU_TENNGUYENLIEU = "TENNGUYENLIEU";
    public static String TB_NGUYENLIEU_KHOILUONG = "KHOILUONG";
    public static String TB_NGUYENLIEU_MOTTA = "MOTA";

    public static String TB_NGUYENLEUMONAN_MAMONAN = "MAMONAN";
    public static String TB_NGUYENLIEUMONAN_MANGUYENLIEU = "MANGUYENLIEU";

    public static String TB_PHUONGTHUCTHANHTOAN_MAPTTH = "MAPTTH";
    public static String TB_PHUONGTHUCTHANHTOAN_NAME = "NAME";


    public CreateDatabase(Context context) {
        super(context, "OrderFoodApp", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbUSER = "CREATE TABLE " + TB_USER + " ( "
                + TB_USER_MAUSER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_USER_TENDN + " TEXT, "
                + TB_USER_MATKHAU + " TEXT, "
                + TB_USER_GIOITINH + " TEXT, "
                + TB_USER_NGAYSINH + " TEXT, "
                + TB_USER_EMAIL + " TEXT , "
                + TB_USER_DIEMTICHLUY + " TEXT , "
                + TB_USER_NGAYTAO + " TEXT, "
                + TB_USER_FULLNAME + " TEXT, "
                + TB_USER_SDT + " TEXT, "
                + TB_USER_MAROLE + " INTEGER )";

        String tbBANAN = "CREATE TABLE " + TB_BANAN + " ( "
                + TB_BANAN_MABAN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_BANAN_TENBAN + " TEXT, "
                + TB_BANAN_TINHTRANG + " TEXT )";

        String tbMONAN = "CREATE TABLE " + TB_MONAN + " ( "
                + TB_MONAN_MAMONAN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_MONAN_TENMONAN + " TEXT NOT NULL, "
                + TB_MONAN_MALOAI + " INTEGER, "
                + TB_MONAN_GIATIEN + " TEXT NOT NULL,"
                + TB_MONAN_HINHANH + " TEXT, "
                + TB_MONAN_MANGUYENLIEU + " INTEGER)";

        String tbLOAIMONAN = "CREATE TABLE " + TB_LOAIMONAN + " ( "
                + TB_LOAIMONAN_MALOAI + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_LOAIMONAN_TENLOAI + " TEXT ,"
                + TB_LOAIMONAN_MOTA + " TEXT )";

        String tbGOIMON = "CREATE TABLE " + TB_GOIMON + " ( "
                + TB_GOIMON_MAGOIMON + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_GOIMON_MABAN + " INTEGER, "
                + TB_GOIMON_MAUSER + " INTEGER, "
                + TB_GOIMON_NGAYGOI + " TEXT, "
                + TB_GOIMON_NGAYTHANHTOAN + " TEXT,"
                + TB_GOIMON_TONGSOMON + " TEXT ,"
                + TB_GOIMON_TONGTIEN + " TEXT, "
                + TB_GOIMON_THUAVAT + " TEXT, "
                + TB_GOIMON_MAPHUONGTHUCTHANHTOAN + " INTEGER )";

        String tbCHITETGOIMON = "CREATE TABLE " + TB_CHITIETGOIMON + " ( "
                + TB_CHITIETGOIMON_MAGOIMON + " INTEGER, "
                + TB_CHITIETGOIMON_MAMONAN + " INTEGER, "
                + TB_CHITIETGOIMON_SOLUONG + " INTEGER, "
                + TB_CHITIETGOIMON_TRANGTHAI + " TEXT,"
                + "PRIMARY KEY ( " + TB_CHITIETGOIMON_MAMONAN + "," + TB_CHITIETGOIMON_MAGOIMON + "))";

        String tbROLE = "CREATE TABLE " + TB_ROLE + " ( "
                + TB_ROLE_MAROLE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_ROLE_CHUCVU + " TEXT, "
                + TB_ROLE_MOTA + " TEXT )";

        String tbNGUYENLIEU = "CREATE TABLE " + TB_NGUYENLIEU + " ( "
                + TB_NGUYENLIEU_MANGUYENLIEU + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_NGUYENLIEU_TENNGUYENLIEU + " TEXT ,"
                + TB_NGUYENLIEU_KHOILUONG + " TEXT, "
                + TB_NGUYENLIEU_MOTTA + " TETX )";

        String tbNGUYENLIEUMONAN = "CREATE TABLE " + TB_NGUYENLIEUMONAN + " ( "
                + TB_NGUYENLIEUMONAN_MANGUYENLIEU + " INTEGER, "
                + TB_NGUYENLEUMONAN_MAMONAN + " INTEGER, "
                + "PRIMARY KEY (" + TB_NGUYENLIEUMONAN_MANGUYENLIEU + ", " + TB_NGUYENLEUMONAN_MAMONAN + "))";

        String tbPHUONGTHUCTHANHTOAN = "CREATE TABLE " + TB_PHUONGTHUCTHANHTOAN + " ( "
                + TB_PHUONGTHUCTHANHTOAN_MAPTTH + " TEXT, "
                + TB_PHUONGTHUCTHANHTOAN_NAME + " TEXT )";

        db.execSQL(tbUSER);
        db.execSQL(tbROLE);
        db.execSQL(tbBANAN);
        db.execSQL(tbMONAN);
        db.execSQL(tbLOAIMONAN);
        db.execSQL(tbGOIMON);
        db.execSQL(tbCHITETGOIMON);
        db.execSQL(tbNGUYENLIEU);
        db.execSQL(tbNGUYENLIEUMONAN);
        db.execSQL(tbPHUONGTHUCTHANHTOAN);
        themTaiKhoanMacDinh(db);

        // Thêm dữ liệu mặc định cho bảng ROLE
        db.execSQL("INSERT INTO ROLE (CHUCVU, MOTA) VALUES ('Admin', 'Quản trị hệ thống')");
        db.execSQL("INSERT INTO ROLE (CHUCVU, MOTA) VALUES ('Nhân viên', 'Nhân viên phục vụ')");
        db.execSQL("INSERT INTO ROLE (CHUCVU, MOTA) VALUES ('Khách hàng', 'Khách hàng sử dụng dịch vụ')");

        // Thêm dữ liệu mặc định cho bảng PHUONGTHUCTHANHTOAN
        db.execSQL("INSERT INTO PHUONGTHUCTHANHTOAN (NAME) VALUES ('Tiền mặt')");
        db.execSQL("INSERT INTO PHUONGTHUCTHANHTOAN (NAME) VALUES ('Thẻ tín dụng')");
        db.execSQL("INSERT INTO PHUONGTHUCTHANHTOAN (NAME) VALUES ('Chuyển khoản ngân hàng')");
        db.execSQL("INSERT INTO PHUONGTHUCTHANHTOAN (NAME) VALUES ('Momo')");

        // Thêm dữ liệu mặc định cho bảng LOAIMONAN
        db.execSQL("INSERT INTO LOAIMONAN (TENLOAI, MOTA) VALUES ('Món chính', 'Các món ăn chính')");
        db.execSQL("INSERT INTO LOAIMONAN (TENLOAI, MOTA) VALUES ('Đồ uống', 'Các loại đồ uống')");
        db.execSQL("INSERT INTO LOAIMONAN (TENLOAI, MOTA) VALUES ('Tráng miệng', 'Các món tráng miệng')");

        // Thêm dữ liệu mặc định cho bảng BANAN
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 1', 'Trống')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 2', 'Trống')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 3', 'Đang sử dụng')");

        // Thêm dữ liệu mặc định cho bảng NGUYENLIEU
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Thịt bò', '100 kg', 'Thịt bò tươi')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Rau xà lách', '50 kg', 'Rau sạch')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Gạo', '200 kg', 'Gạo thơm')");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TB_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TB_ROLE);
            db.execSQL("DROP TABLE IF EXISTS " + TB_BANAN);
            db.execSQL("DROP TABLE IF EXISTS " + TB_MONAN);
            db.execSQL("DROP TABLE IF EXISTS " + TB_LOAIMONAN);
            db.execSQL("DROP TABLE IF EXISTS " + TB_GOIMON);
            db.execSQL("DROP TABLE IF EXISTS " + TB_CHITIETGOIMON);
            db.execSQL("DROP TABLE IF EXISTS " + TB_NGUYENLIEU);
            db.execSQL("DROP TABLE IF EXISTS " + TB_NGUYENLIEUMONAN);
            db.execSQL("DROP TABLE IF EXISTS " + TB_PHUONGTHUCTHANHTOAN);
            onCreate(db);
        }

    }

    private void themTaiKhoanMacDinh(SQLiteDatabase db) {
        // Mã hóa mật khẩu
        String matKhauAdmin = PasswordUtils.bamMatKhau("admin123456789");
        String matKhauStaff = PasswordUtils.bamMatKhau("staff123456789");

        ContentValues adminValues = new ContentValues();
        adminValues.put(TB_USER_TENDN, "admin");
        adminValues.put(TB_USER_MATKHAU, matKhauAdmin);
        adminValues.put(TB_USER_GIOITINH, "Nam");
        adminValues.put(TB_USER_NGAYSINH, "01/01/1990");
        adminValues.put(TB_USER_EMAIL, "admin@example.com");
        adminValues.put(TB_USER_MAROLE, 1); // Admin
        adminValues.put(TB_USER_SDT, "0123456789");
        adminValues.put(TB_USER_FULLNAME, "Admin User");

        ContentValues staffValues = new ContentValues();
        staffValues.put(TB_USER_TENDN, "staff");
        staffValues.put(TB_USER_MATKHAU, matKhauStaff);
        staffValues.put(TB_USER_GIOITINH, "Nữ");
        staffValues.put(TB_USER_NGAYSINH, "01/01/1995");
        staffValues.put(TB_USER_EMAIL, "staff@example.com");
        staffValues.put(TB_USER_MAROLE, 2); // Nhân viên
        staffValues.put(TB_USER_SDT, "0987654321");
        staffValues.put(TB_USER_FULLNAME, "Staff User");

        // Thêm admin và staff vào bảng USER
        db.insertWithOnConflict(TB_USER, null, adminValues, SQLiteDatabase.CONFLICT_REPLACE);
        db.insertWithOnConflict(TB_USER, null, staffValues, SQLiteDatabase.CONFLICT_REPLACE);

    }

    public  SQLiteDatabase open() {
        return this.getWritableDatabase();
    }
}